package net.raysuhyunlee.boritgogae;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SuhyunLee on 2015. 12. 9..
 */
public class PaydayFragment extends StepFragment {

    private int numberPickerValue = 1;

    @Bind(R.id.numberPickerDate) NumberPicker numberPickerDate;

    @OnClick(R.id.buttonPrev) void prev() {
        onPrevEvent.onEvent();
    }
    @OnClick(R.id.buttonNext) void next() {
        SharedPreferences pref = getActivity()
                .getSharedPreferences(API.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(API.PREF_KEY_PAYDAY, numberPickerDate.getValue());
        editor.apply();
        onNextEvent.onEvent();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payday, container, false);
        ButterKnife.bind(this, view);

        numberPickerDate.setMinValue(1);
        numberPickerDate.setMaxValue(31);
        numberPickerDate.setValue(numberPickerValue);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        numberPickerValue = numberPickerDate.getValue();
    }
}
