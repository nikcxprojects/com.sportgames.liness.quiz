package com.sportgames.liness.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.sportgames.liness.quiz.databinding.ActivityPrivacyBinding;

public class PrivacyActivity extends AppCompatActivity {

    private ActivityPrivacyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("privacy",false)) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.checkBox.setOnCheckedChangeListener((compoundButton, b) -> binding.button.setEnabled(!binding.button.isEnabled()));
        binding.button.setOnClickListener(view -> {
            getSharedPreferences("prefs",MODE_PRIVATE)
                    .edit()
                    .putBoolean("privacy",true)
                    .apply();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });
    }
}