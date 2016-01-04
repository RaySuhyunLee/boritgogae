package net.raysuhyunlee.boritgogae.DB;

import android.content.Context;
import android.content.SharedPreferences;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import net.raysuhyunlee.boritgogae.API;

import java.util.Calendar;

/**
 * Created by SuhyunLee on 2016. 1. 3..
 */

@Table(name="Budgets")
public class Budget extends Model {
    @Column(name="budget")
    public int budget;

    @Column(name="date")
    public Calendar date;

    public Budget() { }

    public Budget(int budget, Calendar date) {
        this.budget = budget;
        this.date = date;
    }

    public static Budget getCurrent(Context context) {
        Calendar now = Calendar.getInstance();
        SharedPreferences pref =
                context.getSharedPreferences(API.PREF_KEY_PAYDAY, Context.MODE_PRIVATE);
        int payday = pref.getInt(API.PREF_KEY_PAYDAY, 0);

        // truncate hour, minute, second, and millisecond
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        if (now.get(Calendar.DAY_OF_MONTH) < payday) {
            now.add(Calendar.MONTH, -1);
        }
        now.set(Calendar.DAY_OF_MONTH, payday);

        return new Select().from(Budget.class)
                .where("Date > ?", now.getTimeInMillis()).executeSingle();
    }
}
