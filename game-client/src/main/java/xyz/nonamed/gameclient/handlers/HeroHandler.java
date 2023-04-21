package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.Actions;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.gameclient.client_protocols.HeroAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class HeroHandler implements HeroAPI {

    public HeroAPI gameApi = Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(HeroAPI.class, BASE_URL);

    @Override
    public List<Hero> getHeroList(String userName, String sessionCode) {
        return gameApi.getHeroList(userName, sessionCode);
    }

    @Override
    public void postActions(Actions actions, String userName, String sessionCode) {
        gameApi.postActions(actions, userName, sessionCode);
    }

    @Override
    public Hero postRegisterHero(Hero hero, String userName, String sessionCode) {
        return gameApi.postRegisterHero(hero, userName, sessionCode);
    }

}
