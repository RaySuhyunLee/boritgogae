package net.raysuhyunlee.boritgogae;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SuhyunLee on 2015. 12. 8..
 */
public class FirstTimeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        // instantiate step fragments
        final StepFragment helloFragment = new HelloFragment();
        final StepFragment livingExpensesFragment = new LivingExpensesFragment();
        final StepFragment paydayFragment = new PaydayFragment();

        // set next and prev event to each fragment
        helloFragment.setOnNextEvent(() -> nextStep(livingExpensesFragment));
        livingExpensesFragment.setOnNextEvent(() -> nextStep(paydayFragment));
        livingExpensesFragment.setOnPrevEvent(() -> prevStep(helloFragment));
        paydayFragment.setOnNextEvent(() -> end());
        paydayFragment.setOnPrevEvent(() -> prevStep(livingExpensesFragment));

        startStep(helloFragment);
    }

    private void startStep(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void nextStep(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void prevStep(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void end() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
