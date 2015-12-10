package net.raysuhyunlee.boritgogae;

import android.support.v4.app.Fragment;

/**
 * Created by SuhyunLee on 2015. 12. 9..
 */
public class StepFragment extends Fragment {
    protected OnEventListener onNextEvent;
    protected OnEventListener onPrevEvent;

    public static interface OnEventListener {
        public void onEvent();
    }

    public void setOnNextEvent(OnEventListener listener) {
        onNextEvent = listener;
    }

    public void setOnPrevEvent(OnEventListener listener) {
        onPrevEvent = listener;
    }
}
