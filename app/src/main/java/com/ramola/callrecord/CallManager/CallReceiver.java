package com.ramola.callrecord.CallManager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import java.util.Date;

public abstract class CallReceiver extends BroadcastReceiver {
    private static String PHONE_NUMBER;
    private static int LAST_STATE = TelephonyManager.CALL_STATE_IDLE;
    private static Date date;
    private static boolean IS_INCOMING;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == Intent.ACTION_NEW_OUTGOING_CALL) {
            PHONE_NUMBER = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        } else {
            int state = -1;
            String num = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER), callState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (callState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (callState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (callState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }
            onCallHandler(context, state, num);
        }
    }

    private void onCallHandler(Context context, int state, String number) {
        if (LAST_STATE == state) {
            return;
        } else {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    if (LAST_STATE == TelephonyManager.CALL_STATE_RINGING) {
                        onMissedCall(context, number, new Date());
                        break;
                    } else {
                        if (IS_INCOMING) {
                            onIncomingCallEnded(context, number, date, new Date());
                        } else {
                            onOutgoingCallEnded(context, number, date, new Date());
                        }
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (LAST_STATE != TelephonyManager.CALL_STATE_RINGING) {
                        IS_INCOMING = false;
                        date = new Date();
                        onOutgoingCallStarted(context, PHONE_NUMBER, date);
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    IS_INCOMING = true;
                    date = new Date();
                    onIncomingCallStarted(context, number, date);
                    break;
            }
            LAST_STATE = state;
        }

    }


    public abstract void onIncomingCallStarted(Context context, String number, Date startTime);

    public abstract void onOutgoingCallStarted(Context context, String number, Date startTime);

    public abstract void onIncomingCallEnded(Context context, String number, Date startTime, Date endTime);

    public abstract void onOutgoingCallEnded(Context context, String number, Date startTime, Date endTime);

    public abstract void onMissedCall(Context context, String number, Date startTime);
}
