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
    private int PERMISSION_CODE = 1;
    //constructor
    public AudioProcessor(MediaPlayer mPlayer, Context context)
    {
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
                System.out.println("Audproc is capturing");
            }
        };

        mVisualizer.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate() / 2, false, true);
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
    public Boolean isNull(){return mPlayer == null;}

    public void disable()
    {
        mVisualizer.setEnabled(false);
    }


}
