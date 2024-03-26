package com.ExampleBot.ExampleBot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackSchedular extends AudioEventAdapter {

    @Getter
    private final AudioPlayer audioPlayer;
    @Getter
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();
    private boolean isRepeat = false;

    public TrackSchedular(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(isRepeat){
            player.startTrack(track.makeClone(),false);
        }else {
            player.startTrack(queue.poll(), false);
        }



    }

    public void queue(AudioTrack track){
        if(!audioPlayer.startTrack(track, true)){
            queue.offer(track);
        }
    }

    public boolean isRepeat(){
        return isRepeat;
    }

    public void setRepeat(boolean repeat){
        isRepeat = repeat;
    }
}
