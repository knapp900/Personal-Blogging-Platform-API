package by.ak.personal_blogging_platform_api.service.mapper;

public interface Mapper<E,D> {
	
	E toEntity(D dtoElement);
	D toDto (E entity);
}
