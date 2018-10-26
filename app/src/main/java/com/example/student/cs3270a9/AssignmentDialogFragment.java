package com.example.student.cs3270a9;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentDialogFragment extends DialogFragment {

    private View root;
    private ListView listView;
    private Assignment[] assignments;

    public AssignmentDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_assignment_dialog, container, false);
        root.setBackgroundColor(Color.WHITE);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.assignmentToolbar);
        toolbar.setTitle("Assignments");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);
        assignments = new Assignment[1];
        assignments[0]= new Assignment("Loading...", "Loading...", "Loading..." );
        listView = (ListView)root.findViewById(R.id.listviewAssignment);
        final ArrayAdapter<Assignment> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, assignments);
        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                dismiss();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setAssignments(Assignment[] assignments) {
        this.assignments = assignments;
        final ArrayAdapter<Assignment> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, assignments);
        listView.setAdapter(adapter);
    }
}
