package xyz.nonamed.factories;

import xyz.nonamed.dto.GameObject;

import java.util.ArrayList;
import java.util.List;

import static xyz.nonamed.dto.GameObject.*;

public class GameObjectFactory implements MyFactory<GameObject> {

    public GameObject create(String type) {
        return switch (type) {
            case GAME_OBJECT_1 -> createGameObject1();
            case GAME_OBJECT_2 -> createGameObject2();
            case GAME_OBJECT_3 -> createGameObject3();
            case GAME_OBJECT_4 -> createGameObject4();
            case GAME_OBJECT_5 -> createGameObject5();
            case GAME_OBJECT_6 -> createGameObject6();
            case GAME_OBJECT_7 -> createGameObject7();
            case GAME_OBJECT_8 -> createGameObject8();
            default -> createGameObject1();
        };
    }

    private GameObject createGameObject1() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_1;
        gameObject.width = 1111;
        gameObject.height = 1033;
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
        gameObject.width = 832;
        gameObject.height = 589;
        gameObject.collisionPosX = 130;
        gameObject.collisionPosY = 90;
        gameObject.collisionHeight = 310;
        gameObject.collisionWidth = 588;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject3() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_3;
        gameObject.width = 154;
        gameObject.height = 162;
        gameObject.collisionPosX = 10;
        gameObject.collisionPosY = 60;
        gameObject.collisionHeight = 60;
        gameObject.collisionWidth = 120;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject4() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_4;
        gameObject.width = 154;
        gameObject.height = 162;
        gameObject.collisionPosX = 17;
        gameObject.collisionPosY = 60;
        gameObject.collisionHeight = 60;
        gameObject.collisionWidth = 120;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject5() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_5;
        gameObject.width = 199;
        gameObject.height = 174;
        gameObject.collisionPosX = 70;
        gameObject.collisionPosY = 40;
        gameObject.collisionWidth = 90;
        gameObject.collisionHeight = 60;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject6() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_6;
        gameObject.width = 199;
        gameObject.height = 174;
        gameObject.collisionPosX = 40;
        gameObject.collisionPosY = 40;
        gameObject.collisionWidth = 90;
        gameObject.collisionHeight = 60;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject7() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_7;
        gameObject.width = 940;
        gameObject.height = 585;
        gameObject.collisionPosX = 70;
        gameObject.collisionPosY = 90;
        gameObject.collisionWidth = 770 ;
        gameObject.collisionHeight = 310;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject8() {
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_8;
        gameObject.width = 764;
        gameObject.height = 577;
        gameObject.collisionPosX = 17;
        gameObject.collisionPosY = 80;
        gameObject.collisionWidth = 673 ;
        gameObject.collisionHeight = 380;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }


    @Override
    public List<String> getTypeList() {
        return new ArrayList<>(GameObject.gameObjectTypeList);
    }

}
