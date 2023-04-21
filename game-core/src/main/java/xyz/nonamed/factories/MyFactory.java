package xyz.nonamed.factories;

import java.util.List;

public interface MyFactory<T> {

    T create(String type);

    List<String> getTypeList();

}
