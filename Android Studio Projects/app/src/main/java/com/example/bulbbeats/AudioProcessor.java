package com.example.bulbbeats;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AudioProcessor {
    private byte bytes[];
    private Visualizer mVisualizer;
    private MediaPlayer mPlayer;
    private int PERMISSION_CODE = 1;
    //constructor
    public AudioProcessor(ProjectSettings projSet, Context context)
    {
        if(mPlayer == null) {
            mPlayer = MediaPlayer.create(context, projSet.songUri);
           /* mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    release();
                }
            });*/
        }

        int id = mPlayer.getAudioSessionId();

        mVisualizer = new Visualizer(id);
        mVisualizer.setEnabled(false);
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
                //nothing because we only want fft
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
                bytes = fft;
            }
        };
    }

    public byte[] getBytesFFT()
    {
        return bytes;
    }

    public void release()
    {
        if(mPlayer != null) {
            mPlayer.pause();
        }
        mVisualizer.release();
    }
    public Boolean isPlaying(){
        return mPlayer.isPlaying();
    }
    public void start()
    {
        mPlayer.start();
    }
    public Boolean isNull(){return mPlayer == null;}

    public  void stopPlayer()
    {
        if(mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            release();
        }
    }
    public MediaPlayer getPlayer(){
        return mPlayer;
    }
}
