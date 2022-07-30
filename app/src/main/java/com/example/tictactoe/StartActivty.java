package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StartActivty extends AppCompatActivity {
    ImageView imageView;
    private long pressedTime;
    Button play_b,two_player_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activty);
        imageView=findViewById(R.id.tictactoe);
        play_b=findViewById(R.id.start_b);
        two_player_b=findViewById(R.id.two_player);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.zoom_animation);
        imageView.startAnimation(animation);
        Animation animation1=AnimationUtils.loadAnimation(this,R.anim.slide);
        play_b.setAnimation(animation1);
        Animation animation2=AnimationUtils.loadAnimation(this,R.anim.slide2);
        two_player_b.setAnimation(animation2);
        play_b.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Computer.class)));
        two_player_b.setOnClickListener(view ->  startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
    @Override
    public void onBackPressed(){
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}