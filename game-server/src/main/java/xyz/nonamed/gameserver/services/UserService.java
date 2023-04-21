package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.User;
import xyz.nonamed.gameserver.ConvertToDto;
import xyz.nonamed.gameserver.entities.UserEntity;
import xyz.nonamed.gameserver.repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements ConvertToDto<UserEntity, User> {

    final UserRepository userRepository;

    public UserEntity save(UserEntity entity) {
        UserEntity user = userRepository.saveAndFlush(entity);
        log.info("saved user: '{}'", user);
        return user;
    }

    public UserEntity readByName(String userName){
        return userRepository.readFirstByNameEqualsIgnoreCase(userName);
    }

    public List<UserEntity> readAllBySessionCode(String sessionCode){
        return userRepository.readAllBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public UserEntity registerUserByName(String userName){
        UserEntity userEntity = readByName(userName);
        if(userEntity == null){
            userEntity = new UserEntity();
            userEntity.setName(userName);
            userEntity = save(userEntity); // need to set id
            log.info("created new user: '{}'", userEntity);
        }
        return userEntity;
    }

    @Override
    public User toDto(UserEntity entity) {
        User user = new User();
        user.setName(entity.getName());
        user.setSessionCode(entity.getSessionCode());
        user.setScore(entity.getScore());
        return user;
    }

}
