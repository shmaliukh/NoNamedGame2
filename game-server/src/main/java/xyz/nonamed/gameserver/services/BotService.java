package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.gameserver.repositories.BotRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BotService {

    final BotRepository botRepository;

    public List<Bot> readBotListBySessionCode(String sessionCode) {
        return botRepository.readAllBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public Bot save(Bot bot) {
        return botRepository.saveAndFlush(bot);
    }

    public List<Bot> save(List<Bot> botList) {
        return botRepository.saveAllAndFlush(botList);
    }

    public Bot addNewBotToSession(Bot bot, String sessionCode) {
        bot.setSessionCode(sessionCode);
        bot = save(bot);
        log.info("created new bot: '{}' // session code: '{}'", bot, sessionCode);
        return bot;
    }

    public List<Bot> getBotListBySessionCode(String sessionCode) {
        return new ArrayList<>(readBotListBySessionCode(sessionCode));
    }

}
