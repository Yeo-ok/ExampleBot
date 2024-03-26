package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ban implements Command{
    @Override
    public String getName() {
        return "추방";
    }

    @Override
    public String getDescription() {
        return "추방의 예시입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "추방",  "유저 추방", true));
        optionData.add(new OptionData(OptionType.INTEGER, "길이", "추방 길이", true));
        optionData.add(new OptionData(OptionType.STRING, "이유", "추방 사유", false));
        return optionData;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if(member.hasPermission(Permission.BAN_MEMBERS)){
            Member banned = event.getOption("추방").getAsMember();
            int length = event.getOption("길이").getAsInt();
            OptionMapping reason = event.getOption("이유");
            if(reason == null){
                banned.ban(length, TimeUnit.DAYS).queue();
            }else {
                banned.ban(length, TimeUnit.DAYS).reason(reason.getAsString()).queue();
            }

        } else {
            event.reply("당신은 이 명령어를 실행시킬 충분한 권한을 가지고 있지 않습니다.").queue();
        }
    }
}
