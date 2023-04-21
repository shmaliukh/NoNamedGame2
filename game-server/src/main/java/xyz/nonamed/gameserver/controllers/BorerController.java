package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.gameserver.services.BorerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class BorerController {

    final BorerService borerService;

    @GetMapping("/{userName}/{sessionCode}/getBorerList")
    public List<Borer> getBorerList(@PathVariable("userName") String userName,
                                    @PathVariable("sessionCode") String sessionCode) {
        return borerService.getBorerListBySessionCode(sessionCode);
    }

}
