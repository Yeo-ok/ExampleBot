package com.ExampleBot.ExampleBot.lavaplayer.music;

import com.ExampleBot.ExampleBot.command.Command;
import com.ExampleBot.ExampleBot.lavaplayer.GuildMusicManager;
import com.ExampleBot.ExampleBot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Queue implements Command {
    @Override
    public String getName() {
        return "노래목록";
    }

    @Override
    public String getDescription() {
        return "큐 를 보여줍니다.";
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
        List<AudioTrack> queue = new ArrayList<>(guildMusicManager.getTrackSchedular().getQueue());
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("현재 대기중");
        if(queue.isEmpty()){
            embedBuilder.setDescription("다음 곡이 없습니다.");
        }
        for(int i = 0; i < queue.size(); i++){
            AudioTrackInfo audioTrackInfo = queue.get(i).getInfo();
            embedBuilder.addField(i+1 + ":", audioTrackInfo.title, false);
        }
        event.replyEmbeds(embedBuilder.build()).queue();


    }
}
