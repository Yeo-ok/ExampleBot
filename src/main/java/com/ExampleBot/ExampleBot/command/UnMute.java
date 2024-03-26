package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class UnMute implements Command{
    @Override
    public String getName() {
        return "음소거해제";
    }

    @Override
    public String getDescription() {
        return "음소거 해제 예시입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "음소거해제", "이 유저는 음소거 해제 됩니다.", true));
        return optionData;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        Role role = guild.getRoleById(1221696928035635230L);
        if(member.getRoles().contains(role)){
            Member muteMember = event.getOption("음소거").getAsMember();

            //각각 역할 ID를 넣습니다.
            Role muteRole = guild.getRoleById(1221708270864044032L);
            Role defaultRole = guild.getRoleById(1221708679607488533L);
            guild.removeRoleFromMember(muteMember, muteRole).queue();
            guild.addRoleToMember(muteMember, defaultRole).queue();
        } else {
            event.reply("당신은 이 명령어를 실행시킬 충분한 권한을 가지고 있지 않습니다.").queue();
        }
    }
}
