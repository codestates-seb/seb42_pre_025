package com.stackoverflow.team25.user.mapper;

import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userPostToUser(UserDto.Post postDto);

    UserDto.Response userToResponse(User user);

    User userPatchToUser(UserDto.Patch patchDto);

    List<UserDto.Response> usersToResponses(List<User> users);
}
