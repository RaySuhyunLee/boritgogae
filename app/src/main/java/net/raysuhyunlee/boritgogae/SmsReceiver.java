package net.raysuhyunlee.boritgogae;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;

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

            List<Money> newMoneys = new ArrayList<>();

            for(int i=0; i<msgs.length; i++){
                if (Build.VERSION.SDK_INT >= 23) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                String msg_from = msgs[i].getOriginatingAddress();
                String msgBody = msgs[i].getMessageBody();
                Money money = new Money("helo", 10);
                if (money != null) {
                    Log.d("Heloo", money.amount + "원 at " + money.name);
                    newMoneys.add(money);
                }
            }

            if (newMoneys.size() > 0)
                putIntoDB(context, newMoneys);
        }
    }

    public void putIntoDB(Context context, List<Money> newMoneys) {
        String DB_KEY_MONEYS = context.getResources()
                .getString(R.string.db_key_moneys);
        try {
            DB db = DBFactory.open(context);
            List<Money> moneys = db.getObject(DB_KEY_MONEYS, ArrayList.class);
            moneys.addAll(newMoneys);
            /* for debuging
            for (Money money : moneys) {
                Log.d("bla", "money amount: " + money.amount);
            }*/
            db.put(DB_KEY_MONEYS, moneys);
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

}
