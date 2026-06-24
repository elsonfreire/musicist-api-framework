package br.com.musicist.modules.framework.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.framework.forum.model.Comment;
import br.com.musicist.modules.framework.forum.model.Topic;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
  List<Comment> findAllByTopic(Topic topic);
}
