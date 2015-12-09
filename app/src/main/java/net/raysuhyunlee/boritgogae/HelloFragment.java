package net.raysuhyunlee.boritgogae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SuhyunLee on 2015. 12. 9..
 */
public class HelloFragment extends StepFragment {

    @OnClick(R.id.buttonNext) void next() {
        onNextEvent.onEvent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
