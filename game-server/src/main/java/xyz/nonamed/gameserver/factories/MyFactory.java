package xyz.nonamed.gameserver.factories;

import java.util.List;

public interface MyFactory<T> {

    T create(String type);

    List<String> getTypeList();

}
