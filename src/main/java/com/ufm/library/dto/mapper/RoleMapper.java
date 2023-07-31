package com.ufm.library.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.RoleDto;
import com.ufm.library.entity.Role;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface RoleMapper {
    @Mapping(target = "code", source = "roleCode")
    @Mapping(target = "name", source = "roleName")
    public RoleDto roleToRoleDto(Role role);

    @Mapping(source = "code", target = "roleCode")
    @Mapping(source = "name", target = "roleName")
    @Mapping(target = "librarians", ignore = true)
    public Role roleDtoToRole(RoleDto roleDto);
}
