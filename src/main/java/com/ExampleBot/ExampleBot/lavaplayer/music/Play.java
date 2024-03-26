package com.ExampleBot.ExampleBot.lavaplayer.music;

import com.ExampleBot.ExampleBot.command.Command;
import com.ExampleBot.ExampleBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Play implements Command {
    @Override
    public String getName() {
        return "불러줘";
    }

    @Override
    public String getDescription() {
        return "노래를 시작하겠습니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "name", "부를 노래"));
        return options;
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
            event.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        } else{
            if(selfVoiceState.getChannel() != memberVoiceState.getChannel()){
                event.reply("당신이 같은 음성채널에 접속해 있어야합니다.").queue();
                return;
            }
        }

        String name = event.getOption("name").getAsString();

        if(!name.contains("https:")){
            name = "ytsearch:" + name;

        }else if(name.contains("https:")){
            try {
                new URI(name);
            } catch (URISyntaxException e) {
                name = "ytsearch:" + name;
            }
        }


        PlayerManager playerManager = PlayerManager.get();
        event.reply("재생중").queue();
        playerManager.play(event.getGuild(), name);



    }
}
