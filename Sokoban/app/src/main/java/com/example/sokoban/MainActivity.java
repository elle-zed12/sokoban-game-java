package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.example.sokoban.utilities.Music;

public class MainActivity extends AppCompatActivity implements ISokobanMoves {
    SokobanController sController;
    private ViewGroup puzzleBoardSokoban;
    //buttons for control
    Button upBtn;
    Button downBtn;
    Button leftBtn;
    Button rightBtn;
    Button musicSwitch;
    Button restart;
    //Buttons for level
    Button level_1;
    Button level_2;
    Button level_3;
    Button level_4;

    private Music myMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sController = new SokobanController();

        //map with the id
        sController.view.levelName = findViewById(R.id.levelName);
        sController.view.targetCount = findViewById(R.id.targetCount);
        sController.view.moveCounter = findViewById(R.id.moveCount);
        sController.view.boardDisplay = findViewById(R.id.boardDisplay);
        sController.view.crateOnTarget = findViewById(R.id.crates_on_target);
        //buttons
        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        musicSwitch = findViewById(R.id.musicSwitch);
        restart = findViewById(R.id.restart);

        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);
        level_4 = findViewById(R.id.level_4);

        puzzleBoardSokoban = findViewById(R.id.board_image);

        sController.go();// starting level
        level_1.setBackgroundColor((getResources().getColor(R.color.black)));
        drawBoardGame();

        myMusic = new Music();
        myMusic.createMusicPlayer(this);


    }

    private void drawBoardGame() {
        puzzleBoardSokoban.removeAllViews();//8.	Only redraw the parts of that screen that has changed
        for (int x = 0; x < sController.getCurrentLevel().allPlaceables.length; x++) {
            TableRow puzzleBlocks = new TableRow(this);
            Blocks block;
            for (int y = 0; y < sController.getCurrentLevel().allPlaceables[x].length; y++) {
                char symbol = sController.getCurrentLevel().allPlaceables[x][y].symbol;
                block = new Blocks(MainActivity.this, symbol);
                if (sController.getCurrentLevel().allPlaceables[x][y].hasCrate()) {
                    block.setImageResource(R.drawable.crate);
                }
                if (sController.getCurrentLevel().allPlaceables[x][y] instanceof Empty &&
                        !sController.getCurrentLevel().allPlaceables[x][y].isEmpty()
                        && !sController.getCurrentLevel().allPlaceables[x][y].hasCrate()) {
                    block.setImageResource(R.drawable.worker1);
                }
                //if its worker on target
                if (sController.getCurrentLevel().allPlaceables[x][y] == sController.getCurrentLevel().workerOnTarget
                        && sController.getCurrentLevel().allPlaceables[x][y] instanceof Target) {
                    block.setImageResource(R.drawable.worker_on_target1);

                }
                //if its crate on target
                if (sController.getCurrentLevel().allPlaceables[x][y].hasCrate() &&
                        sController.getCurrentLevel().allPlaceables[x][y] instanceof Target) {
                    block.setImageResource(R.drawable.crate_target1);
                }
                puzzleBlocks.addView(block);
            }
            puzzleBoardSokoban.addView(puzzleBlocks);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myMusic.playing) {
            myMusic.pauseMusic();
            musicSwitch.setTextColor(Color.RED);
            musicSwitch.setText(R.string.sound_off);
        }
    }

    @Override
    public void moveDown(View view) {
        sController.moveDown();
        drawBoardGame();
    }

    @Override
    public void moveRight(View view) {
        sController.moveRight();
        drawBoardGame();
    }

    @Override
    public void moveLeft(View view) {
        sController.moveLeft();
        drawBoardGame();
    }

    @Override
    public void moveUp(View view) {
        sController.moveUp();
        drawBoardGame();
    }

    public void setLevel1(View view) {
        sController.setLevel("level_1");
        updateLevelChange();
        level_1.setBackgroundColor((getResources().getColor(R.color.black)));
    }

    public void setLevel2(View view) {
        sController.setLevel("level_2");
        updateLevelChange();
        level_2.setBackgroundColor((getResources().getColor(R.color.black)));
    }

    public void setLevel3(View view) {
        sController.setLevel("level_3");
        updateLevelChange();
        level_3.setBackgroundColor((getResources().getColor(R.color.black)));

    }

    public void setLevel4(View view) {
        sController.setLevel("level_4");
        updateLevelChange();
        level_4.setBackgroundColor((getResources().getColor(R.color.black)));
    }

    public void setDefaultColor() {
        level_1.setBackgroundColor((getResources().getColor(R.color.blue)));
        level_2.setBackgroundColor((getResources().getColor(R.color.lightBlue)));
        level_3.setBackgroundColor((getResources().getColor(R.color.darkerBlue)));
        level_4.setBackgroundColor((getResources().getColor(R.color.lighterBlue)));
    }

    public void updateLevelChange() {
        sController.restart();
        sController.updateDisplay();
        setDefaultColor();
        drawBoardGame();
    }

    public void toggleMusic(View view) {//3.	Provide sounds
        if (myMusic.playing) {
            myMusic.pauseMusic();
            musicSwitch.setTextColor(Color.RED);
            musicSwitch.setText(R.string.sound_off);
            return;
        }
        myMusic.startMusic();
        musicSwitch.setTextColor(Color.parseColor("#006400"));
        musicSwitch.setText(R.string.sound_on);
    }

    public void restart(View view) {
        sController.restart();
        sController.updateDisplay();
        drawBoardGame();
    }

}
