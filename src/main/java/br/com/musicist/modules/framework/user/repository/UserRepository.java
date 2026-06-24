package br.com.musicist.modules.framework.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.framework.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  @EntityGraph(attributePaths = "interests")
  List<User> findByIdNotIn(List<UUID> ids);
}
