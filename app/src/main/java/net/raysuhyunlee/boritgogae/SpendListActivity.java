package net.raysuhyunlee.boritgogae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import net.raysuhyunlee.boritgogae.DB.Money;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SuhyunLee on 2015. 12. 11..
 */
public class SpendListActivity extends AppCompatActivity {

    @Bind(R.id.listViewSpend) ListView listViewSpend;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        List<Money> moneys = new Select().from(Money.class).execute();
        ListAdapter adapter = new InjectionArrayAdapter<>(this,
                R.layout.money, moneys, (data, position, view) -> {

            Money money = (Money)data;
            TextView textViewName = (TextView)view.findViewById(R.id.textViewName);
            TextView textViewAmount = (TextView)view.findViewById(R.id.textViewAmount);

            textViewName.setText(money.name);
            textViewAmount.setText(money.amount + "원");
            return view;
        });
        listViewSpend.setAdapter(adapter);
    }
}
