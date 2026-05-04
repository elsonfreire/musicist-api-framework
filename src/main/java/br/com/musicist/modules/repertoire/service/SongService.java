package br.com.musicist.modules.repertoire.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicist.modules.repertoire.dto.SongResponse;
import br.com.musicist.modules.repertoire.enums.LearningStatusType;
import br.com.musicist.modules.repertoire.model.Song;
import br.com.musicist.modules.repertoire.repository.SongRepository;
import br.com.musicist.modules.user.model.User;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    public Map<LearningStatusType, List<SongResponse>> findAllByUser(User user) {
        return songRepository.findAllByUser(user)
            .stream()
            .collect(
                Collectors.groupingBy(
                    Song::getStatus,
                    Collectors.mapping(
                        SongResponse::new, 
                        Collectors.toList()
                    )
                )
            );
    }
}
