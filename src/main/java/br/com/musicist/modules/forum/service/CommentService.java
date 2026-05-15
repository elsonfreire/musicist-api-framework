package br.com.musicist.modules.forum.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.musicist.modules.forum.dto.CommentRequest;
import br.com.musicist.modules.forum.dto.CommentResponse;
import br.com.musicist.modules.forum.model.Comment;
import br.com.musicist.modules.forum.model.Topic;
import br.com.musicist.modules.forum.repository.CommentRepository;
import br.com.musicist.modules.user.model.User;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;

  private final TopicService topicService;

  public List<CommentResponse> findAllByTopicId(Long topicId) {
    Topic topic = topicService.findById(topicId);

    return commentRepository.findAllByTopic(topic).stream().map(CommentResponse::new).toList();
  }

  public CommentResponse create(CommentRequest commentRequest, Long topicId, User user) {
    Topic topic = topicService.findById(topicId);

    Comment comment = new Comment(commentRequest.content(), user, topic);
    return new CommentResponse(commentRepository.save(comment));
  }
}
