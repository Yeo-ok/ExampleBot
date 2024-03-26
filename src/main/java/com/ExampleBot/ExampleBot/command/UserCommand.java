package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserCommand implements Command {

    @Override
    public String getName() {
        return "유저명령어";
    }

    @Override
    public String getDescription() {
        return "유저를 받는 명령어의 예제입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> dataList = new ArrayList<>();
        dataList.add(
                new OptionData(OptionType.USER, "유저", "유저에 대한 설명", true));

        return dataList;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("유저명령어")) return;

        OptionMapping user = event.getOption("유저");
        var userTag = Objects.requireNonNull(user).getAsUser().getAsMention();

        event.reply(userTag + "를 받는 유저명령어 반응입니다.").queue();
    }
}
