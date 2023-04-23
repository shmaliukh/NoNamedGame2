package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.World;
import xyz.nonamed.factories.GameObjectFactory;
import xyz.nonamed.gameserver.repositories.GameObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class GameObjectService {

    static Random random = new Random();

    final GameObjectRepository gameObjectRepository;

    public List<GameObject> readGameObjectListBySessionCode(String sessionCode) {
        return gameObjectRepository.readAllBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public GameObject save(GameObject gameObject) {
        return gameObjectRepository.saveAndFlush(gameObject);
    }

    public List<GameObject> save(List<GameObject> gameObjectList) {
        return gameObjectRepository.saveAllAndFlush(gameObjectList);
    }

    public GameObject addNewGameObjectToSession(GameObject gameObject, String sessionCode) {
        gameObject.setSessionCode(sessionCode);
        gameObject = save(gameObject);
        log.info("created new gameObject: '{}' // session code: '{}'", gameObject, sessionCode);
        return gameObject;
    }

    public List<GameObject> getGameObjectListBySessionCode(String sessionCode) {
        return new ArrayList<>(readGameObjectListBySessionCode(sessionCode));
    }

    public List<GameObject> generateGameObjects(World world, String sessionCode) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        GameObjectFactory gameObjectFactory = new GameObjectFactory();
        List<String> typeList = gameObjectFactory.getTypeList();

        for (int x = 0; x < world.getWidth() - 1800; x += 400) {
            for (int y = 0; y < world.getHeight() - 1000; y += 400) {
                GameObject gameObject = gameObjectFactory.create(typeList.get(random.nextInt(typeList.size())));
                if (gameObject.getWidth() > 400) {
                    x += gameObject.getWidth() - 400;
                }
                if (gameObject.getHeight() > 400) {
                    y += gameObject.getHeight() - 400;
                }
                gameObject.setPosX(x + random.nextInt(400));
                gameObject.setPosY(y + random.nextInt(400));
                gameObjects.add(addNewGameObjectToSession(gameObject, sessionCode));
            }
        }

        return gameObjects;
    }

}
