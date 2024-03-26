package com.ExampleBot.ExampleBot.response;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;


//전략 패턴을 이용해보았다.
public class DefaultResponseStrategy implements ResponseStrategy {
    @Override
    public void respond(User user, Message message, TextChannel textChannel, String chat, String answer) {
        if (user.isBot()) {
            return;
        }
        if (message.getContentRaw().equals(chat)) {
            textChannel.sendMessage(answer).queue();
        }
    }
}


class FormatResponseStrategy implements ResponseStrategy {
    @Override
    public void respond(User user, Message message, TextChannel textChannel, String chat, String answer) {
        if (user.isBot()) {
            return;
        }
        if (message.getContentDisplay().equals(chat)) {
            textChannel.sendMessage(answer).queue();
        }
    }
}

class UserResponseStrategy implements ResponseStrategy {
    @Override
    public void respond(User user, Message message, TextChannel textChannel, String chat, String answer) {
        if (user.isBot()) {
            return;
        }
        if (message.getContentRaw().equals(chat)) {
            textChannel.sendMessage(user.getName() + answer).queue();
        }
    }
}

class MentionResponseStrategy implements ResponseStrategy {
    @Override
    public void respond(User user, Message message, TextChannel textChannel, String chat, String answer) {
        if (user.isBot()) {
            return;
        }
        if (message.getContentRaw().equals(chat)) {
            textChannel.sendMessage(user.getAsMention() + answer).queue();
        }
    }
}
