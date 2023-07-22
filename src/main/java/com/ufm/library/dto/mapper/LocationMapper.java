package com.ufm.library.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.LocationDto;
import com.ufm.library.entity.Location;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface LocationMapper {
    LocationDto locationToLocationDto(Location reader);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "locationBooks", ignore = true)
    Location locationDtoToLocation(LocationDto readerDto);
}
