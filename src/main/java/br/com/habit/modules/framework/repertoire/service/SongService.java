package br.com.habit.modules.framework.repertoire.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.repertoire.dto.SongRequest;
import br.com.habit.modules.framework.repertoire.exceptions.SongNotFoundException;
import br.com.habit.modules.framework.repertoire.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.repertoire.dto.SongResponse;
import br.com.habit.modules.framework.repertoire.enums.LearningStatusType;
import br.com.habit.modules.framework.repertoire.repository.SongRepository;

@Service
@RequiredArgsConstructor
public class SongService {
  private final SongRepository songRepository;

  public Map<LearningStatusType, List<SongResponse>> findAllByUser(User user) {
    return songRepository.findAllByUser(user).stream()
        .collect(
            Collectors.groupingBy(
                Song::getStatus, Collectors.mapping(SongResponse::new, Collectors.toList())));
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

  public SongResponse updateSongStatus(UUID id, LearningStatusType status, User user) {
    Song song = songRepository.findByIdAndUser(id, user).orElseThrow(SongNotFoundException::new);
    song.setStatus((status));

    return new SongResponse(songRepository.save(song));
  }

  public void delete(UUID id, User user) {
    Song song = songRepository.findByIdAndUser(id, user).orElseThrow(SongNotFoundException::new);
    songRepository.delete(song);
  }
}
