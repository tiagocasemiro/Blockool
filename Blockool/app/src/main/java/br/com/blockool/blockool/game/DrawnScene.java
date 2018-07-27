package br.com.blockool.blockool.game;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import br.com.blockool.blockool.R;
import br.com.blockool.blockool.entity.Block;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class DrawnScene {
    private Context context;
    private final Integer margin;
    private LinearLayout container;
    private FrameLayout gameView;

    public DrawnScene(Context context, FrameLayout gameView) {
        this.context = context;
        this.margin = (int) context.getResources().getDimension(R.dimen.line_game);
        this.gameView = gameView;
    }

    public void initScene(Block[][] allBlocks) {
        container =  new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
        container.setLayoutParams(layoutParams);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(margin, margin, margin, margin);

        int lineCount = 0;
        for (Block[] blocks: allBlocks) {
            int columCount = 0;
            LinearLayout line =  new LinearLayout(context);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            line.setOrientation(LinearLayout.HORIZONTAL);
            for(Block block: blocks) {
                View position = new View(context);
                position.setId(joinNumbers(lineCount, columCount));
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                layoutParams.setMargins(margin, margin, margin, margin);
                position.setLayoutParams(layoutParams);
                drawn(block, position);
                line.addView(position);
                columCount++;
            }
            container.addView(line);
            lineCount++;
        }

        gameView.removeAllViews();
        gameView.addView(container);
    }

    public void reDrawnScene(Block[][] allBlocks) {
        int lineCount = 0;
        for (Block[] blocks: allBlocks){
            int columCount = 0;
            for(Block block: blocks) {
                View position = container.findViewById(joinNumbers(lineCount, columCount));
                drawn(block, position);
                columCount++;
            }
            lineCount++;
        }
    }

    public void gameOverScene(Block[][] allBlocks) {
        int lineCount = 0;
        for (Block[] blocks: allBlocks){
            int columCount = 0;
            for(Block block: blocks) {
                View position = container.findViewById(joinNumbers(lineCount, columCount));
                drawnGameOver(block, position);
                columCount++;
            }
            lineCount++;
        }
    }

    private int joinNumbers(int first, int second) {
        String firstNumber = String.valueOf(first);
        String secondnumber = String.valueOf(second);
        String number = firstNumber.concat(secondnumber);

        return Integer.parseInt(number);
    }

    public void getScene(Block[][] allBlocks) {
        if(container == null)
            initScene(allBlocks);
        else
            reDrawnScene(allBlocks);
    }

    public static void drawn(Block blockObject, View blockView) {
        if(blockObject != null && blockObject.getColor() != null) {
            switch (blockObject.getColor()) {
                case BLUE:
                    blockView.setBackgroundColor(Color.argb(255, 20,114, 254));
                    break;
                case MAGENTA:
                    blockView.setBackgroundColor(Color.argb(255, 255,0, 191));
                    break;
                case CYAN:
                    blockView.setBackgroundColor(Color.argb(255, 38,188, 234));
                    break;
                case GREEN:
                    blockView.setBackgroundColor(Color.argb(255, 25,210, 45));
                    break;
                case YELLOW:
                    blockView.setBackgroundColor(Color.argb(255, 255,197, 31));
                    break;
                case RED:
                    blockView.setBackgroundColor(Color.argb(255, 234,60, 14));
                    break;
            }
        } else {
            blockView.setBackgroundColor(Color.argb(20, 70,69, 80));
        }
    }

    public static void drawnGameOver(Block blockObject, View blockView) {
        if(blockObject != null && blockObject.getColor() != null) {
            blockView.setBackgroundColor(Color.argb(255, 90,90, 90));
        } else {
            blockView.setBackgroundColor(Color.argb(20, 70,69, 80));
        }
    }
}
