package com.ramola.callrecord.CallManager;


import android.content.Context;

import com.ramola.callrecord.CallRecorder.Recorder;

import java.util.Date;

public class ManagerCall extends CallReceiver {

    public static Recorder recorder;

    @Override
    public void onIncomingCallStarted(Context context, String number, Date startTime) {
        recorder = new Recorder(number + " Incoming Call at " + startTime.getTime());

        recorder.setRecord(true);
    }

    @Override
    public void onOutgoingCallStarted(Context context, String number, Date startTime) {
        recorder = new Recorder(number + " Outgoing Call  at " + startTime.getTime());
        recorder.setRecord(true);
    }

    @Override
    public void onIncomingCallEnded(Context context, String number, Date startTime, Date endTime) {
        recorder.setRecord(false);
    }

    @Override
    public void onOutgoingCallEnded(Context context, String number, Date startTime, Date endTime) {
         recorder.setRecord(false);
    }

    @Override
    public void onMissedCall(Context context, String number, Date startTime) {

    }
}
