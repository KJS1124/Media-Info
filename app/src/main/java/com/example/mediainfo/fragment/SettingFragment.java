package com.example.mediainfo.fragment;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.mediainfo.R;

public class SettingFragment extends PreferenceFragmentCompat  {



    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.setting);
    }


}
