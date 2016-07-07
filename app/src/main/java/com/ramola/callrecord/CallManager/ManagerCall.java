package com.ramola.callrecord.CallManager;


import android.content.Context;

import java.util.Date;

public class ManagerCall extends CallReceiver{
    @Override
    public void onIncomingCallStarted(Context context, String number, Date startTime) {

    }

    @Override
    public void onOutgoingCallStarted(Context context, String number, Date startTime) {

    }

    @Override
    public void onIncomingCallEnded(Context context, String number, Date startTime, Date endTime) {

    }

    @Override
    public void onOutgoingCallEnded(Context context, String number, Date startTime, Date endTime) {

    }

    @Override
    public void onMissedCall(Context context, String number, Date startTime) {

    }
}
