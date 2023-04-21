package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.Bot;

import java.util.List;

@Headers("Accept: application/json")
public interface BotAPI {

    @RequestLine("GET /{userName}/{sessionCode}/getBotList")
    List<Bot> getBotList(@Param("userName") String userName,
                         @Param("sessionCode") String sessionCode);

}
