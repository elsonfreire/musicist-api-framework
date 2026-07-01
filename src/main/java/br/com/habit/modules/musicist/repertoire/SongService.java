package br.com.habit.modules.musicist.repertoire;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user_collection.exceptions.UserCollectionItemNotFoundException;
import br.com.habit.modules.framework.user_collection.model.Song;
import br.com.habit.modules.framework.user_collection.repository.UserCollectionRepository;
import br.com.habit.modules.framework.user_collection.service.UserCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService implements UserCollectionService<SongRequest, SongResponse, LearningStatusType> {
  private final UserCollectionRepository songRepository;

  public Map<LearningStatusType, List<SongResponse>> findAllByUser(User user) {
    return songRepository.findAllByUser(user).stream()
        .collect(
            Collectors.groupingBy(
                Song::getStatus, Collectors.mapping(SongResponse::new, Collectors.toList())));
  }

  public SongResponse create(SongRequest requestDto, User user) {
    Song song =
        new Song(
            user,
            LearningStatusType.TO_LEARN,
            requestDto.title(),
            requestDto.artist(),
            requestDto.difficulty());
    return new SongResponse(songRepository.save(song));
  }

  public SongResponse updateStatus(UUID id, LearningStatusType status, User user) {
    Song song = songRepository.findByIdAndUser(id, user).orElseThrow(UserCollectionItemNotFoundException::new);
    song.setStatus((status));

    return new SongResponse(songRepository.save(song));
  }

  public void delete(UUID id, User user) {
    Song song = songRepository.findByIdAndUser(id, user).orElseThrow(UserCollectionItemNotFoundException::new);
    songRepository.delete(song);
  }
}
