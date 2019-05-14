package com.example.mediainfo.free;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mediainfo.R;
import com.example.mediainfo.activites.DetailActivity;
import com.example.mediainfo.asynctaks.CardDetailsAsyncTask;
import com.example.mediainfo.fragment.FavouriteFragment;
import com.example.mediainfo.fragment.MovieFragment;
import com.example.mediainfo.fragment.TVSeriesFragment;
import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.wrapper.CommonListFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnFragmentInteractionListener
        , TVSeriesFragment.OnFragmentInteractionListener, FavouriteFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;

    InterstitialAd mInterstitialAd;
    private boolean isPopular = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        registerTopics();
        registerInstanceFirebaseMessaging();
    }


    private void registerInstanceFirebaseMessaging() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Could Messaging Issue", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("Cloud Messaging", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void registerTopics() {
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("Failed", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieFragment(), "Movies");
        adapter.addFragment(new TVSeriesFragment(), "TV Series");
        adapter.addFragment(new FavouriteFragment(), "Favourite");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(String id, String controller) {
        setupInterstitialAds(id,controller);
    }

    @Override
    public void clickOnIcon(CardDetails cardDetails,String controller) {
        CardDetailsAsyncTask asyncTask = new CardDetailsAsyncTask(getApplicationContext());
        cardDetails.setType(controller);
        asyncTask.saveDetails(cardDetails);
    }

    @Override
    public void favCardsListner(CardDetails cardDetails) {
        setupInterstitialAds(cardDetails.getId(),cardDetails.getType());
    }

    @Override
    public void clickOnDelete(CardDetails cardDetails) {
        CardDetailsAsyncTask asyncTask = new CardDetailsAsyncTask(getApplicationContext());
        asyncTask.deleteDetails(cardDetails);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);

        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.famous_titlebar_icon) {
            Toast.makeText(this, "" + isPopular, Toast.LENGTH_LONG).show();
            isPopular = !isPopular;
            for (Fragment fragment : adapter.mFragmentList) {
                CommonListFragment fragment1 = (CommonListFragment) fragment;
                fragment1.createList(isPopular);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupInterstitialAds(String data,String controller) {
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(getApplication().getString(R.string.indestrial_ad_unit_id ));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.i("Click on item", "onFragmentInteraction: " + data);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.INTENT_DATA, data);
                intent.putExtra(DetailActivity.INTENT_CONTROLLER_DATA, controller);
                startActivity(intent);
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
