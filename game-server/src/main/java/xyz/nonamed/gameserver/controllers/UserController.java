package xyz.nonamed.gameserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.nonamed.dto.User;
import xyz.nonamed.gameserver.entities.UserEntity;
import xyz.nonamed.gameserver.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping
public class UserController  {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/{userName}/postRegisterUser")
    public User postRegisterUser(@PathVariable("userName") String userName) {
        UserEntity userEntity = userService.registerUserByName(userName);
        return userService.toDto(userEntity);
    }

    @GetMapping(value = "/{userName}/{sessionCode}/getUsersBySessionCode")
    public List<User> getUsersBySessionCode(@PathVariable("userName") String userName,
                                            @PathVariable("sessionCode") String sessionCode) {
        List<UserEntity> userEntities = userService.readAllBySessionCode(sessionCode);
        return userEntities.stream()
                .map(userService::toDto)
                .collect(Collectors.toList());
    }

}
