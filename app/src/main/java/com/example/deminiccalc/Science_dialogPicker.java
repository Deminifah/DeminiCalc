package com.example.deminiccalc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Science_dialogPicker extends AppCompatDialogFragment {
    EditText Weight_pick;
    EditText Height_pick;
    EditText Age_pick;
    Dialog_pickerListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.scientific_dialog,null);
        builder.setView(view)
                .setTitle("Scientific function")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String height=Height_pick.getText().toString();
                        String weight=Weight_pick.getText().toString();
                        String age=Age_pick.getText().toString();
                        listener.applyStat(height,weight,age);

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Dialog_pickerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement Dialog_pickerListener");
        }
    }

    public interface Dialog_pickerListener{
        void applyStat(String height,String weight,String age);
    }
}
