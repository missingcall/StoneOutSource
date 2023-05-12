package com.jilsfdivbvetwo.jlsat167.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jilsfdivbvetwo.jlsat167.R;


public class GameActivity extends AppCompatActivity {

    private LoyabLayout mLoyabLayout;
    private TextView mLevel;
    private TextView mTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTime = (TextView)findViewById(R.id.id_time);
        mLevel = (TextView)findViewById(R.id.id_level);

        mLoyabLayout = (LoyabLayout)findViewById(R.id.id_puzzle);
        mLoyabLayout.setTimeEnabled(true);
        mLoyabLayout.setmListener(new LoyabLayout.PuzzleListener() {
            @Override
            public void nextLevel(final int nextLevel) {
                new AlertDialog.Builder(GameActivity.this).setTitle("Game Over!").setMessage("You have successfully advanced!").setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLoyabLayout.nextLevel();
                        mLevel.setText("Level : "+nextLevel);
                    }
                }).show();
            }

            @Override
            public void timeChanged(int currentTime) {
                mTime.setText("Remaining Time : "+currentTime);
            }

            @Override
            public void gameOver() {
                new AlertDialog.Builder(GameActivity.this).setTitle("Game Info").setMessage("Game Over").setPositiveButton("Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLoyabLayout.restart();
                    }
                }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        mLoyabLayout.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mLoyabLayout.resume();
    }
}
