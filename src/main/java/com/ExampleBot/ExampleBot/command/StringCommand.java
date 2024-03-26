package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class StringCommand implements Command {

    @Override
    public String getName() {
        return "문자열명령어";
    }

    @Override
    public String getDescription() {
        return "문자열을 받는 명령어의 예제입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> dataList = new ArrayList<>();
        dataList.add(
                new OptionData(OptionType.STRING, "문자열1", "문자열 1에 대한 설명", true));
        dataList.add(
                new OptionData(OptionType.STRING, "문자열2", "문자열 2에 대한 설명", true));

        return dataList;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("문자열명령어")) return;

        OptionMapping string1 = event.getOption("문자열1");
        String str1 = string1.getAsString();
        OptionMapping string2 = event.getOption("문자열2");
        String str2=  string2.getAsString();

        event.reply("문자열1은 \n```cs\n" + str1 + "``` 문자열2는 \n```cs\n" + str2 + "``` 를(을) 받는 문자열명령어 반응입니다.").queue();
    }
}
