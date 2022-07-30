package com.example.tictactoe;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Computer extends AppCompatActivity implements View.OnClickListener {

    ImageButton mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    Button restartGameBtn;

    int PLAYER_Computer = 0;
    int PLAYER_Human = 1;
    int activePlayer = PLAYER_Computer;
    boolean isGameActive = true;
    TextView winnerTextwiew;
    int[] winPosition={10,10,10};
    Dialog dialog;
    int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);

        mButton0 =  findViewById(R.id.button_1);
        mButton1 =  findViewById(R.id.button_2);
        mButton2 =  findViewById(R.id.button_3);
        mButton3 =  findViewById(R.id.button_4);
        mButton4 =  findViewById(R.id.button_5);
        mButton5 =  findViewById(R.id.button_6);
        mButton6 =  findViewById(R.id.button_7);
        mButton7 =  findViewById(R.id.button_8);
        mButton8 =  findViewById(R.id.button_9);
        restartGameBtn =  findViewById(R.id.reset_b);
        dialog=new Dialog(this);

        mButton0.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);

        restartGameBtn.setOnClickListener(view -> restartGame());

        compterPlayer();
    }

    private void compterPlayer() {

        int randomNumber = new Random().nextInt(filledPositions.length);

        int buttonToClick = filledPositions[randomNumber];
        if (buttonToClick == -1) {
            if (randomNumber == 0) {
                filledPositions[randomNumber] = PLAYER_Computer;
                mButton0.setBackgroundResource(R.drawable.o_img);
                activePlayer = PLAYER_Human;
            }
            if (randomNumber == 1) {
                mButton1.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
            }
            if (randomNumber == 2) {
                mButton2.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;

            }
            if (randomNumber == 3) {
                mButton3.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
            }
            if (randomNumber == 4) {
                mButton4.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;

            }
            if (randomNumber == 5) {
                mButton5.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;

            }
            if (randomNumber == 6) {
                mButton6.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;

            }
            if (randomNumber == 7) {
                mButton7.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
            }
            if (randomNumber == 8) {
                mButton8.setBackgroundResource(R.drawable.o_img);
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
            }
        } else {
            compterPlayer();
        }

        checkWinner();

        checkDraw();

    }

    @Override
    public void onClick(View view) {
        if (!isGameActive)
            return;
        ImageButton clickedBtn = findViewById(view.getId());

        int clickedTag = Integer.parseInt(view.getTag().toString());

        if (filledPositions[clickedTag] != -1) {
            return;

        }
        filledPositions[clickedTag] = activePlayer;

        if (activePlayer != PLAYER_Computer) {
            clickedBtn.setBackgroundResource(R.drawable.x_img);

        }
        compterPlayer();

        checkWinner();

        checkDraw();
    }

    private void checkDraw() {
        int count = 0;

        for (int i = 0; i < 9; i++) {
            if (filledPositions[i] != -1) {
                count++;
            }
        }
        if (count == 9) {
            if (checkWinner())
                return;
            showdiagbox("Game Draw");
            isGameActive = false;
        }
    }

    private boolean checkWinner() {

        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            winPosition[0] = winningPosition[i][0];
            winPosition[1] = winningPosition[i][1];
            winPosition[2] = winningPosition[i][2];
            
            if (filledPositions[winPosition[0]] != -1) {
                if (filledPositions[winPosition[0]] == filledPositions[winPosition[1]] && filledPositions[winPosition[1]] == filledPositions[winPosition[2]]) {

                    isGameActive = false;
                    if (filledPositions[winPosition[0]] == PLAYER_Computer) {
                        showWinner("O WON!",winPosition);

                    } else {
                        showWinner("YOU WON!",winPosition);
                    }
                    return true;
                }


            }
        }
        return false;
    }

    private void showWinner(String winner,int[] winPosition) {
        try {
            Animation animation= AnimationUtils.loadAnimation(this,R.anim.blink);
            try {
                for (int j : winPosition) {
                    if (j == 0) {
                        mButton0.setAnimation(animation);
                    }
                    else if (j == 1) {
                        mButton1.setAnimation(animation);
                    }
                    else if (j == 2) {
                        mButton2.setAnimation(animation);
                    }
                    else if (j == 3) {
                        mButton3.setAnimation(animation);
                    }
                    else if (j == 4) {
                        mButton4.setAnimation(animation);
                    }
                    else if (j == 5) {
                        mButton5.setAnimation(animation);
                    }
                    else if (j == 6) {
                        mButton6.setAnimation(animation);
                    }
                    else if (j == 7) {
                        mButton7.setAnimation(animation);
                    }
                    else if (j == 8) {
                        mButton8.setAnimation(animation);
                    }
                }
            }catch (Exception e){
                Toast.makeText(this,"Something Went Wrong!!",Toast.LENGTH_LONG).show();
            }

            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    for (int id:BUTTON_IDS){
                        ImageButton button2= findViewById(id);
                        button2.setEnabled(false);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    showdiagbox(winner);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }catch (RuntimeException r){
            Toast.makeText(this,r.toString(),Toast.LENGTH_LONG).show();
        }
    }


    public void showdiagbox(String winner){
        try {
            dialog.setContentView(R.layout.winner_layout_img);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            winnerTextwiew=dialog.findViewById(R.id.winner_text);
            winnerTextwiew.setText(winner);
            dialog.findViewById(R.id.ok_button).setOnClickListener(view -> {
                dialog.dismiss();
                restartGame();
            });
            dialog.show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private void restartGame() {
        Intent intent = new Intent(getApplicationContext(), Computer.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are You Sure Want to Quit")
                .setIcon(R.drawable.ic_baseline_error_24)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),StartActivty.class));
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

}