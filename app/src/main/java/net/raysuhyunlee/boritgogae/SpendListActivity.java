package net.raysuhyunlee.boritgogae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SuhyunLee on 2015. 12. 11..
 */
public class SpendListActivity extends AppCompatActivity {

    @Bind(R.id.listViewSpend) ListView listViewSpend;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        List<Money> moneyList = new ArrayList<>();
        ListAdapter adapter = new InjectionArrayAdapter<>(this,
                R.layout.money, moneyList, (data, position, view) -> {

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
