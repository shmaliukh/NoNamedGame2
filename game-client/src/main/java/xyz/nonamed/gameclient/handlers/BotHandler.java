package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.gameclient.client_protocols.BotAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class BotHandler implements BotAPI {

    public BotAPI botAPI = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(BotAPI.class, BASE_URL);

    @Override
    public List<Bot> getBotList(String userName, String sessionCode) {
        return botAPI.getBotList(userName, sessionCode);
    }

    @Override
    public Bot postUpdateBot(Bot bot, String userName, String sessionCode) {
        return botAPI.postUpdateBot(bot, userName, sessionCode);
    }

}
