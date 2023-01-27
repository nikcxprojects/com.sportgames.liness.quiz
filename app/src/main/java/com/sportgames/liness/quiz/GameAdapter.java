package com.sportgames.liness.quiz;

import android.content.Context;
import android.graphics.Color;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sportgames.liness.quiz.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameHolder> {

    private List<Character> data;
    private String[] letters = new String[]{
            "coach".toUpperCase(), "footsal".toUpperCase(), "hockey".toUpperCase(),
            "soccer".toUpperCase(), "goalkeeper".toUpperCase(), "volleyball".toUpperCase(),
            "lacrosse".toUpperCase(), "running".toUpperCase()
    };
    private List<Integer> list = new ArrayList<>(),
        checked = new ArrayList<>();
    private List<List<Integer>> inds = new ArrayList<>();
    private List<Integer> hints = new ArrayList<>();
    private Callback callback;
    private boolean[] hinted = new boolean[100];
    private Context context;

    public GameAdapter(Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
        data = new ArrayList<>();
        Random random = new Random();
        for(int i = 0;i<100;i++) {
            data.add((char)('A'+random.nextInt(25)));
        }
        List<Integer> arrays = new ArrayList<>();
        for(int i = 0;i<letters[0].length();i++) {
            data.set(11+i,letters[0].charAt(i));
            list.add(11+i);
            arrays.add(11+i);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[1].length();i++) {
            data.set(23+i,letters[1].charAt(i));
            list.add(23+i);
            arrays.add(23+i);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[2].length();i++) {
            data.set(15+i*10,letters[2].charAt(i));
            list.add(15+i*10);
            arrays.add(15+i*10);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[3].length();i++) {
            data.set(38+i*10,letters[3].charAt(i));
            list.add(38+i*10);
            arrays.add(38+i*10);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[4].length();i++) {
            data.set(70+i,letters[4].charAt(i));
            list.add(70+i);
            arrays.add(70+i);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[5].length();i++) {
            data.set(2+i*10,letters[5].charAt(i));
            list.add(2+i*10);
            arrays.add(2+i*10);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[6].length();i++) {
            data.set(92+i,letters[6].charAt(i));
            list.add(92+i);
            arrays.add(92+i);
        }
        inds.add(arrays);
        arrays = new ArrayList<>();
        for(int i = 0;i<letters[7].length();i++) {
            data.set(10+i*10,letters[7].charAt(i));
            list.add(10+i*10);
            arrays.add(10+i*10);
        }
        inds.add(arrays);
    }

    interface Callback {
        void back(String txt);
    }

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public void hint() {
        Random random = new Random();
        while(true) {
            int sum = list.size();
            Log.d("TAG",checked.size()+" @@@@ "+sum);
            if(checked.size()==52) break;
            int ind = random.nextInt(sum);
            int tmp = 0;
            while(ind > inds.get(tmp).size()-1) {
                inds.size();
                ind-=inds.get(tmp).size();
                tmp++;
            }
            if(!checked.contains(inds.get(Math.min(tmp,inds.size()-1)).get(ind))) {
                Log.d("TAG",inds.get(tmp).get(ind)+" !!! "+checked.contains(inds.get(tmp).get(ind)));
                hints.add(inds.get(tmp).get(ind));
                hinted[inds.get(tmp).get(ind)] = true;
                notifyItemChanged(inds.get(tmp).get(ind),null);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        holder.binding(data.get(position).toString());
        Log.d("TAG",position+" || "+hints.contains(position));
        if(hints.contains(position)) {
            Log.d("TAG","CONTAINTS");
            holder.binding.getRoot().setBackgroundColor(context.getColor(R.color.hint));
        }
        holder.binding.getRoot().setOnClickListener(view -> {
            if (list.contains(position)) {
                holder.binding.getRoot().setBackgroundColor(context.getColor(R.color.choice));
                if(!checked.contains(position)) checked.add(position);
                for(int j = 0;j<inds.size();j++) {
                    List<Integer> tmp = inds.get(j);
                    boolean b = true;
                    for(int i:tmp) {
                        if(!checked.contains(i)) {
                            b = false;
                            break;
                        }
                    }
                    if(b) {
                        callback.back(letters[j]);
                    }
                }
            }
        });
        holder.binding.getRoot().setSoundEffectsEnabled(context.getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("sound",true));
        holder.binding.getRoot().playSoundEffect(SoundEffectConstants.CLICK);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    protected static class GameHolder extends RecyclerView.ViewHolder {

        ListItemBinding binding;

        public GameHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void binding(String str) {
            binding.letter.setText(str.trim());
        }
    }
}
