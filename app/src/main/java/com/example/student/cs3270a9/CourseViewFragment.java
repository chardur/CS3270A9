package com.example.student.cs3270a9;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.student.cs3270a9.db.Course;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseViewFragment extends Fragment {

    private View root;
    private TextInputEditText code;
    private TextInputEditText id;
    private TextInputEditText name;
    private TextInputEditText start;
    private TextInputEditText end;
    private Course course;
    private CourseViewInterface mCallBack;

    interface CourseViewInterface{
        void editPressed(Course course);
        void deletePressed();
    }

    public CourseViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course_view, container, false);
        root.setBackgroundColor(Color.WHITE);
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (CourseViewInterface) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    getString(R.string.ViewInterfaceError));
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        code = (TextInputEditText) root.findViewById(R.id.codeView);
        code.setEnabled(false);
        if (course.getCourse_code() != null) {
            code.setText(course.getCourse_code().toString());
        }else{
            code.setText("");
        }
        id = (TextInputEditText) root.findViewById(R.id.idView);
        id.setEnabled(false);
        if (course.getId() != null) {
            id.setText(course.getId().toString());
        }else{
            id.setText("");
        }
        name = (TextInputEditText) root.findViewById(R.id.nameView);
        name.setEnabled(false);
        if (course.getName() != null) {
            name.setText(course.getName().toString());
        }else{
            name.setText("");
        }
        start = (TextInputEditText) root.findViewById(R.id.startView);
        start.setEnabled(false);
        if (course.getStart_at() != null) {
            start.setText(course.getStart_at().toString());
        }else{
            start.setText("");
        }
        end = (TextInputEditText) root.findViewById(R.id.endView);
        end.setEnabled(false);
        if (course.getEnd_at() != null) {
            end.setText(course.getEnd_at().toString());
        }else{
            end.setText("");
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_view_frag, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_edit:
                mCallBack.editPressed(course);
                return true;

            case R.id.action_trash:
                mCallBack.deletePressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
            }

    public void setCourse(Course course){
        this.course = course;
    }

}
