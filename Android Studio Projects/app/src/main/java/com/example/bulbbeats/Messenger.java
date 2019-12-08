package com.example.bulbbeats;
import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Messenger {
    int maxBin=-1;
    int minBin=100;
    float maxWave=-1;
    int minWave=-1;
    PHHueSDK hue;
    private static final int MAX_HUE=65535;
    float[] fft;
    public Messenger(PHHueSDK hue){
        this.hue = hue;
        //start the HueSDK. Bridge should already be connected.
        hue = PHHueSDK.create();


        PHBridge bridge = hue.getSelectedBridge();
        if((bridge != null)  ) {
            List<PHLight> allLights = bridge.getResourceCache().getAllLights();
            Random rand = new Random();

            for (PHLight light : allLights) {
                PHLightState lightState = new PHLightState();
                lightState.setHue(rand.nextInt(MAX_HUE));
                // To validate your lightstate is valid (before sending to the bridge) you can use:
                // String validState = lightState.validateState();
                bridge.updateLightState(light, lightState, listener);
                //  bridge.updateLightState(light, lightState);   // If no bridge response is required then use this simpler form.
            }
        }
    }

    public void changeLights(float[] fft){
        this.fft = fft;
        int localMaxBin=-1;
        float localMaxWave = -1;
        for(int i=0; i<fft.length; i++){
            if(fft[i]>localMaxWave){
                localMaxBin=i;
                localMaxWave=fft[i];
            }
        }
        if(localMaxBin>maxBin){
            maxBin = localMaxBin;
        }
        if(localMaxBin<minBin && localMaxBin!=0){
            minBin = localMaxBin;
        }
        if(localMaxWave > maxWave){
            maxWave = localMaxWave;
        }
        float percentage = localMaxBin / (maxBin-minBin + 1);
        int newHue = (int)(percentage * MAX_HUE);
        PHBridge bridge = hue.getSelectedBridge();
        if((bridge != null)  ) {
            List<PHLight> allLights = bridge.getResourceCache().getAllLights();
            Random rand = new Random();

            for (PHLight light : allLights) {
                PHLightState lightState = new PHLightState();
                lightState.setHue(newHue);
                // To validate your lightstate is valid (before sending to the bridge) you can use:
                // String validState = lightState.validateState();
                bridge.updateLightState(light, lightState, listener);
                //  bridge.updateLightState(light, lightState);   // If no bridge response is required then use this simpler form.
            }
        }
    }
    PHLightListener listener = new PHLightListener() {

        @Override
        public void onSuccess() {
        }

        @Override
        public void onStateUpdate(Map<String, String> arg0, List<PHHueError> arg1) {
            //  Log.w(TAG, "Light has updated");
        }

        @Override
        public void onError(int arg0, String arg1) {}

        @Override
        public void onReceivingLightDetails(PHLight arg0) {}

        @Override
        public void onReceivingLights(List<PHBridgeResource> arg0) {}

        @Override
        public void onSearchComplete() {}
    };
}
