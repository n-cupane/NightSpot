package com.nighter.nightspot.models;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.nighter.nightspot.R;

public class AlertDialogCustom extends AlertDialog {


    public AlertDialogCustom(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_custom);
    }
}
