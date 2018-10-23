package com.example.student.cs3270a9;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.student.cs3270a9.db.AppDatabase;
import com.example.student.cs3270a9.db.Course;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteDialogFragment extends DialogFragment {

    private DeleteDialogInterface mCallBack;
    private Course course;

    interface DeleteDialogInterface{
        void deleteConfirmed();
    }

    public DeleteDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_message)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase.getInstance(getContext())
                                        .courseDAO()
                                        .delete(course);
                            }
                        }).start();
                        mCallBack.deleteConfirmed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (DeleteDialogInterface) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    getString(R.string.DeleteInterfaceError));
        }

    }

    public void setCourse (Course course){
        this.course = course;
    }

}
