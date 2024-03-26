package com.ExampleBot.ExampleBot.lavaplayer.music;

import com.ExampleBot.ExampleBot.command.Command;
import com.ExampleBot.ExampleBot.lavaplayer.GuildMusicManager;
import com.ExampleBot.ExampleBot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class NowPlaying implements Command {
    @Override
    public String getName() {
        return "노래정보";
    }

    @Override
    public String getDescription() {
        return "지금 현재 곡을 보여줍니다.";
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
        if(guildMusicManager.getTrackSchedular().getAudioPlayer().getPlayingTrack() == null){
            event.reply("아무 것도 재생되고 있지 않습니다.").queue();
            return;
        }
        AudioTrackInfo audioTrackInfo = guildMusicManager.getTrackSchedular().getAudioPlayer().getPlayingTrack().getInfo();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("현재 재생중");
        embedBuilder.setDescription("**이름** : `" + audioTrackInfo.title + "`");
        embedBuilder.appendDescription("\n**채널명** : `" + audioTrackInfo.author + "`");
        embedBuilder.appendDescription("\n**주소** : `" + audioTrackInfo.uri + "`");
        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
