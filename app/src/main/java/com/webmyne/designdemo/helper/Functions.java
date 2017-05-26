package com.webmyne.designdemo.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by chiragpatel on 25-05-2017.
 */

public class Functions {

    public static void showPromptDialog(Context context, String title, String message, String positive, String negative, final onPromptListener onPromptListener) {

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (onPromptListener != null) {
                    onPromptListener.onPositive();
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (onPromptListener != null) {
                    onPromptListener.onNegative();
                }
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public interface onPromptListener {
        void onPositive();

        void onNegative();
    }


}
