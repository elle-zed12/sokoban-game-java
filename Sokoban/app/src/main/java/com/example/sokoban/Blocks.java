package com.example.sokoban;

import android.content.Context;

public class Blocks extends androidx.appcompat.widget.AppCompatImageView {
    public Blocks(Context context) {
        super(context);
    }

    public Blocks(Context context, char symbol) {
        super(context);

        switch (symbol) {

            case '#': {
                this.setImageResource(R.drawable.wall);
                break;
            }
            case '+': {
                this.setImageResource(R.drawable.target2);
                break;
            }
            case 'x': {
                this.setImageResource(R.drawable.crate);
                break;
            }
            case 'w': {
                this.setImageResource(R.drawable.worker1);
                break;
            }
            case '.': {
                this.setImageResource(R.drawable.empty);
                break;
            }
        }

    }

}
