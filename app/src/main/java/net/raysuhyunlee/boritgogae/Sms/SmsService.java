package net.raysuhyunlee.boritgogae.Sms;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;

import net.raysuhyunlee.boritgogae.DB.Money;

/**
 * Created by SuhyunLee on 2016. 1. 3..
 */
public class SmsService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        processSms(intent.getExtras());
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void processSms(Bundle bundle) {
        Object[] pdus = (Object[]) bundle.get("pdus");
        String format = (String)bundle.get("format");
        SmsMessage[] msgs = new SmsMessage[pdus.length];

        for(int i=0; i<msgs.length; i++){
            if (Build.VERSION.SDK_INT >= 23) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            } else {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String msg_from = msgs[i].getOriginatingAddress();
            String msgBody = msgs[i].getMessageBody();
            Money money = SmsParser.parse(msgBody);
            if (money != null) {
                Log.d("Heloo", money.amount + "원 at " + money.name);
                money.save();
            }
        }
    }
}
