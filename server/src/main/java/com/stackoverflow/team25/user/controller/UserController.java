package com.stackoverflow.team25.user.controller;

import com.stackoverflow.team25.dto.MultiResponseDto;
import com.stackoverflow.team25.dto.SingleResponseDto;
import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.mapper.UserMapper;
import com.stackoverflow.team25.user.service.UserService;
import com.stackoverflow.team25.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final static String USERS_DEFAULT_URL = "/api/users/";
    private final UserMapper mapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto.Post postDto) {
        User user = userService.createUser(mapper.userPostToUser(postDto));
        String accessToken = userService.delegateAccessToken(user);
        String refreshToken = userService.delegateRefreshToken(user);
        URI location = UriCreator.createUri(USERS_DEFAULT_URL, user.getUserId());

        return ResponseEntity.created(location)
                .header("Authorization", "Bearer " + accessToken)
                .header("Refresh", refreshToken)
                .build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable @Positive long userId,
                                     @Valid @RequestBody UserDto.Patch patchDto) {
        patchDto.setUserId(userId);
        User user = userService.updateUser(mapper.userPatchToUser(patchDto));
        UserDto.Response response = mapper.userToResponse(user);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable @Positive long userId) {
        User user = userService.getUser(userId);
        UserDto.Response response = mapper.userToResponse(user);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getUsers(Pageable pageable) {
        Page<User> pageUser = userService.getUsers(pageable);
        List<User> users = pageUser.getContent();
        List<UserDto.Response> responses = mapper.usersToResponses(users);

        return new ResponseEntity(new MultiResponseDto<>(responses, pageUser), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable @Positive Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
