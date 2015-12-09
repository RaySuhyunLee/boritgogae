package net.raysuhyunlee.boritgogae;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SuhyunLee on 2015. 12. 9..
 */
public class PaydayFragment extends StepFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_payday, container, false);
    return view;
}
}
