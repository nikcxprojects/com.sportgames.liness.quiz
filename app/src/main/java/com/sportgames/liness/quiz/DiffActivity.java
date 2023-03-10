package com.sportgames.liness.quiz;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sportgames.liness.quiz.databinding.ActivityDiffBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class DiffActivity extends AppCompatActivity {

    private ActivityDiffBinding binding;

    private Locale mCurrentLocale;

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
        binding = ActivityDiffBinding.inflate(getLayoutInflater());
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
        int count1 = getSharedPreferences("prefs",MODE_PRIVATE).getInt("hints",0);
        count1 = (count1+1)%4;
        String txt1 = count1==0 ? getString(R.string.hints_off) : getString(R.string.hints)+" "+count1;
        binding.button2.setText(txt1);
        binding.button2.setOnClickListener(view -> {
            int count = getSharedPreferences("prefs",MODE_PRIVATE).getInt("hints",0);
            count = (count+1)%4;
            String txt = count==0 ? getString(R.string.hints_off) : getString(R.string.hints)+" "+count;
            binding.button2.setText(txt);
            getSharedPreferences("prefs",MODE_PRIVATE)
                    .edit()
                    .putInt("hints",count)
                    .apply();
        });
    }
}