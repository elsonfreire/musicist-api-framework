package br.com.habit.modules.musicist.profile;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicProfileRepository extends JpaRepository<MusicProfile, UUID> {
}
