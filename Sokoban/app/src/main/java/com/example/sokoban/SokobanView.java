package com.example.sokoban;


import android.widget.TextView;

public class SokobanView implements IViewSokoban {
    //    TextView
    TextView levelName;
    TextView targetCount;
    TextView moveCounter;
    TextView boardDisplay;
    TextView crateOnTarget;



    @Override
    public void showTargetCount(String num) {
        targetCount.setText(num);
    }

    @Override
    public void showMoveCount(String num) {
        moveCounter.setText(num);
    }

    @Override
    public void showLevelName(String name) {
        this.levelName.setText(name);
    }
    @Override
    public void showCratesOnTarget(String num) {
        crateOnTarget.setText(num);
    }



}
