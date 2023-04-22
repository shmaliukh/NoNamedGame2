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

        double prevX = 0;
        double prevY = 0;
        double maxX = 1200;
        double maxY = 1200;
        for (int x = 0; x < world.getWidth(); x += 1200) {
            for (int y = 0; y < world.getHeight(); y += 1200) {
                if (x >= 0 && x < 1000
                        && y >= 0 && y < 1000) {
                    // TODO hero spawn
                } else {
//                while (prevY < 1200){
                    if (random.nextInt(100) > 33) {
                        GameObject gameObject = gameObjectFactory.create(typeList.get(random.nextInt(typeList.size())));

                        gameObject.setPosX(x);
                        gameObject.setPosY(y);
//                        y += gameObject.getHeight();
//                        prevY = y;

                        gameObjects.add(addNewGameObjectToSession(gameObject, sessionCode));
//                        log.info("generated game object: '{}'", gameObject);
//                    }
                    }
                }

            }
        }
        return gameObjects;
    }

}
