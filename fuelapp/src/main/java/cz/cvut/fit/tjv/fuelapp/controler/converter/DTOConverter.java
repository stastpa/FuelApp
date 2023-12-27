package cz.cvut.fit.tjv.fuelapp.controler.converter;

public interface DTOConverter<DTO, Entity> {
    DTO toDTO(Entity e);

    Entity toEntity(DTO dto);
}
