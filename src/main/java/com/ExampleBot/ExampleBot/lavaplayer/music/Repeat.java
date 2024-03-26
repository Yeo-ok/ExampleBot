package com.ExampleBot.ExampleBot.lavaplayer.music;

import com.ExampleBot.ExampleBot.command.Command;
import com.ExampleBot.ExampleBot.lavaplayer.GuildMusicManager;
import com.ExampleBot.ExampleBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Repeat implements Command {
    @Override
    public String getName() {
        return "반복";
    }

    @Override
    public String getDescription() {
        return "반복을 보여줍니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()){
            event.reply("음성 채널에 당신이 들어가있어야합니다.").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()){
            event.reply("저는 음성채널에 없습니다.").queue();
            return;
        }

        if(selfVoiceState.getChannel() != memberVoiceState.getChannel()){
            event.reply("같은 채널에 있지 않은거 같아요.").queue();
            return;
        }

        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        boolean isRepeat = !guildMusicManager.getTrackSchedular().isRepeat();
        guildMusicManager.getTrackSchedular().setRepeat(isRepeat);
        event.reply("반복 재생" + isRepeat).queue();

    }
}
