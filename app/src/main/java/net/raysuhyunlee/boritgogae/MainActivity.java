package net.raysuhyunlee.boritgogae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import net.raysuhyunlee.boritgogae.DB.Budget;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.textViewMoneyLeft) TextView textViewMoneyLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMoneyLeft();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuSpendList:
                openSpendList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateMoneyLeft() {
        Budget budget = Budget.getCurrent(this);
        textViewMoneyLeft.setText(String.valueOf(budget.budget));
    }

    private void openSpendList() {
        Intent intent = new Intent(this, SpendListActivity.class);
        startActivity(intent);
    }

}
