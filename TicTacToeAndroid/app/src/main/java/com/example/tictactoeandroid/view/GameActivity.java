package com.example.tictactoeandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoeandroid.R;
import com.example.tictactoeandroid.game.TicTacToe;
import com.example.tictactoeandroid.model.ClientController;

import pl.droidsonroids.gif.GifImageView;

public class GameActivity extends AppCompatActivity {

    private Button oneSquare;
    private Button twoSquare;
    private Button threeSquare;
    private Button fourSquare;
    private Button fiveSquare;
    private Button sixSquare;
    private Button sevenSquare;
    private Button eightSquare;
    private Button nineSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ClientController.getInstance().setContext(this);
        Toast.makeText(GameActivity.this, "Bienvenido " + getIntent().getStringExtra("username"), Toast.LENGTH_SHORT).show();
        this.init();
    }

    public void waitTurn () {
        ((TextView)findViewById(R.id.message)).setText("Esperando la jugada del contrincante");
    }

    public void playTurn () {
        ((TextView)findViewById(R.id.message)).setText("Juega tu turno");
    }

    public void onSelect(int row, int column) {
        if (!ClientController.getInstance().isWaiting()) {
            if (ClientController.getInstance().sendSelectSquare(row, column) == TicTacToe.SUCCESS) {
                Button btn = null;
                Log.wtf("", row + "     " + column);
                if (row == 0 && column == 0) {
                    btn = oneSquare;
                } else if (row == 0 && column == 1) {
                    btn = twoSquare;
                } else if (row == 0 && column == 2) {
                    btn = threeSquare;
                } else if (row == 1 && column == 0) {
                    btn = fourSquare;
                } else if (row == 1 && column == 1) {
                    btn = fiveSquare;
                } else if (row == 1 && column == 2) {
                    btn = sixSquare;
                } else if (row == 2 && column == 0) {
                    btn = sevenSquare;
                } else if (row == 2 && column == 1) {
                    btn = eightSquare;
                } else if (row == 2 && column == 2) {
                    btn = nineSquare;
                }
                //Drawable d = getResources().getDrawable(android.R.color.darker_gray);
                //btn.setBackground(d);
            } else {
                Toast.makeText(GameActivity.this, "Ya esa casilla está seleccionada", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(GameActivity.this, "No es tu turno", Toast.LENGTH_SHORT).show();
        }
    }

    public void animation(int type) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                oneSquare.setVisibility(View.GONE);
                twoSquare.setVisibility(View.GONE);
                threeSquare.setVisibility(View.GONE);
                fourSquare.setVisibility(View.GONE);
                fiveSquare.setVisibility(View.GONE);
                sixSquare.setVisibility(View.GONE);
                sevenSquare.setVisibility(View.GONE);
                sevenSquare.setVisibility(View.GONE);
                eightSquare.setVisibility(View.GONE);
                nineSquare.setVisibility(View.GONE);
                ((TextView)findViewById(R.id.message)).setVisibility(View.GONE);

                if (type == TicTacToe.SELECTED_PLAYER) {
                    ((GifImageView)findViewById(R.id.victory)).setVisibility(View.VISIBLE);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(((GifImageView)findViewById(R.id.victory)), "x", 500f);
                    animator.setRepeatCount(ObjectAnimator.INFINITE);
                    animator.setDuration(3000);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(animator);
                    animatorSet.start();
                } else if (type == TicTacToe.SELECTED_CPU) {
                    ((GifImageView)findViewById(R.id.defeated)).setVisibility(View.VISIBLE);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(((GifImageView)findViewById(R.id.defeated)), "y", 1000f);
                    animator.setRepeatCount(ObjectAnimator.INFINITE);
                    animator.setDuration(3000);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(animator);
                    animatorSet.start();
                }

            }
        });
    }

    public void init() {

        this.oneSquare = findViewById(R.id.oneSquare);
        this.oneSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect(0, 0);
            }
        });
        this.twoSquare = findViewById(R.id.twoSquare);
        this.twoSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect(0, 1);
            }
        });
        this.threeSquare = findViewById(R.id.threeSquare);
        this.threeSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 0, 2);
            }
        });
        this.fourSquare = findViewById(R.id.fourSquare);
        this.fourSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 1, 0);
            }
        });
        this.fiveSquare = findViewById(R.id.fiveSquare);
        this.fiveSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 1, 1);
            }
        });
        this.sixSquare = findViewById(R.id.sixSquare);
        this.sixSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 1, 2);
            }
        });
        this.sevenSquare = findViewById(R.id.sevenSquare);
        this.sevenSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 2, 0);
            }
        });
        this.eightSquare = findViewById(R.id.eightSquare);
        this.eightSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 2, 1);
            }
        });
        this.nineSquare = findViewById(R.id.nineSquare);
        this.nineSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect( 2, 2);
            }
        });

    }
}