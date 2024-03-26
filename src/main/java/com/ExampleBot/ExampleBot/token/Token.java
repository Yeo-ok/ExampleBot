package com.ExampleBot.ExampleBot.token;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Token {
    @Value("${discord.bot.token}")
    private String botToken;

}
