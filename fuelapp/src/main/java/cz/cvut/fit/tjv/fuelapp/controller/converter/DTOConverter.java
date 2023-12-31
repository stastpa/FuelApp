package cz.cvut.fit.tjv.fuelapp.controller.converter;

public interface DTOConverter<DTO, Entity> {
    DTO toDTO(Entity e);

    Entity toEntity(DTO dto);
}
