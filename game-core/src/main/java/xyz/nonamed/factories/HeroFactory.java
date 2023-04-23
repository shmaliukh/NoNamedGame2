package xyz.nonamed.factories;

import xyz.nonamed.dto.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroFactory implements MyFactory<Hero> {

    public Hero create(String type) {
        return switch (type) {
            case Hero.HERO_1 -> createHero1();
            case Hero.HERO_2 -> createHero2();
            case Hero.HERO_3 -> createHero3();
            case Hero.HERO_4 -> createHero4();
            default -> createHero3();
        };
    }

    private Hero createHero1() {
        Hero hero = new Hero();
        hero.type = Hero.HERO_1;
        hero.damage = Hero.DEFAULT_HERO_DAMAGE * 0.8;
        hero.maxHealth = Hero.DEFAULT_HERO_MAX_HEALTH * 1.2;
        hero.speed = Hero.DEFAULT_HERO_SPEED * 0.95;
        hero.health = hero.maxHealth;
        hero.color = "BLUE";
        return hero;
    }

    private Hero createHero2() {
        Hero hero = new Hero();
        hero.type = Hero.HERO_2;
        hero.damage = Hero.DEFAULT_HERO_DAMAGE * 1.3;
        hero.maxHealth = Hero.DEFAULT_HERO_MAX_HEALTH * 0.75;
        hero.speed = Hero.DEFAULT_HERO_SPEED * 1.15;
        hero.health = hero.maxHealth;
        hero.color = "CRIMSON";
        return hero;
    }

    private Hero createHero3() {
        Hero hero = new Hero();
        hero.type = Hero.HERO_3;
        hero.damage = Hero.DEFAULT_HERO_DAMAGE;
        hero.maxHealth = Hero.DEFAULT_HERO_MAX_HEALTH;
        hero.speed = Hero.DEFAULT_HERO_SPEED;
        hero.health = hero.maxHealth;
        hero.color = "DARKORCHID";
        return hero;
    }

    private Hero createHero4() {
        Hero hero = new Hero();
        hero.type = Hero.HERO_4;
        hero.damage = Hero.DEFAULT_HERO_DAMAGE * 0.95;
        hero.maxHealth = Hero.DEFAULT_HERO_MAX_HEALTH * 1.1;
        hero.speed = Hero.DEFAULT_HERO_SPEED * 1.3;
        hero.health = hero.maxHealth;
        hero.color = "GREEN";
        return hero;
    }


    public List<String> getTypeList() {
        return new ArrayList<>(Hero.heroTypeList);
    }

}
