package com.ExampleBot.ExampleBot.response;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import static com.ExampleBot.ExampleBot.response.Dialog.*;

@Slf4j
public class Response extends ListenerAdapter {
    private ResponseStrategy responseStrategy;

    public void setResponseStrategy(ResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        Message message = event.getMessage();
        TextChannel textChannel = event.getChannel().asTextChannel();

        log.info("입력된 메세지 : " + message.getContentDisplay());


        //setResponseStrategy을 통해서 어떤 응답을 받을지 정한다.
        setResponseStrategy(new DefaultResponseStrategy());

        //Dialog enum에 담겨 있는 대화와 그에 대한 답변을 가져온다.
        responseStrategy.respond(user, message, textChannel, HELLO.getValue(), HELLO_ANSWER.getValue());
        responseStrategy.respond(user, message, textChannel, COMMAND.getValue(), COMMAND_ANSWER.getValue());

        setResponseStrategy(new MentionResponseStrategy());
        responseStrategy.respond(user, message, textChannel, MENTION.getValue(), MENTION_ANSWER.getValue());


    }
}
