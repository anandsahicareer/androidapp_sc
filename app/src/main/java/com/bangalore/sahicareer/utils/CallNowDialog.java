package com.bangalore.sahicareer.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.bangalore.sahicareer.R;

public class CallNowDialog {                   //suppose this class not working copied below code paste it to where you want line no -52 to 86

    public void showDialog(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.callus_customlayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button callnow = dialog.findViewById(R.id.frmCallnow);
        //final EditText et_mobileno=dialog.findViewById(R.id.edit_mobileno);
        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:18001031610"));
                activity.startActivity(intent);

            }
        });

        Button cancel = dialog.findViewById(R.id.frmCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }

}


/*  public class CallNowDialog {

        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.callus_customlayout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Button callnow = dialog.findViewById(R.id.frmCallnow);
            //final EditText et_mobileno=dialog.findViewById(R.id.edit_mobileno);
            callnow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:18001031610"));
                    startActivity(intent);

                }
            });

            Button cancel = dialog.findViewById(R.id.frmCancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            dialog.show();
        }
    }*/
