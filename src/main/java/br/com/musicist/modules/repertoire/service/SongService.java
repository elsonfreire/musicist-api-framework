package br.com.musicist.modules.repertoire.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.musicist.modules.repertoire.dto.SongRequest;
import br.com.musicist.modules.repertoire.exceptions.SongNotFoundException;
import br.com.musicist.modules.repertoire.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicist.modules.repertoire.dto.SongResponse;
import br.com.musicist.modules.repertoire.enums.LearningStatusType;
import br.com.musicist.modules.repertoire.repository.SongRepository;
import br.com.musicist.modules.user.model.User;

@Service
public class SongService {
  @Autowired SongRepository songRepository;

  public Map<LearningStatusType, List<SongResponse>> findAllByUser(User user) {
    return songRepository.findAllByUser(user).stream()
        .collect(
            Collectors.groupingBy(
                song -> song.getStatus(),
                Collectors.mapping(SongResponse::new, Collectors.toList())));
  }

  public SongResponse addSong(SongRequest requestDto, User user) {
    Song song =
        new Song(
            user,
            LearningStatusType.TO_LEARN,
            requestDto.title(),
            requestDto.artist(),
            requestDto.difficulty());
    return new SongResponse(songRepository.save(song));
  }

  public SongResponse updateSongStatus(Long id, LearningStatusType status, User user) {
    Song song =
        songRepository.findByIdAndUser(id, user).orElseThrow(() -> new SongNotFoundException());
    song.setStatus((status));

    return new SongResponse(songRepository.save(song));
  }

  public void delete(Long id, User user) {
    Song song =
        songRepository.findByIdAndUser(id, user).orElseThrow(() -> new SongNotFoundException());
    songRepository.delete(song);
  }
}
