package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.UserEntity;
import xyz.nonamed.gameserver.repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public UserEntity save(UserEntity entity) {
        UserEntity user = userRepository.saveAndFlush(entity);
        log.info("saved user: '{}'", user);
        return user;
    }

    public UserEntity readByName(String userName) {
        return userRepository.readFirstByNameEqualsIgnoreCase(userName);
    }

    public List<UserEntity> readAllBySessionCode(String sessionCode) {
        return userRepository.readAllBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public UserEntity registerUserByName(String userName) {
        UserEntity user = readByName(userName);
        if (user == null) {
            user = new UserEntity();
            user.setName(userName);
            user = save(user); // need to set id
            log.info("created new user: '{}'", user);
        }
        return user;
    }

}
