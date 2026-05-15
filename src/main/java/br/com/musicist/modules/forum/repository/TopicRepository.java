package br.com.musicist.modules.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {}
