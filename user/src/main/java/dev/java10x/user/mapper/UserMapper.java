package dev.java10x.user.mapper;

import dev.java10x.user.dto.UserDTO;
import dev.java10x.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toModel(UserDTO userDTO) {

        UserModel user = new UserModel();
        user.setUserId(userDTO.userId());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        return user;
    }

    public UserDTO toDto(UserModel user) {

        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );

    }



}
