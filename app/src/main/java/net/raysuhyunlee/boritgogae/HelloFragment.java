package net.raysuhyunlee.boritgogae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SuhyunLee on 2015. 12. 9..
 */
public class HelloFragment extends StepFragment {

    @Bind(R.id.editTextMoney) EditText editTextMoney;
    @Bind(R.id.textViewError) TextView textViewError;
    @BindString(R.string.db_key_money_left) String DB_KEY_MONEY_LEFT;

    @OnClick(R.id.buttonNext) void next() {
        String moneyString = editTextMoney.getText().toString();
        if (moneyString.length() == 0) {
            textViewError.setText(R.string.error_type_number);
        } else {
            try {
                DB db = DBFactory.open(getContext());
                int money = Integer.parseInt(moneyString);
                db.put(DB_KEY_MONEY_LEFT, money);
                db.close();
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
            onNextEvent.onEvent();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
