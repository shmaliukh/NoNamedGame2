package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.World;
import xyz.nonamed.gameserver.services.GameObjectService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class GameObjectController {

    final GameObjectService gameObjectService;

    @GetMapping("/{userName}/{sessionCode}/getGameObjectList")
    public List<GameObject> getGameObjectList(@PathVariable("userName") String userName,
                                              @PathVariable("sessionCode") String sessionCode) {
        return gameObjectService.getGameObjectListBySessionCode(sessionCode);
    }

    @PostMapping("/{userName}/{sessionCode}/postGenerateGameObjects")
    public List<GameObject> postGenerateGameObjects(@RequestBody World world,
                                                    @PathVariable("userName") String userName,
                                                    @PathVariable("sessionCode") String sessionCode) {
        return gameObjectService.generateGameObjects(world, sessionCode);
    }

}
