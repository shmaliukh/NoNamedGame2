package xyz.nonamed.gameserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.dto.Session;
import xyz.nonamed.dto.World;
import xyz.nonamed.factories.BotFactory;
import xyz.nonamed.gameserver.services.BotService;
import xyz.nonamed.gameserver.services.HeroService;
import xyz.nonamed.gameserver.services.SessionService;
import xyz.nonamed.gameserver.services.WorldService;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static xyz.nonamed.Constants.MOVE_LEFT;
import static xyz.nonamed.Constants.MOVE_RIGHT;


@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class ScheduleTaskExecutor {

    static Random random = new Random();

    final BotService botService;
    final HeroService heroService;
    final SessionService sessionService;
    final WorldService worldService;

    @Scheduled(fixedDelay = 10)
    public void moveBots() {
        sessionService.readAllSessions().stream()
                .filter(Session::isRun)
                .map(Session::getSessionCode)
                .forEach(this::moveBotsBySession);
    }

    private void moveBotsBySession(String sessionCode) {
        List<Bot> botList = botService.readBotListBySessionCode(sessionCode).stream()
                .filter(bot -> bot.getUserName() == null)
                .collect(Collectors.toList()); // todo
        List<Hero> heroEntities = heroService.readHeroesBySessionCode(sessionCode);

        for (Bot bot : botList) {
            double botSpeed = bot.getSpeed();
            double botPosX = bot.getPosX();
            double botPosY = bot.getPosY();
            Optional<Hero> theClosestHero = getTheClosestHero(heroEntities, bot);
            if (theClosestHero.isPresent()) {
                moveToClosestHero(bot, botSpeed, botPosX, botPosY, theClosestHero);
            } else {
                freeMove(bot, botSpeed);
            }
        }
        botService.save(botList);
    }

    private static void moveToClosestHero(Bot bot, double botSpeed, double botPosX, double botPosY, Optional<Hero> theClosestHero) {
        Hero hero = theClosestHero.get();

        if (hero.getPosX() > botPosX) {
            bot.setPosX(botPosX + botSpeed);
            bot.setAnimationType(MOVE_RIGHT);
        } else if (hero.getPosX() < botPosX) {
            bot.setPosX(botPosX - botSpeed);
            bot.setAnimationType(MOVE_LEFT);
        }
        if (hero.getPosY() > botPosY) {
            bot.setPosY(botPosY + botSpeed);
        } else if (hero.getPosY() < botPosY) {
            bot.setPosY(botPosY - botSpeed);
        }
    }

    private static void freeMove(Bot bot, double botSpeed) {
        double additional = random.nextDouble(botSpeed * 0.1);
        botSpeed += additional;
        if (random.nextBoolean()) {
            bot.setPosX(bot.getPosX() + botSpeed);
            bot.setAnimationType(MOVE_RIGHT);
        }
        if (random.nextBoolean()) {
            bot.setPosX(bot.getPosX() - botSpeed);
            bot.setAnimationType(MOVE_LEFT);
        }
        if (random.nextBoolean()) {
            bot.setPosY(bot.getPosY() + botSpeed);
        }
        if (random.nextBoolean()) {
            bot.setPosY(bot.getPosY() - botSpeed);
        }
        log.info("free move bot '{}'", bot);
    }

    @Scheduled(fixedDelay = 5000)
    public void generateBotToRandomSession() {
        sessionService.readAllSessions().forEach(this::generateNewBotToSession);
    }

    private void generateNewBotToSession(Session session) {
        World world = worldService.readWorldBySessionCode(session.getSessionCode());
        if (session.getBotCounter() < session.getBotMaxCounter()) {
            BotFactory botFactory = new BotFactory();
            String randomType = getRandomType(botFactory);

            Bot bot = botFactory.create(randomType);
            bot.setPosX(random.nextDouble(World.DEFAULT_WIDTH));
            bot.setPosY(random.nextDouble(World.DEFAULT_HEIGHT));
            botService.addNewBotToSession(bot, session.getSessionCode());
            session.setBotCounter(session.getBotCounter() + 1);
            sessionService.save(session);
        } else {
            log.info("generated max bot quantity '{}' // session: '{}'", session.getBotCounter(), session);
        }
    }

    private static String getRandomType(BotFactory botFactory) {
        List<String> typeList = botFactory.getTypeList();
        return typeList.get(random.nextInt(typeList.size()));
    }

    public static Optional<Hero> getTheClosestHero(List<Hero> heroEntities, Bot bot) {
        return heroEntities.stream()
                .filter(o -> getDistance(new Point(o.posX, o.posY), new Point(bot.posX, bot.posY)) < bot.getMinDistanceToActivate())
                .findFirst();
    }

    public static double getDistance(Point p1, Point p2) { // TODO refactor
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Data
    @AllArgsConstructor
    static class Point {

        public double x;
        public double y;

    }

}