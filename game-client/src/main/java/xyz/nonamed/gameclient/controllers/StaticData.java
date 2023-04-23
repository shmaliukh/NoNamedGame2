package xyz.nonamed.gameclient.controllers;

import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.gameclient.printable.HeroFX;

import java.util.ArrayList;
import java.util.List;


public class StaticData {

    public static HeroFX MY_HERO_FX = new HeroFX();
    public static List<Hero> heroList = new ArrayList<>();
    public static List<Bot> botList = new ArrayList<>();
    public static List<Borer> borerList = new ArrayList<>();
    public static List<String> actionList = new ArrayList<>();

}
