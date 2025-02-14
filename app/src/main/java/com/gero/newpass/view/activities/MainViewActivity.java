package com.gero.newpass.view.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.gero.newpass.ContextWrapper.NewPassContextWrapper;
import com.gero.newpass.SharedPreferences.SharedPreferencesHelper;
import com.gero.newpass.database.DatabaseServiceLocator;
import com.gero.newpass.databinding.ActivityMainViewBinding;

import com.gero.newpass.R;
import com.gero.newpass.utilities.SystemBarColorHelper;
import com.gero.newpass.view.fragments.LanguageDialogFragment;
import com.gero.newpass.view.fragments.MainViewFragment;

import java.util.Locale;
import java.util.Objects;

public class MainViewActivity extends AppCompatActivity implements LanguageDialogFragment.LanguageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainViewBinding binding = ActivityMainViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseServiceLocator.init(getApplicationContext());

        SystemBarColorHelper.changeBarsColor(this, R.color.background_primary);

        // Initial fragment setup, showing the 'AddFragment' by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainViewFragment())
                    .commit();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferencesHelper.toggleDarkLightModeUI(this);
    }

    public void openFragment(Fragment fragment) {

        // Perform the fragment transaction and add it to the back stack
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // If the fragment stack has more than one entry, pop the back stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            // Otherwise, defer to the system default behavior
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesHelper.SHARED_PREF_FLAG, MODE_PRIVATE);
        String language = sharedPreferences.getString(SharedPreferencesHelper.LANG_PREF_FLAG, "en");

        super.attachBaseContext(NewPassContextWrapper.wrap(context, language));

        Locale locale = new Locale(language);
        Resources resources = getBaseContext().getResources();
        Configuration conf = resources.getConfiguration();

        conf.setLocale(locale);
        resources.updateConfiguration(conf, resources.getDisplayMetrics());
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        String selectedLanguage = list[position];

        if (Objects.equals(selectedLanguage, "中国人")) {
            selectedLanguage = "zh";
        }

        SharedPreferencesHelper.setLanguage(this, selectedLanguage);
    }

    @Override
    public void onNegativeButtonClicked() { }
}