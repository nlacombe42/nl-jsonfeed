package net.nlacombe.jsonfeedlib.impl.mapper;

import java.util.List;

public interface BeanMapper<DtoType, DomainType> {

    DtoType mapToDto(DomainType domainObject);

    List<DtoType> mapToDtos(List<DomainType> domainObjects);

    DomainType mapToDomainObject(DtoType dto);

    List<DomainType> mapToDomainObjects(List<DtoType> dtos);

}
