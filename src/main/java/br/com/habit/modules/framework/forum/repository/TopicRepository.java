package br.com.habit.modules.framework.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.habit.modules.framework.forum.model.Topic;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {}
