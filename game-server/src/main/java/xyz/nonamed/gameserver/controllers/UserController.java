package xyz.nonamed.gameserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.nonamed.dto.UserEntity;
import xyz.nonamed.gameserver.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/{userName}/postRegisterUser")
    public UserEntity postRegisterUser(@PathVariable("userName") String userName) {
        return userService.registerUserByName(userName);
    }

    @GetMapping(value = "/{userName}/{sessionCode}/getUsersBySessionCode")
    public List<UserEntity> getUsersBySessionCode(@PathVariable("userName") String userName,
                                                  @PathVariable("sessionCode") String sessionCode) {
        List<UserEntity> userEntities = userService.readAllBySessionCode(sessionCode);
        return new ArrayList<>(userEntities);
    }

}
