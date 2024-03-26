package com.ExampleBot.ExampleBot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {

    private TrackSchedular trackSchedular;
    private AudioForward audioForward;

    public GuildMusicManager(AudioPlayerManager manager){
        AudioPlayer player = manager.createPlayer();
        trackSchedular = new TrackSchedular(player);
        player.addListener(trackSchedular);
        audioForward = new AudioForward(player);
    }

    public TrackSchedular getTrackSchedular() {
        return trackSchedular;
    }

    public AudioForward getAudioForward() {
        return audioForward;
    }
}
