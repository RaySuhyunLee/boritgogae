package net.raysuhyunlee.boritgogae;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SuhyunLee on 2015. 12. 9..
 */
public class LivingExpensesFragment extends StepFragment {

    @Bind(R.id.editTextMoney) EditText editTextMoney;
    @Bind(R.id.textViewError) TextView textViewError;

    @BindString(R.string.pref_name) String PREF_NAME;
    @BindString(R.string.pref_key_budget) String PREF_KEY_BUDGET;

    @OnClick(R.id.buttonPrev) void prev() {
        onPrevEvent.onEvent();
    }
    @OnClick(R.id.buttonNext) void next() {
        String moneyString = editTextMoney.getText().toString();
        if (moneyString.length() == 0) {
            textViewError.setText(R.string.error_type_number);
        } else {
            int budget = Integer.parseInt(moneyString);
            SharedPreferences pref = getActivity()
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(PREF_KEY_BUDGET, budget);
            editor.apply();

            onNextEvent.onEvent();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_living_expenses, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
