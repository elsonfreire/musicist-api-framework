package br.com.habit.modules.framework.user_collection.repository;

import br.com.habit.modules.framework.user_collection.model.UserCollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.habit.modules.framework.user.model.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface UserCollectionRepository<T extends UserCollectionItem>  extends JpaRepository<T, UUID> {
  List<T> findAllByUser(User user);

  Optional<T> findByIdAndUser(UUID id, User user);
}
