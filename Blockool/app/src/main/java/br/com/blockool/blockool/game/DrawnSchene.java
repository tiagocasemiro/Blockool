package br.com.blockool.blockool.game;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import br.com.blockool.blockool.R;
import br.com.blockool.blockool.entity.Block;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class DrawnSchene {
    private Context context;
    private final Integer margin;

    public DrawnSchene(Context context) {
        this.context = context;
        margin = (int) context.getResources().getDimension(R.dimen.line_game);
    }

    public View getSchene(Block[][] allBlocks) {
        LinearLayout container =  new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
        container.setLayoutParams(layoutParams);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(margin,margin,margin,margin);

        for (Block[] blocks: allBlocks){
            LinearLayout line =  new LinearLayout(context);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            line.setOrientation(LinearLayout.HORIZONTAL);
            for(Block block: blocks) {
                View position = new View(context);
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                layoutParams.setMargins(margin, margin, margin, margin);
                position.setLayoutParams(layoutParams);
                drawn(block, position);
                line.addView(position);
            }
            container.addView(line);
        }

        return container;
    }

    private void drawn(Block blockObject, View blockView) {
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
}
