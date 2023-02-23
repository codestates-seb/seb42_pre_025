package com.stackoverflow.team25.mail.event;

import com.stackoverflow.team25.user.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegistrationApplicationEvnet extends ApplicationEvent {
    User user;

    public UserRegistrationApplicationEvnet(Object source, User user) {
        super(source);
        this.user = user;
    }
}
