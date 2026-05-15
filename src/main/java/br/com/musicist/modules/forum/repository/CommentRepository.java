package br.com.musicist.modules.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.forum.model.Comment;
import br.com.musicist.modules.forum.model.Topic;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByTopic(Topic topic);
}
