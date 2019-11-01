package com.example.bulbbeats;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.Log;

import java.util.Date;

public class AudioProcessor{
    private float FFT[];
    private Visualizer mVisualizer;
    private static int numBins = 16;
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
                FFT = new float[fft.length];
                updateFFT(fft);
            }
        };

        //this sets up the listener but does not actually enable the visualizer. That happens back in launch.
        mVisualizer.setDataCaptureListener(captureListener,
                20000, false, true);
    }

    public void updateFFT(byte[] fft)
    {
        date2 = new Date();

        for (int i = 0; i < FFT.length / numBins; i++)
        {
            FFT[i] = 0;
            byte rfk = fft[numBins * i];
            byte ifk = fft[numBins * i + 1];
            FFT[i] = (int) Math.log10(rfk * rfk + ifk *ifk) * 4;
        }

        long capture = date2.getTime() - date1.getTime();
        Log.d("Updating FFT", String.format("%d ms -- %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f",
                capture, FFT[0], FFT[1], FFT[2], FFT[3],FFT[4], FFT[5], FFT[6], FFT[7],FFT[8], FFT[9], FFT[10], FFT[11],FFT[12], FFT[13], FFT[14], FFT[15]));
        date1 = new Date();
    }

    public void release()
    {
        mVisualizer.release();
    }

    public float[] getBytesFFT()
    {
        return FFT;
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
