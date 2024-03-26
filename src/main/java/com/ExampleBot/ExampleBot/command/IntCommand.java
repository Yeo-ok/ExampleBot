package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class IntCommand implements Command {

    @Override
    public String getName() {
        return "정수명령어";
    }

    @Override
    public String getDescription() {
        return "정수만을 받는 명령어의 예제입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> dataList = new ArrayList<>();

        dataList.add(
                new OptionData(OptionType.INTEGER, "숫자1", "숫자1에 대한 설명", true)
                        .setMinValue(1)
                        .setMaxValue(10000));

        dataList.add(new OptionData(OptionType.INTEGER, "숫자2", "숫자2에 대한 설명", true)
                .setMinValue(1)
                .setMaxValue(10000));

        return dataList;
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("정수명령어")) return;

        OptionMapping number1 = event.getOption("숫자1");
        int num1 = number1.getAsInt();
        OptionMapping number2 = event.getOption("숫자2");
        int num2 = number2.getAsInt();

        event.reply("숫자1은 \n```cs\n" + num1 + "``` 숫자2는 \n```cs\n" + num2 + "``` 를(을) 받는 정수명령어 반응입니다.").queue();
    }
}
