package xyz.nonamed.factories;

import xyz.nonamed.dto.Bot;
import xyz.nonamed.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class BotFactory implements MyFactory<Bot> {

    public Bot create(String type) {
        return switch (type) {
            case Bot.BOT_1 -> createBot1();
            case Bot.BOT_2 -> createBot2();
//            case Bot.BOT_3 -> createBot3();
//            case Bot.BOT_4 -> createBot4();
//            case Bot.BOT_5 -> createBot5();
//            case Bot.BOT_6 -> createBot5();
            default -> createBot1();
        };
    }

    public Bot createBot1() {
        Bot bot = new Bot();
        bot.setType(Bot.BOT_1);
        bot.setMaxHealth(Bot.DEFAULT_BOT_MAX_HEALTH * 1.1);
        bot.setDamage(Bot.DEFAULT_BOT_DAMAGE * 1.1);
        bot.setSpeed(Bot.DEFAULT_BOT_SPEED);
        bot.setMinDistanceToActivate(Bot.DEFAULT_BOT_DISTANCE_TO_ACTIVATE * 1.3);
        bot.setColor("OLIVE");
        bot.setHealth(bot.getMaxHealth());
        bot.setName(DataUtils.getRandomName());
        return bot;
    }

    public Bot createBot2() {
        Bot bot = new Bot();
        bot.setType(Bot.BOT_2);
        bot.setMaxHealth(Bot.DEFAULT_BOT_MAX_HEALTH * 2.0);
        bot.setDamage(Bot.DEFAULT_BOT_DAMAGE * 1.2);
        bot.setSpeed(Bot.DEFAULT_BOT_SPEED * 0.6);
        bot.setMinDistanceToActivate(Bot.DEFAULT_BOT_DISTANCE_TO_ACTIVATE * 1.3);
        bot.setColor("FORESTGREEN");
        bot.setHealth(bot.getMaxHealth());
        bot.setName(DataUtils.getRandomName());
        return bot;
    }

    public Bot createBot3() {
        Bot bot = new Bot();
        bot.setType(Bot.BOT_3);
        bot.setMaxHealth(Bot.DEFAULT_BOT_MAX_HEALTH);
        bot.setDamage(Bot.DEFAULT_BOT_DAMAGE);
        bot.setSpeed(Bot.DEFAULT_BOT_SPEED);
        bot.setMinDistanceToActivate(Bot.DEFAULT_BOT_DISTANCE_TO_ACTIVATE * 0.8);
        bot.setColor("LAWNGREEN");
        bot.setHealth(bot.getMaxHealth());
        bot.setName(DataUtils.getRandomName());
        return bot;
    }

    public Bot createBot4() {
        Bot bot = new Bot();
        bot.setType(Bot.BOT_4);
        bot.setMaxHealth(Bot.DEFAULT_BOT_MAX_HEALTH * 0.5);
        bot.setDamage(Bot.DEFAULT_BOT_DAMAGE * 0.7);
        bot.setSpeed(Bot.DEFAULT_BOT_SPEED * 2.0);
        bot.setMinDistanceToActivate(Bot.DEFAULT_BOT_DISTANCE_TO_ACTIVATE * 2);
        bot.setColor("LIGHTGREEN");
        bot.setHealth(bot.getMaxHealth());
        bot.setName(DataUtils.getRandomName());
        return bot;
    }

    public Bot createBot5() {
        Bot bot = new Bot();
        bot.setType(Bot.BOT_5);
        bot.setMaxHealth(Bot.DEFAULT_BOT_MAX_HEALTH * 2.0);
        bot.setDamage(Bot.DEFAULT_BOT_DAMAGE * 1.7);
        bot.setSpeed(Bot.DEFAULT_BOT_SPEED * 0.5);
        bot.setMinDistanceToActivate(Bot.DEFAULT_BOT_DISTANCE_TO_ACTIVATE * 0.9);
        bot.setColor("LIGHTGREEN");
        bot.setHealth(bot.getMaxHealth());
        bot.setName(DataUtils.getRandomName());
        return bot;
    }

    public Bot createBot6() {
        Bot bot = new Bot();
        bot.setType(Bot.BOT_6);
        bot.setMaxHealth(Bot.DEFAULT_BOT_MAX_HEALTH * 0.8);
        bot.setDamage(Bot.DEFAULT_BOT_DAMAGE * 0.7);
        bot.setSpeed(Bot.DEFAULT_BOT_SPEED * 1.3);
        bot.setMinDistanceToActivate(Bot.DEFAULT_BOT_DISTANCE_TO_ACTIVATE);
        bot.setColor("MEDIUMAQUAMARINE");
        bot.setHealth(bot.getMaxHealth());
        bot.setName(DataUtils.getRandomName());
        return bot;
    }

    public List<String> getTypeList() {
        return new ArrayList<>(Bot.botTypeList);
    }

}
