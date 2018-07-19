package br.com.blockool.blockool.game;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import br.com.blockool.blockool.entity.Block;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class DrawnSchene {
    private Context context;

    public DrawnSchene(Context context) {
        this.context = context;
    }

    public View getSchene(Block[][] allBlocks) {
        LinearLayout container =  new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
        container.setLayoutParams(layoutParams);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(1,1,1,1);

        for (Block[] blocks: allBlocks){
            LinearLayout line =  new LinearLayout(context);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            line.setOrientation(LinearLayout.HORIZONTAL);
            for(Block block: blocks) {
                View position = new View(context);
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                layoutParams.setMargins(1, 1, 1, 1);
                position.setLayoutParams(layoutParams);
                drawn(block, position);
                line.addView(position);
            }
            container.addView(line);
        }

        return container;
    }

    private void drawn(Block blockObject, View blockView) {
        if(blockObject != null) {
            switch (blockObject.getColor()) {
                case BLUE:
                    blockView.setBackgroundColor(Color.BLUE);
                    break;
                case MAGENTA:
                    blockView.setBackgroundColor(Color.MAGENTA);
                    break;
                case CYAN:
                    blockView.setBackgroundColor(Color.CYAN);
                    break;
                case GREEN:
                    blockView.setBackgroundColor(Color.GREEN);
                    break;
                case YELLOW:
                    blockView.setBackgroundColor(Color.YELLOW);
                    break;
                case RED:
                    blockView.setBackgroundColor(Color.RED);
                    break;
            }
        } else {
            blockView.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
