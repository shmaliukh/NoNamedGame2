package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.gameserver.services.BotService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class BotController {

    final BotService botService;

    @GetMapping("/{userName}/{sessionCode}/getBotList")
    public List<Bot> getBotList(@PathVariable("userName") String userName,
                                @PathVariable("sessionCode") String sessionCode) {
        // TODO add check if user is active
        return botService.getBotListBySessionCode(sessionCode);
    }

}
