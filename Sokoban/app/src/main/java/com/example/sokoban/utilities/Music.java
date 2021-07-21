package com.example.sokoban.utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.sokoban.R;

public class Music {

    public boolean playing = false;

    private MediaPlayer myMusicPlayer;

    public Music() {
    }
    public void createMusicPlayer(Context context){ // from tutorial linkedIn
        myMusicPlayer = MediaPlayer.create(context.getApplicationContext(),
                R.raw.puzzle_music);
        myMusicPlayer.setVolume(.4f, .4f);
        myMusicPlayer.setLooping(true);

    }
    public void startMusic(){
        if (myMusicPlayer != null){
            myMusicPlayer.start();
            this.playing = true;

        }
    }
    public void pauseMusic(){
        if (myMusicPlayer != null && myMusicPlayer.isPlaying()){
            myMusicPlayer.pause();
            this.playing = false;
        }
    }
}
