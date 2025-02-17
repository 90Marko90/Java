package com.example.tenisuj.Mapper;
import com.example.tenisuj.model.User;
import com.example.tenisuj.model.dto.SignUpDto;
import com.example.tenisuj.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user); //maps User to UserDto, This code is useful when you need to convert between different layers of your application, like from entities to DTOs and vice versa.

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}