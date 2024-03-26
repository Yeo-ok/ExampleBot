package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class UnStaff implements Command{
    @Override
    public String getName() {
        return "스태프제거";
    }

    @Override
    public String getDescription() {
        return "스태프 즉 매니저 역할 예제입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();

        //역할 탭에서 역할 ID를 복사해옵니다.
        Role role = event.getGuild().getRoleById(1221696928035635230L);

        guild.removeRoleFromMember(member, role).queue();
        event.reply("역할 삭제됨!").queue();
    }
}
