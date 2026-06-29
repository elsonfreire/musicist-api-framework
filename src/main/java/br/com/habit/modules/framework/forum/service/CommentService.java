package br.com.habit.modules.framework.forum.service;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.forum.dto.CommentRequest;
import br.com.habit.modules.framework.forum.dto.CommentResponse;
import br.com.habit.modules.framework.forum.model.Comment;
import br.com.habit.modules.framework.forum.model.Topic;
import br.com.habit.modules.framework.forum.repository.CommentRepository;
import br.com.habit.modules.framework.user.model.User;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;

  private final TopicService topicService;

  public List<CommentResponse> findAllByTopicId(UUID topicId) {
    Topic topic = topicService.findById(topicId);

    return commentRepository.findAllByTopic(topic).stream().map(CommentResponse::new).toList();
  }

  public CommentResponse create(CommentRequest commentRequest, UUID topicId, User user) {
    Topic topic = topicService.findById(topicId);

    Comment comment = new Comment(commentRequest.content(), user, topic);
    return new CommentResponse(commentRepository.save(comment));
  }
}
