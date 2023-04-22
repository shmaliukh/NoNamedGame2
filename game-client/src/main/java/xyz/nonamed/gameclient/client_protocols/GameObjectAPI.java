package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.dto.World;

import java.util.List;

@Headers("Accept: application/json")
public interface GameObjectAPI {

    @RequestLine("GET /{userName}/{sessionCode}/getGameObjectList")
    List<GameObject> getGameObjectList(@Param("userName") String userName,
                                       @Param("sessionCode") String sessionCode);

    @RequestLine("POST /{userName}/{sessionCode}/postGenerateGameObjects")
    @Headers("Content-Type: application/json")
    List<GameObject> postGenerateGameObjects(World world,
                                             @Param("userName") String userName,
                                             @Param("sessionCode") String sessionCode);


}
