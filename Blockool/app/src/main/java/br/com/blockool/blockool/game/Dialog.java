package br.com.blockool.blockool.game;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.blockool.blockool.R;

/**
 * Created by tiago.casemiro on 27/07/2018.
 */

public class Dialog {
    private android.app.Dialog dialog;

    public Dialog create(Context context){
        dialog = new android.app.Dialog(context);
        dialog.setContentView(R.layout.dialog);

        View dialogButton = dialog.findViewById(R.id.restart);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return this;
    }

    public void showGameOver() {
        TextView text = (TextView) dialog.findViewById(R.id.message);
        text.setText("Game Over!");
        dialog.show();
    }

}
