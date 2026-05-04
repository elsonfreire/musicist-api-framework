package br.com.musicist.modules.repertoire.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicist.modules.repertoire.dto.SongResponse;
import br.com.musicist.modules.repertoire.enums.LearningStatusType;
import br.com.musicist.modules.repertoire.service.SongService;
import br.com.musicist.modules.user.model.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/repertoire")
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping
    public Map<LearningStatusType, List<SongResponse>> getSongsByStatus(
        @AuthenticationPrincipal User currentUser
    ) {
        return songService.findAllByUser(currentUser);
    }
}
