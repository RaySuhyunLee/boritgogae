package net.raysuhyunlee.boritgogae;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by SuhyunLee on 2015. 12. 8..
 */
public class SmsReceiver extends BroadcastReceiver {
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle == null) return;

            Object[] pdus = (Object[]) bundle.get("pdus");
            String format = (String)bundle.get("format");
            SmsMessage[] msgs = new SmsMessage[pdus.length];
            String msg_from;

            for(int i=0; i<msgs.length; i++){
                if (Build.VERSION.SDK_INT >= 23) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                msg_from = msgs[i].getOriginatingAddress();
                String msgBody = msgs[i].getMessageBody();
                Log.d("Heloo", msg_from + ": " + msgBody);
            }
        }
    }
}
