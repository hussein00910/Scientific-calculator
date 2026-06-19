package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements ButtonCallback {

    public static final HistoryManager historyManager = new HistoryManager();

    private CalculatorEngine engine;
    private TextView tvResult;
    private TextView tvExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        tvResult     = findViewById(R.id.tvResult);
        tvExpression = findViewById(R.id.tvExpression);

        engine = new CalculatorEngine();

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new CalcPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, pos) -> {
            tab.setText(pos == 0 ? R.string.tab_basic : R.string.tab_scientific);
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_theme) {
            int current = AppCompatDelegate.getDefaultNightMode();
            if (current == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            return true;
        } else if (id == R.id.action_history) {
            startActivity(new Intent(this, HistoryActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateDisplay() {
        tvResult.setText(engine.getCurrentInput());
        tvExpression.setText(engine.getExpression());
    }

    // --- ButtonCallback ---

    @Override public void onDigit(String digit)   { engine.inputDigit(digit); updateDisplay(); }
    @Override public void onDot()                 { engine.inputDot();        updateDisplay(); }
    @Override public void onSign()                { engine.toggleSign();      updateDisplay(); }
    @Override public void onPercent()             { engine.percent();         updateDisplay(); }
    @Override public void onClear()               { engine.reset();           updateDisplay(); }

    @Override
    public void onOperator(String op) {
        engine.setOperator(op);
        updateDisplay();
    }

    @Override
    public void onEqual() {
        String expr   = engine.getExpression();
        String result = engine.evaluate();
        historyManager.add(expr, result);
        updateDisplay();
    }

    @Override
    public void onScientific(String func) {
        String before = engine.getExpression();
        engine.applyScientific(func);
        String after  = engine.getExpression();
        historyManager.add(after, engine.getCurrentInput());
        updateDisplay();
    }

    @Override
    public void onConstant(double value) {
        engine.setConstant(value);
        updateDisplay();
    }
}
