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

import java.util.ArrayList;

public class Health_weightDialog extends AppCompatDialogFragment {

    EditText Weight_pick;
    Dialog_pickerListener listener;
    ArrayList selectedItems;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        selectedItems = new ArrayList();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.health_weight,null);
        builder.setView(view)
                .setTitle("Stat Picker")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String age=Weight_pick.getText().toString();
                        listener.applyStat(age);

                    }
                });
        Weight_pick = view.findViewById(R.id.weight_pick);
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
        void applyStat(String weight);
    }
}
