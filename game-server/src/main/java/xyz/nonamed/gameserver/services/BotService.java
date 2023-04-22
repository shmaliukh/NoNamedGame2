package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.dto.UserEntity;
import xyz.nonamed.gameserver.repositories.BotRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BotService {

    final UserService userService;
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

    public Bot postUpdateBot(Bot bot, String userName, String sessionCode) {
        if (bot.getHealth() <= 0) {
            botRepository.delete(bot);
            UserEntity userEntity = userService.readByName(userName);
            userEntity.setScore(userEntity.getScore() + 30);
            UserEntity save = userService.save(userEntity);
            log.info("user '{}' get score '{}' by bot '{}'", userName, save.getScore(), bot.getType());
            return null;
        }
        return save(bot);
    }

}
