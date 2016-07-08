package com.ramola.callrecord.CallRecorder;


import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
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
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(setFileName());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
    }

    private String setFileName(){
        File folder=Environment.getExternalStoragePublicDirectory("Call Recording");
        if(!folder.exists())
            folder.mkdir();
        return folder.getAbsolutePath()+"/"+fileName+".mp3";
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
