package com.example.tenisuj.Mapper;
import com.example.tenisuj.model.User;
import com.example.tenisuj.model.dto.SignUpDto;
import com.example.tenisuj.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}