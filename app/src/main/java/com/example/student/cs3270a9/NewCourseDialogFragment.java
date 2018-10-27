package com.example.student.cs3270a9;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.student.cs3270a9.db.AppDatabase;
import com.example.student.cs3270a9.db.Course;


public class NewCourseDialogFragment extends DialogFragment {

    private TextInputEditText edtID, edtName, edtCode, edtStart, edtEnd;
    private View root;
    private NewCourseDialogInterface mCallBack;

    interface NewCourseDialogInterface {
        void bringBackListView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.dialog_new_course, container, false);
        root.setBackgroundColor(Color.WHITE);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("New Course");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);

        edtID = (TextInputEditText) root.findViewById(R.id.edtID);
        edtName = (TextInputEditText) root.findViewById(R.id.edtName);
        edtCode = (TextInputEditText) root.findViewById(R.id.edtCode);
        edtStart = (TextInputEditText) root.findViewById(R.id.edtStart);
        edtEnd = (TextInputEditText) root.findViewById(R.id.edtEnd);

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (NewCourseDialogInterface) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    getString(R.string.ViewInterfaceError));
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_create_dialog, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_save:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Course course = new Course(edtID.getText().toString(), edtName.getText().toString()
                                , edtCode.getText().toString(), edtStart.getText().toString(),
                                edtEnd.getText().toString());
                        AppDatabase.getInstance(getContext())
                                .courseDAO()
                                .insert(course);
                    }
                }).start();
                mCallBack.bringBackListView();
                dismiss();
                return true;

            case android.R.id.home:
                mCallBack.bringBackListView();
                dismiss();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
