package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    public int[][] winpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int count = 0;
    private static final int[] BUTTON_IDS = {
            R.id.button_1,
            R.id.button_2,
            R.id.button_3,
            R.id.button_4,
            R.id.button_5,
            R.id.button_6,
            R.id.button_7,
            R.id.button_8,
            R.id.button_9,
    };
    TextView winnerTextwiew;
    Dialog dialog;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_1=findViewById(R.id.button_1);
        button_2=findViewById(R.id.button_2);
        button_3=findViewById(R.id.button_3);
        button_4=findViewById(R.id.button_4);
        button_5=findViewById(R.id.button_5);
        button_6=findViewById(R.id.button_6);
        button_7=findViewById(R.id.button_7);
        button_8=findViewById(R.id.button_8);
        button_9=findViewById(R.id.button_9);
        dialog=new Dialog(this);
    }

    @SuppressLint("ResourceAsColor")
    public void PlayerTap(View view) {
        Button button = (Button) view;
        int tappedbuttontag = Integer.parseInt(button.getTag().toString());
        if (!gameActive) {
            Toast.makeText(this, "RESET", Toast.LENGTH_LONG).show();
        }
        if (gameState[tappedbuttontag] == 2) {
            count++;
            if (count == 9) {
                gameActive = false;
            }
        }
        gameState[tappedbuttontag] = activePlayer;
        if (activePlayer == 0) {
            button.setBackgroundResource(R.drawable.x_img);
            activePlayer = 1;
        } else {
            button.setBackgroundResource(R.drawable.o_img);
            activePlayer = 0;
        }
        for (int id:BUTTON_IDS){
            Button button1=findViewById(id);
            if(button1.isPressed()){
                button1.setEnabled(false);
            }
        }
        int winnerDeclare = 0;
        for (int[] winPosition : winpositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                winnerDeclare = 1;
                String winner;
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
                try {
                    for (int j : winPosition) {
                        if (j == 0) {
                            button_1.setAnimation(animation);
                        } else if (j == 1) {
                            button_2.setAnimation(animation);
                        } else if (j == 2) {
                            button_3.setAnimation(animation);
                        } else if (j == 3) {
                            button_4.setAnimation(animation);
                        } else if (j == 4) {
                            button_5.setAnimation(animation);
                        } else if (j == 5) {
                            button_6.setAnimation(animation);
                        } else if (j == 6) {
                            button_7.setAnimation(animation);
                        } else if (j == 7) {
                            button_8.setAnimation(animation);
                        } else if (j == 8) {
                            button_9.setAnimation(animation);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Something Went Wrong!!", Toast.LENGTH_LONG).show();
                }

                if (gameState[winPosition[0]] == 0) {
                    winner = "X WON!";

                } else {
                    winner = "O WON!";
                }
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        for (int id : BUTTON_IDS) {
                            Button button2 =findViewById(id);
                            button2.setEnabled(false);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        showwinner1(winner);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

        }
            if (count == 9 && winnerDeclare == 0) {
                showwinner1("Game Draw!");
//                reset(view);
            }

    }

    public void reset(View view) {
        dialog.dismiss();
        gameActive = true;
        count = 0;
        activePlayer = 0;
        for (int id : BUTTON_IDS) {
            Button button2 =  findViewById(id);
            button2.setEnabled(true);
        }
        Arrays.fill(gameState, 2);
        for (int id : BUTTON_IDS) {
            findViewById(id).setBackgroundResource(R.drawable.roundbutton);
        }

    }
    public void showwinner1(String winner){
        try {
            dialog.setContentView(R.layout.winner_layout_img);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            winnerTextwiew=dialog.findViewById(R.id.winner_text);
            winnerTextwiew.setText(winner);
            dialog.findViewById(R.id.ok_button).setOnClickListener(view -> {
                dialog.dismiss();
                reset(view);
            });
            dialog.show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are You Sure Want to Quit")
                    .setIcon(R.drawable.ic_baseline_error_24)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), StartActivty.class));
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }catch (RuntimeException r){
            Toast.makeText(this,r.toString(),Toast.LENGTH_LONG).show();
        }
    }
}