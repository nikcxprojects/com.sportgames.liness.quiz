package com.sportgames.liness.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sportgames.liness.quiz.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String lang;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(getLanguage(newBase));
    }
    private Context getLanguage(Context context) {
        lang = context.getSharedPreferences("prefs",MODE_PRIVATE).getString("locale","ru");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!lang.equalsIgnoreCase(getSharedPreferences("prefs",MODE_PRIVATE).getString("locale","ru"))) {
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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
        binding.game.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),GameActivity.class)));
        binding.button3.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),OptionsActivity.class)));
        binding.button4.setOnClickListener(view -> finish());
        binding.button5.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),DiffActivity.class)));
    }
}