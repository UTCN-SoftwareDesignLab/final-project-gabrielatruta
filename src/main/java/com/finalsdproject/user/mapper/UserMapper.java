package com.finalsdproject.user.mapper;

import com.finalsdproject.user.dto.UserDTO;
import com.finalsdproject.user.dto.UserListDTO;
import com.finalsdproject.user.dto.UserMinimalDTO;
import com.finalsdproject.user.model.User;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User fromDTO(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "name", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "name", source = "user.username"),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "roles", ignore = true)

    })
    UserListDTO userListDtoFromUser(User user);

    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {
        userListDTO.setRoles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }
}
