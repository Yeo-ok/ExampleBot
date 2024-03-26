package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


import java.util.ArrayList;
import java.util.List;


public class ChannelCommand implements Command {

    @Override
    public String getName() {
        return "채널명령어";
    }

    @Override
    public String getDescription() {
        return "채널을 받는 명령어의 예제입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> dataList = new ArrayList<>();
        dataList.add(
                new OptionData(OptionType.CHANNEL, "채널", "채널에 대한 설명", true));
        return dataList;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("채널명령어")) return;

        OptionMapping channel = event.getOption("채널");
        String channelTag = channel.getAsChannel().getJumpUrl();

        event.reply(channelTag + "를 받는 채널명령어 반응입니다.").queue();
    }
}
