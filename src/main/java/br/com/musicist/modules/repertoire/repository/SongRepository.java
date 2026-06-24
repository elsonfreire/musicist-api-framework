package br.com.musicist.modules.repertoire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.framework.user.model.User;
import br.com.musicist.modules.repertoire.model.Song;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
  List<Song> findAllByUser(User user);

  Optional<Song> findByIdAndUser(UUID id, User user);
}
