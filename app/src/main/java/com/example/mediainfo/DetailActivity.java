package com.example.mediainfo;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener {

    public static final String INTENT_DATA = "detail_activity_intent_data";
    public static final String INTENT_CONTROLLER_DATA = "detail_activity_intent_controller_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String id = getIntent().getStringExtra(INTENT_DATA);
        String controller = getIntent().getStringExtra(INTENT_CONTROLLER_DATA);
        FragmentManager manager = getSupportFragmentManager();
        Toast.makeText(getApplicationContext(),id + controller , Toast.LENGTH_LONG).show();
        manager.beginTransaction().add(R.id.fragment,DetailFragment.newInstance(id,controller)).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
