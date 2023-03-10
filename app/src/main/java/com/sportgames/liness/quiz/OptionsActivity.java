package com.sportgames.liness.quiz;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.sportgames.liness.quiz.databinding.ActivityOptionsBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class OptionsActivity extends AppCompatActivity {

    private ActivityOptionsBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(getLanguage(newBase));
    }
    private Context getLanguage(Context context) {
        Locale locale = new Locale(context.getSharedPreferences("prefs",MODE_PRIVATE).getString("locale","ru"));
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        try
        {
            // get input stream
            InputStream ims = getAssets().open("bg.png");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            binding.imageView.setImageDrawable(d);
            ims.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return;
        }
        setContentView(binding.getRoot());
        binding.button2.setOnClickListener(view -> {
            getSharedPreferences("prefs",MODE_PRIVATE)
                    .edit()
                    .putBoolean("music",!getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("music",true))
                    .apply();
            if(getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("music",true)) {
                binding.button2.setText(getString(R.string.music_on));
            } else binding.button2.setText(getString(R.string.music_off));
        });
        binding.button7.setText(getString(R.string.lang));
        if(getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("music",true)) {
            binding.button2.setText(getString(R.string.music_on));
        } else binding.button2.setText(getString(R.string.music_off));

        if(getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("sound",true)) {
            binding.button6.setText(getString(R.string.sounds_on));
        } else binding.button6.setText(getString(R.string.sounds_off));
        binding.button6.setOnClickListener(view -> {
            getSharedPreferences("prefs",MODE_PRIVATE)
                    .edit()
                    .putBoolean("sound",!getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("sound",true))
                    .apply();
            if(getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("sound",true)) {
                binding.button6.setText(getString(R.string.sounds_on));
            } else binding.button6.setText(getString(R.string.sounds_off));
        });

        binding.button7.setOnClickListener(view -> {
            Configuration configuration = getApplicationContext().getResources().getConfiguration();
            Log.d("TAG",configuration.locale.getLanguage());
            if(configuration.locale.getLanguage().equalsIgnoreCase("ru")) configuration.setLocale(Locale.forLanguageTag("en"));
            else configuration.setLocale(Locale.forLanguageTag("ru"));
            getSharedPreferences("prefs",MODE_PRIVATE)
                    .edit()
                    .putString("locale",configuration.locale.getLanguage())
                    .apply();
            recreate();
        });
    }

}