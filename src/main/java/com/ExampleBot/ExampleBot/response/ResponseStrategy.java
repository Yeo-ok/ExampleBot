package com.ExampleBot.ExampleBot.response;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public interface ResponseStrategy {
    void respond(User user, Message message, TextChannel textChannel, String chat, String answer);
}

