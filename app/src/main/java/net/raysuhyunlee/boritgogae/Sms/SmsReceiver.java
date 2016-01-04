package net.raysuhyunlee.boritgogae.Sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by SuhyunLee on 2015. 12. 8..
 */
public class SmsReceiver extends BroadcastReceiver {
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Intent serviceIntent = new Intent(context, SmsService.class);
                serviceIntent.putExtras(bundle);
                context.startService(serviceIntent);
            }
        }
    }

}
