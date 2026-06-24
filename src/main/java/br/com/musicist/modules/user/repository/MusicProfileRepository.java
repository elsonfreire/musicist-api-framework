package br.com.musicist.modules.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicist.modules.user.model.MusicProfile;

public interface MusicProfileRepository extends JpaRepository<MusicProfile, UUID> {
    
}
