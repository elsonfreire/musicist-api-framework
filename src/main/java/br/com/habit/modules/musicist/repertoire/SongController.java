package br.com.habit.modules.musicist.repertoire;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import br.com.habit.modules.framework.user_collection.controller.UserCollectionController;
import br.com.habit.modules.framework.user_collection.service.UserCollectionService;


@RestController
@RequestMapping("/repertoire")
@RequiredArgsConstructor
public class SongController extends UserCollectionController<SongRequest, SongResponse, LearningStatusType> {
  private final SongService songService;

  @Override
  protected UserCollectionService<SongRequest, SongResponse, LearningStatusType> service() {
    return songService;
  }
}
