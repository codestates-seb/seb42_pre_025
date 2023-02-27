package com.stackoverflow.team25.user.mapper;

import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T21:00:43+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userPostToUser(UserDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.password( postDto.getPassword() );
        user.email( postDto.getEmail() );
        user.displayName( postDto.getDisplayName() );

        return user.build();
    }

    @Override
    public UserDto.Response userToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.Response.ResponseBuilder response = UserDto.Response.builder();

        response.userId( user.getUserId() );
        response.email( user.getEmail() );
        response.displayName( user.getDisplayName() );
        response.aboutMe( user.getAboutMe() );
        response.acceptRate( user.getAcceptRate() );
        response.userStatus( user.getUserStatus() );

        return response.build();
    }

    @Override
    public User userPatchToUser(UserDto.Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( patchDto.getUserId() );
        user.displayName( patchDto.getDisplayName() );
        user.aboutMe( patchDto.getAboutMe() );

        return user.build();
    }

    @Override
    public List<UserDto.Response> usersToResponses(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto.Response> list = new ArrayList<UserDto.Response>( users.size() );
        for ( User user : users ) {
            list.add( userToResponse( user ) );
        }

        return list;
    }
}
