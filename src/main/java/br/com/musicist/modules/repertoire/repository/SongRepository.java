package br.com.musicist.modules.repertoire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.musicist.modules.repertoire.model.Song;
import java.util.List;
import br.com.musicist.modules.user.model.User;


public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByUser(User user);
}
