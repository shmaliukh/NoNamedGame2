package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.Borer;

import java.util.List;

@Headers("Accept: application/json")
public interface BorerAPI {

    @RequestLine("GET /{userName}/{sessionCode}/getBorerList")
    List<Borer> getBorerList(@Param("userName") String userName,
                             @Param("sessionCode") String sessionCode);

}
