package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.Constants;
import xyz.nonamed.dto.Actions;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.factories.HeroFactory;
import xyz.nonamed.gameserver.repositories.HeroRepository;

import java.util.ArrayList;
import java.util.List;

import static xyz.nonamed.Constants.MOVE_LEFT;
import static xyz.nonamed.Constants.MOVE_RIGHT;

@Slf4j
@Service
@AllArgsConstructor
public class HeroService {

    final HeroRepository heroRepository;
    final HeroFactory heroFactory = new HeroFactory();

    public Hero save(Hero entity) {
        return heroRepository.saveAndFlush(entity);
    }

    public Hero registerHero(Hero hero, String sessionCode, boolean isCustom) {
        Hero heroEntity = heroRepository.readByNameEqualsIgnoreCaseAndSessionCodeEqualsIgnoreCase(hero.getName(), sessionCode);
        if (heroEntity == null) {
            heroEntity = heroFactory.create(hero.getType());
            heroEntity.setName(hero.getName());
            heroEntity.setColor(hero.getColor());
            heroEntity.setSessionCode(sessionCode);
            if (isCustom) { // todo implement ???
                heroEntity = hero;
            }
            heroEntity = save(heroEntity);
            log.info("registered hero: '{}'", hero);
        }
        return heroEntity;
    }

    public List<Hero> getHeroList(String userName, String sessionCode) {
        return new ArrayList<>(readHeroesBySessionCode(sessionCode));
    }

    public List<Hero> readHeroesBySessionCode(String sessionCode) {
        return heroRepository.readAllBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    // TODO refactor (delegate) move printing to client app
    // no need to read current hero state per request
    public void postActions(Actions actions, String userName, String sessionCode) {
        Hero heroEntity = heroRepository.readByNameEqualsIgnoreCaseAndSessionCodeEqualsIgnoreCase(userName, sessionCode);
        for (String actionStr : actions.getActionStrList()) {
            doAction(heroEntity, actionStr);
        }
        save(heroEntity);
    }

    private void doAction(Hero heroEntity, String action) {
        switch (action) {
            case Constants.MOVE_UP -> moveUp(heroEntity);
            case Constants.MOVE_DOWN -> moveDown(heroEntity);
            case Constants.MOVE_LEFT -> moveLeft(heroEntity);
            case Constants.MOVE_RIGHT -> moveRight(heroEntity);
            default -> log.error("unexpected action: '{}' // hero: '{}'", action, heroEntity);
        }
        log.info("hero do action: '{}' // hero: '{}'", action, heroEntity);
    }

    public void moveUp(Hero hero) {
        hero.setPosY(hero.getPosY() - hero.getSpeed());
    }

    public void moveDown(Hero hero) {
        hero.setPosY(hero.getPosY() + hero.getSpeed());
    }

    public void moveLeft(Hero hero) {
        hero.setPosX(hero.getPosX() - hero.getSpeed());
        hero.setAnimationType(MOVE_LEFT);
    }

    public void moveRight(Hero hero) {
        hero.setPosX(hero.getPosX() + hero.getSpeed());
        hero.setAnimationType(MOVE_RIGHT);
    }

}