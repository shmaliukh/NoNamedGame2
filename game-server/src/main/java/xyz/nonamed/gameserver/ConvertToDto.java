package xyz.nonamed.gameserver;

public interface ConvertToDto<Entity, Dto> {

    Dto toDto(Entity entity);

}
