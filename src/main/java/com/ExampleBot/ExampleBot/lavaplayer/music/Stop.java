package com.ExampleBot.ExampleBot.lavaplayer.music;

import com.ExampleBot.ExampleBot.command.Command;
import com.ExampleBot.ExampleBot.lavaplayer.GuildMusicManager;
import com.ExampleBot.ExampleBot.lavaplayer.PlayerManager;
import com.ExampleBot.ExampleBot.lavaplayer.TrackSchedular;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Stop implements Command {
    @Override
    public String getName() {
        return "멈춰";
    }

    @Override
    public String getDescription() {
        return "곡을 중지하겠습니다.";
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
        TrackSchedular trackSchedular = guildMusicManager.getTrackSchedular();
        trackSchedular.getQueue().clear();
        trackSchedular.getAudioPlayer().stopTrack();
        event.reply("재생이 중지되었습니다.").queue();
    }
}
