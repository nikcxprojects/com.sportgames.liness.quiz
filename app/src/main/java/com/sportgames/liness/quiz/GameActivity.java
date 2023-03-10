package com.sportgames.liness.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.sportgames.liness.quiz.databinding.ActivityGameBinding;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private int hints = 0;
    private GameAdapter adapter;
    private MediaPlayer player;

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
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        hints = getSharedPreferences("prefs",MODE_PRIVATE).getInt("hints",0);
        setContentView(binding.getRoot());
        adapter = new GameAdapter(
                (txt)->{
                    Log.d("TAG",txt.toLowerCase()+" ||| "+binding.textView5.getText().toString());
                    if(binding.textView5.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView5.setPaintFlags(binding.textView5.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView6.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView6.setPaintFlags(binding.textView6.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView7.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView7.setPaintFlags(binding.textView7.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView8.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView8.setPaintFlags(binding.textView8.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView9.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView9.setPaintFlags(binding.textView9.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView10.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView10.setPaintFlags(binding.textView10.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView11.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView11.setPaintFlags(binding.textView11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if(binding.textView12.getText().toString().equalsIgnoreCase(txt)) {
                        binding.textView12.setPaintFlags(binding.textView12.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                },
                this);
        binding.list.setAdapter(adapter);
        binding.list.setNestedScrollingEnabled(false);
        binding.list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        timerHandler.postDelayed(timerRunnable, 0);
        binding.pause.setOnClickListener(view -> {
            if(start) {
                timerHandler.removeCallbacks(timerRunnable);
            } else {
                timerHandler.postDelayed(timerRunnable, 0);
            }
            start = !start;
        });
        if(getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("sound",true)) {
            player = MediaPlayer.create(this,R.raw.t2);
            player.start();
            player.setOnCompletionListener(mediaPlayer -> {
                player.reset();
                player.start();
            });
        }
        binding.hint.setOnClickListener(view -> {
            if(hints>0) {
                hints--;
                adapter.hint();
            }
            binding.hint.setEnabled(hints>0);
        });
        binding.hint.setEnabled(hints>0);
    }
    long startTime = 0;
    boolean start = true;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            startTime++;
            long seconds = startTime;
            binding.timer.setText(String.format("%s %d",getString(R.string.time),seconds));
            timerHandler.postDelayed(this, 1000);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        if(player!=null) player.pause();
        timerHandler.removeCallbacks(timerRunnable);
    }
}