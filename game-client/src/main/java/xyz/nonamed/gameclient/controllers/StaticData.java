package xyz.nonamed.gameclient.controllers;

import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.gameclient.printable.HeroFX;
import xyz.nonamed.gameclient.threads.CustomThread;

import java.util.ArrayList;
import java.util.List;

import static xyz.nonamed.gameclient.threads.ThreadConfig.ONE_MILLISECOND;

public class StaticData {

    //    public static World world = null;
    public static HeroFX MY_HERO_FX = new HeroFX();
    public static List<Hero> heroList = new ArrayList<>();
    public static List<Bot> botList = new ArrayList<>();
    public static List<Borer> borerList = new ArrayList<>();
    public static List<String> actionList = new ArrayList<>();

    public static CustomThread updateMyHeroThread = new CustomThread() {
        long delay = 50 * ONE_MILLISECOND;

        @Override
        public void doWithDelay() {

        }
    };

    public static CustomThread updateHeroesThread = new CustomThread() {
        long delay = 50 * ONE_MILLISECOND;

        @Override
        public void doWithDelay() {

        }
    };

    public static CustomThread updateBotThread = new CustomThread() {
        long delay = 500 * ONE_MILLISECOND;

        @Override
        public void doWithDelay() {

        }
    };

}
