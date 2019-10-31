package com.example.bulbbeats;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;

import java.util.Date;

public class AudioProcessor{
    private byte bytes[];
    private Visualizer mVisualizer;
    private int PERMISSION_CODE = 1;

    Date date1 = new Date();
    Date date2 = null;

    //constructor
    public AudioProcessor(MediaPlayer mPlayer, Context context)
    {
        mVisualizer = new Visualizer(mPlayer.getAudioSessionId());
        mVisualizer.setEnabled(false);
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
                //nothing because we only want fft
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
                updateFFT(fft);
                System.out.println("audProc is capturing");
            }
        };

        //this sets up the listener but does not actually enable the visualizer. That happens back in launch.
        mVisualizer.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate() / 2, false, true);
    }

    public void updateFFT(byte[] fft)
    {
        bytes = fft;
    }

    public void release()
    {
        mVisualizer.release();
    }

    public byte[] getBytesFFT()
    {
        return bytes;
    }

    public void enable()
    {
        mVisualizer.setEnabled(true);
    }

    public void disable()
    {
        mVisualizer.setEnabled(false);
    }


}
