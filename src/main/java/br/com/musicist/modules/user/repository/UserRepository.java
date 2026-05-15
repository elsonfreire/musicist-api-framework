package br.com.musicist.modules.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  @EntityGraph(attributePaths = "interests")
  List<User> findByIdNotIn(List<Long> ids);
}
