package com.stackoverflow.team25.mail.event;

import com.stackoverflow.team25.mail.sender.EmailSender;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Configuration
@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationEventListener {
    @Value("${mail.subject.user.registration}")
    private String subject;
    @Value("${mail.template.name.user.join}")
    private String templateName;
    private final EmailSender emailSender;
    private final UserService userService;

    @Async
    @EventListener
    public void Listen(UserRegistrationApplicationEvnet event) throws Exception {
        User user = event.getUser();
        try {
            String[] to = new String[]{user.getEmail()};
            String message = user.getEmail() + "님, 회원 가입이 성공적으로 완료되었습니다.";
            emailSender.send(to, subject, message, templateName);
        } catch (MailSendException e) {
            e.printStackTrace();
            log.error("MailSendException: rollback for User Registration!");
            userService.deleteUser(user.getUserId());
        }
    }
}
