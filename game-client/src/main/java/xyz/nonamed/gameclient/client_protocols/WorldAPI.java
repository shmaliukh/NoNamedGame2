package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.World;

import java.util.List;

@Headers("Accept: application/json")
public interface WorldAPI {

    @RequestLine("GET /{userName}/{sessionCode}/getWorldBySession")
    World getWorldBySession(@Param("userName") String userName,
                            @Param("sessionCode") String sessionCode);

}
