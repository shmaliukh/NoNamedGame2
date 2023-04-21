package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class IsServerAliveController {

    @GetMapping(value = "/getIsServerAlive")
    public boolean getIsServerAlive() {
        // TODO add some info to user (no priority)
        return true;
    }

}
