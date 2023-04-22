package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.World;
import xyz.nonamed.gameserver.services.BorerService;
import xyz.nonamed.gameserver.services.WorldService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class WorldController {

    final WorldService worldService;

    @GetMapping("/{userName}/{sessionCode}/getWorldBySession")
    public World getWorldBySession(@PathVariable("userName") String userName,
                                   @PathVariable("sessionCode") String sessionCode) {
        World world = worldService.readWorldBySessionCode(sessionCode);
        if (world == null) {
            world = worldService.addWorldToSession(new World(), sessionCode);
            // TODO implement ability to configWorld
        }
        return world;
    }

}
