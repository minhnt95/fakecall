package com.uet.fakecall;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.uet.fakecall.activity.SettingActivity;
import com.uet.fakecall.adapter.PagerViewerAdapter;
import com.uet.fakecall.fragment.FakeCallFragment;
import com.uet.fakecall.fragment.FakeSMSFragment;
import com.uet.fakecall.fragment.ScheduleFagment;

public class MainActivity extends AppCompatActivity {
    public static Context contextOfApp;
    private ImageView ivSetting;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private PagerViewerAdapter pagerAdapter;

    private FakeSMSFragment fakeSMSFragment;
    private FakeCallFragment fakeCallFragment;
    private ScheduleFagment scheduleFagment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo: rename meaningful: inits -> initViews()
        inits();
        initComps();
    }

    // remove unnesccesary func
    public static Context getContextOfApp(){
        return contextOfApp;
    }

    private void inits() {
        contextOfApp = getApplicationContext();
        ivSetting = (ImageView) findViewById(R.id.iv_setting);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        fragmentManager = getSupportFragmentManager();
        fakeCallFragment = new FakeCallFragment();
        fakeSMSFragment = new FakeSMSFragment();
        scheduleFagment = new ScheduleFagment();
        pagerAdapter = new PagerViewerAdapter(fragmentManager,fakeCallFragment,fakeSMSFragment,scheduleFagment);

    }

    private void initComps(){
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);

        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(mIntent);
            }
        });
    }
}