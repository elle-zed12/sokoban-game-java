package com.example.sokoban;

import androidx.annotation.NonNull;

import java.text.MessageFormat;

public class SokobanController {
    Game game;
    SokobanView view;

    SokobanController() {
        this.game = new Game();
        this.view = new SokobanView();
    }

    public Level getCurrentLevel() {
        return game.currentLevel;
    }

    //2.	Displays the level name
    public String getlevelName() {
        return game.getCurrentLevelName();
    }

    public void setLevel(@NonNull String level) {
        switch (level) {
            case "level_1": {
                game.addLevel("Level 1", 6, 7, "#######" +
                        "#+x...#" +
                        "#..#..#" +
                        "#.x+w##" +
                        "#....##" +
                        "#######");
                break;
            }
            case "level_2": {
                game.addLevel("Level 2", 7, 7, "#######" +
                        "#+x...#" +
                        "#+x#..#" +
                        "#+xw..#" +
                        "#...###" +
                        "#.x.+##" +
                        "#####.#");
                break;
            }
            case "level_3": {
                game.addLevel("Level 3", 7, 7, "#######" +
                        "#+x...#" +
                        "#+x#.##" +
                        "#+xw..#" +
                        "#..####" +
                        "#..x.+#" +
                        "#######");
                break;
            }
            case "level_4": {
                game.addLevel("Level 4", 7, 7, "#######" +
                        "#+x..+#" +
                        "#+x#.x#" +
                        "#+xw..#" +
                        "#...###" +
                        "#..x+##" +
                        "#######");
                break;
            }
        }
    }
    public void moveDown() {
        this.game.move(Direction.DOWN);
        updateDisplay();
    }

    public void moveUp() {
        this.game.move(Direction.UP);
        updateDisplay();
    }

    public void moveLeft() {
        this.game.move(Direction.LEFT);
        updateDisplay();
    }

    public void moveRight() {
        this.game.move(Direction.RIGHT);
        updateDisplay();
    }

    public void restart(){
        this.game.currentLevel.restart();
    }

    public void go(){
        this.setLevel("level_1");
        updateDisplay();
    }

    public void updateDisplay(){//method to update the view
        this.view.showCratesOnTarget("Crates on target:" + this.game.currentLevel.crateOnTarget);
        this.view.showLevelName(this.getCurrentLevel().name);
        this.view.showMoveCount("Number of moves:"+this.getCurrentLevel().moveCount);
        this.view.showTargetCount("Num of target:" + this.getCurrentLevel().targetCount);
        checkIfLevelCompleted();
    }

    public void checkIfLevelCompleted() {
        if (this.getCurrentLevel().targetCount == this.getCurrentLevel().crateOnTarget) {
            this.view.showMoveCount(MessageFormat.format("{0} is completed in {1} moves", this.getlevelName(),
                    this.getCurrentLevel().moveCount));
        }
    }


}
