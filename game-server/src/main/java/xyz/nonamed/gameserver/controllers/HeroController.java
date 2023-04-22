package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.nonamed.dto.Actions;
import xyz.nonamed.dto.Hero;
import xyz.nonamed.gameserver.services.HeroService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class HeroController {

    final HeroService heroService;

    @GetMapping("/{userName}/{sessionCode}/getHeroList")
    public List<Hero> getHeroList(@PathVariable("userName") String userName,
                                  @PathVariable("sessionCode") String sessionCode) {
        return heroService.getHeroList(userName, sessionCode);
    }


    @PostMapping(value = "/{userName}/{sessionCode}/postActions")
    public void postActions(@RequestBody Actions actions,
                            @PathVariable("userName") String userName,
                            @PathVariable("sessionCode") String sessionCode) {
        heroService.postActions(actions, userName, sessionCode);
    }

    @PostMapping("/{userName}/{sessionCode}/postRegisterHero")
    public Hero postRegisterHero(@RequestBody Hero hero,
                                 @PathVariable("userName") String userName,
                                 @PathVariable("sessionCode") String sessionCode) {
        log.info("user '{}' registered new hero", userName);
        return heroService.registerHero(hero, sessionCode, false);
    }

    @PostMapping("/{userName}/{sessionCode}/postUpdateHero")
    public Hero postUpdateHero(@RequestBody Hero hero,
                                 @PathVariable("userName") String userName,
                                 @PathVariable("sessionCode") String sessionCode) {
        return heroService.postUpdateHero(hero, userName, sessionCode);
    }

}
