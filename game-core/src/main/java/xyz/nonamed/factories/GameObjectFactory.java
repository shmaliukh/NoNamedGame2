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
            case GAME_OBJECT_9 -> createGameObject9();
            case GAME_OBJECT_10 -> createGameObject10();
            case GAME_OBJECT_11 -> createGameObject11();
            case GAME_OBJECT_12 -> createGameObject12();
            case GAME_OBJECT_13 -> createGameObject13();
            case GAME_OBJECT_14 -> createGameObject14();
            case GAME_OBJECT_15 -> createGameObject15();
            case GAME_OBJECT_16 -> createGameObject16();
            case GAME_OBJECT_17 -> createGameObject17();
            case GAME_OBJECT_18 -> createGameObject18();
            case GAME_OBJECT_19 -> createGameObject19();
            case GAME_OBJECT_20 -> createGameObject20();
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

    private GameObject createGameObject9(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_9;
        gameObject.width = 291;
        gameObject.height = 533;
        gameObject.collisionPosX = 17;
        gameObject.collisionPosY = 80;
        gameObject.collisionWidth = 200 ;
        gameObject.collisionHeight = 500;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject10(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_10;
        gameObject.width = 291;
        gameObject.height = 533;
        gameObject.collisionPosX = 17;
        gameObject.collisionPosY = 80;
        gameObject.collisionWidth = 200 ;
        gameObject.collisionHeight = 500;
        gameObject.isCollision = false;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject11(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_11;
        gameObject.width = 291;
        gameObject.height = 533;
        gameObject.collisionPosX = 17;
        gameObject.collisionPosY = 80;
        gameObject.collisionWidth = 200 ;
        gameObject.collisionHeight = 500;
        gameObject.isCollision = false;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject12(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_12;
        gameObject.width = 232;
        gameObject.height = 151;
        gameObject.collisionPosX = 20;
        gameObject.collisionPosY = 15;
        gameObject.collisionWidth = 200;
        gameObject.collisionHeight = 150;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject13(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_13;
        gameObject.width = 232;
        gameObject.height = 151;
        gameObject.collisionPosX = 17;
        gameObject.collisionPosY = 30;
        gameObject.collisionWidth = 200 ;
        gameObject.collisionHeight = 150;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject14(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_14;
        gameObject.width = 52;
        gameObject.height = 81;
        gameObject.collisionPosX = 5;
        gameObject.collisionPosY = 30;
        gameObject.collisionWidth = 50 ;
        gameObject.collisionHeight = 80;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject15(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_15;
        gameObject.width = 52;
        gameObject.height = 81;
        gameObject.collisionPosX = 5;
        gameObject.collisionPosY = 30;
        gameObject.collisionWidth = 50 ;
        gameObject.collisionHeight = 80;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject16(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_16;
        gameObject.width = 85;
        gameObject.height = 85;
        gameObject.collisionPosX = 8;
        gameObject.collisionPosY = 10;
        gameObject.collisionWidth = 60 ;
        gameObject.collisionHeight = 60;
        gameObject.isCollision = false;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject17(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_17;
        gameObject.width = 75;
        gameObject.height = 75;
        gameObject.collisionPosX = 7;
        gameObject.collisionPosY = 11;
        gameObject.collisionWidth = 50 ;
        gameObject.collisionHeight =50;
        gameObject.isCollision = false;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject18(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_18;
        gameObject.width = 75;
        gameObject.height = 75;
        gameObject.collisionPosX = 7;
        gameObject.collisionPosY = 11;
        gameObject.collisionWidth = 50 ;
        gameObject.collisionHeight =50;
        gameObject.isCollision = false;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject19(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_19;
        gameObject.width = 173;
        gameObject.height = 112;
        gameObject.collisionPosX = 150;
        gameObject.collisionPosY = 90;
        gameObject.collisionWidth = 150 ;
        gameObject.collisionHeight = 90;
        gameObject.isCollision = false;
        gameObject.color = "WHITE";
        return gameObject;
    }

    private GameObject createGameObject20(){
        GameObject gameObject = new GameObject();
        gameObject.type = GAME_OBJECT_20;
        gameObject.width =232;
        gameObject.height = 274;
        gameObject.collisionPosX = 15;
        gameObject.collisionPosY = 9;
        gameObject.collisionWidth = 210 ;
        gameObject.collisionHeight = 220;
        gameObject.isCollision = true;
        gameObject.color = "WHITE";
        return gameObject;
    }



    @Override
    public List<String> getTypeList() {
        return new ArrayList<>(GameObject.gameObjectTypeList);
    }

}
