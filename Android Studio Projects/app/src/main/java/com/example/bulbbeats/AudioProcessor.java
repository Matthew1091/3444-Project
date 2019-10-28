package com.example.bulbbeats;

import android.media.audiofx.Visualizer;

public class AudioProcessor {
    private byte bytes[];
    private Visualizer mVisualizer;

    //constructor
    public AudioProcessor(int audioSessionID)
    {
        mVisualizer = new Visualizer(audioSessionID);
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
        mVisualizer.release();
    }
}
