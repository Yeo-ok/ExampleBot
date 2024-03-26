package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public interface Command {

    //"/"이후 쓰일 명령어 이름
    String getName();

    //명령어에 대한 설명
    String getDescription();

    //명령어가 가지는 옵션
    List<OptionData> getOptions();

    //명령어 실행
    void execute(SlashCommandInteractionEvent event);
}
