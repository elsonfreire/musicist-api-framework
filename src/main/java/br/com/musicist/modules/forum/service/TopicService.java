package br.com.musicist.modules.forum.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.musicist.modules.forum.dto.TopicRequest;
import br.com.musicist.modules.forum.dto.TopicResponse;
import br.com.musicist.modules.forum.exceptions.CannotDeleteFromOtherUserException;
import br.com.musicist.modules.forum.exceptions.TopicNotFoundException;
import br.com.musicist.modules.forum.model.Topic;
import br.com.musicist.modules.forum.repository.TopicRepository;
import br.com.musicist.modules.user.model.User;

@Service
@RequiredArgsConstructor
public class TopicService {
  private final TopicRepository topicRepository;

  public Topic findById(Long topicId) {
    return topicRepository
        .findById(topicId)
        .orElseThrow(() -> new RuntimeException("Topic does not exist"));
  }

  public List<TopicResponse> findAll() {
    return topicRepository.findAll().stream().map(TopicResponse::new).toList();
  }

  public TopicResponse create(TopicRequest topicRequest, User user) {
    Topic topic =
        new Topic(topicRequest.title(), topicRequest.category(), topicRequest.description(), user);
    return new TopicResponse(topicRepository.save(topic));
  }

  public void delete(Long id, User currentUser) {
    Topic topic = topicRepository.findById(id).orElseThrow(TopicNotFoundException::new);

    if (!topic.getUser().getId().equals(currentUser.getId())) {
      throw new CannotDeleteFromOtherUserException();
    }

    topicRepository.delete(topic);
  }
}
