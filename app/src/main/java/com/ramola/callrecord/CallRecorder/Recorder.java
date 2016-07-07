package com.ramola.callrecord.CallRecorder;


import android.media.MediaRecorder;
import android.os.Environment;

import java.io.IOException;

public class Recorder {

    private MediaRecorder mediaRecorder;
    private String fileName;

    public Recorder(String fileName) {
        this.fileName = fileName;
        initiateMediaRecorder();
    }

    private void initiateMediaRecorder(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(setFileName());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }

    private String setFileName(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+fileName+".3gp";
    }

    public void setRecord(boolean isPlay){
        if(isPlay){
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaRecorder.start();
        }
        else {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder=null;
        }
    }
}
