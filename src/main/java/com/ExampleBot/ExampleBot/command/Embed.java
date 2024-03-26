package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.List;

public class Embed implements Command{

    @Override
    public String getName() {
        return "임베드";
    }

    @Override
    public String getDescription() {
        return "임베드를 보내겠습니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("예제 임베드");
        builder.setDescription("예제 임베드 내용");
        builder.addField("필드 1", "필드 1 값", false);
        builder.addField("필드 2", "필드 2 값", true);
        builder.addField("필드 3", "필드 3 값", true);
        builder.setFooter("예제 바닥글");
        builder.setColor(Color.YELLOW);
        builder.appendDescription(" + 내용에 글 추가 하는곳 입니다.");
        builder.setAuthor("예제 작성자");
        event.replyEmbeds(builder.build()).queue();
    }
}
