package net.raysuhyunlee.boritgogae.DB;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Calendar;

/**
 * Created by SuhyunLee on 2015. 12. 8..
 */
@Table(name="Moneys")
public class Money extends Model {

    @Column(name="Name")
    public String name;

    @Column(name="Amount")
    public int amount;

    @Column(name="Date")
    public Calendar date;

    public Money() {}

    public Money(String name, int amount, Calendar date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}
