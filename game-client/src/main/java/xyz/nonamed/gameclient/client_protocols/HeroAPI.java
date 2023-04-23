package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.Actions;
import xyz.nonamed.dto.Hero;

import java.util.List;

@Headers("Accept: application/json")
public interface HeroAPI {

    @RequestLine("GET /{userName}/{sessionCode}/getHeroList")
    List<Hero> getHeroList(@Param("userName") String userName,
                           @Param("sessionCode") String sessionCode);


    @RequestLine("POST /{userName}/{sessionCode}/postActions")
    @Headers("Content-Type: application/json")
    void postActions(Actions actions,
                     @Param("userName") String userName,
                     @Param("sessionCode") String sessionCode);

    @RequestLine("POST /{userName}/{sessionCode}/postRegisterHero")
    @Headers("Content-Type: application/json")
    Hero postRegisterHero(Hero hero,
                          @Param("userName") String userName,
                          @Param("sessionCode") String sessionCode);

    @RequestLine("POST /{userName}/{sessionCode}/postUpdateHero")
    @Headers("Content-Type: application/json")
    Hero postUpdateHero(Hero hero,
                        @Param("userName") String userName,
                        @Param("sessionCode") String sessionCode);

}
