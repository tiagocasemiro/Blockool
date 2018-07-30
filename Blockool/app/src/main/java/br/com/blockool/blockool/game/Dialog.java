package br.com.blockool.blockool.game;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.blockool.blockool.R;

/**
 * Created by tiago.casemiro on 27/07/2018.
 */

public class Dialog {
    private android.app.Dialog dialog;
    private String labelButton;
    private String message;

    public Dialog create(Context context){
        dialog = new android.app.Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return this;
    }

    public Dialog listener(final View.OnClickListener onClickListener) {
        dialog.findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null) {
                    onClickListener.onClick(v);
                }
                dialog.dismiss();
            }
        });

        return this;
    }


    public Dialog button(String labelButton) {
        this.labelButton = labelButton;

        return this;
    }

    public Dialog message(String message) {
        this.message = message;

        return this;
    }

    public void show() {
        Button restart = ((Button) dialog.findViewById(R.id.restart));

        if(labelButton != null) {
            restart.setText(labelButton);
        } else {
            restart.setVisibility(View.GONE);
        }

        if(message != null) {
            ((TextView) dialog.findViewById(R.id.message)).setText(message);
        }

        dialog.show();
    }
}
