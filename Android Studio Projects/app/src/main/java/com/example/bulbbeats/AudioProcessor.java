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
    private fftListener listener;

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
                FFT = new float[numBins];
                updateFFT(fft);
                if(listener == null)
                    return;
                listener.onUpdate(FFT);
            }
        };

        //this sets up the listener but does not actually enable the visualizer. That happens back in launch.
        mVisualizer.setDataCaptureListener(captureListener,
                20000, false, true);
    }

    public void setfftListener(fftListener listener) {
        this.listener = listener;
    }

    public void updateFFT(byte[] fft)
    {
        /*int n = fft.length;
        int[] magnitudes = new int[n / 2 + 1];
        magnitudes[0] = Math.abs(fft[0]);
        magnitudes[n / 2] = Math.abs(fft[1]);

        for(int k = 1; k < n / 2; k++){
            int x = k * 2;
            magnitudes[k] = (int)Math.log10((float)Math.hypot(fft[x], fft[x + 1])) * 40;
        }

        for (int i = 1; i <= numBins; i++) {
            float avg = 0;

            for(int j = 0; j < (magnitudes.length-1) / numBins; j++)
                 avg += magnitudes[j*i+1];

            avg /= numBins;
            FFT[i-1] = avg;
        }*/

        int numBuckets = fft.length/2;
        int binSize = numBuckets / numBins;

        for(int j = 0; j < numBins; j++){
            int avg = 0;
            int offset = j*binSize;
            for (int i = 0; i < binSize; i++) {
                byte rfk = fft[offset + i];
                byte ifk = fft[offset + i + 1];
                avg += (int) Math.log10(rfk * rfk + ifk * ifk)*4;
            }
            FFT[j] = (float)avg / binSize;
            FFT[j] = (float)Math.pow(FFT[j],4)/64;
        }


        //roundtrip time
        date2 = new Date();
        long capture = date2.getTime() - date1.getTime();
        Log.v("LaunchActivity.onUpdate", String.format("%6.1f: %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f %6.1f",
                (float)capture,FFT[0], FFT[1], FFT[2], FFT[3],FFT[4], FFT[5], FFT[6], FFT[7],FFT[8], FFT[9], FFT[10], FFT[11],FFT[12], FFT[13], FFT[14], FFT[15]));
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
