package com.example.bulbbeats;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;

public class AudioProcessor {
    private byte bytes[];
    private Visualizer mVisualizer;
    private MediaPlayer mPlayer;

    //constructor
    public AudioProcessor(ProjectSettings projSet, Context context)
    {
        if(mPlayer == null) {
            mPlayer = MediaPlayer.create(context, projSet.songUri);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    release();
                }
            });
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

    public void start()
    {
        mPlayer.start();
    }

    public  void stopPlayer()
    {
        if(mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            release();
        }
    }
}
