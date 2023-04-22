package xyz.nonamed.factories;

import xyz.nonamed.dto.GameObject;

import java.util.ArrayList;
import java.util.List;

import static xyz.nonamed.dto.GameObject.GAME_OBJECT_1;
import static xyz.nonamed.dto.GameObject.GAME_OBJECT_2;

public class GameObjectFactory implements MyFactory<GameObject> {

    public GameObject create(String type) {
        return switch (type) {
            case GAME_OBJECT_1 -> createGameObject1();
            case GAME_OBJECT_2 -> createGameObject2();
            default -> createGameObject1();
        };
    }

    private GameObject createGameObject1() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_1;
        gameObject.collisionPosX = 130;
        gameObject.collisionPosY = 140;
        gameObject.collisionHeight = 560;
        gameObject.collisionWidth = 750;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject2() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_2;
        gameObject.collisionPosX = 130;
        gameObject.collisionPosY = 90;
        gameObject.collisionHeight = 310;
        gameObject.collisionWidth = 588;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    @Override
    public List<String> getTypeList() {
        return new ArrayList<>(GameObject.gameObjectTypeList);
    }

}
