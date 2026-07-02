package br.com.habit.modules.readist.profile;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadProfileRepository extends JpaRepository<ReadProfile, UUID> {
}