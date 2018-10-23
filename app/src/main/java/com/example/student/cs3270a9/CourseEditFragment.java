package com.example.student.cs3270a9;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.student.cs3270a9.db.AppDatabase;
import com.example.student.cs3270a9.db.Course;



/**
 * A simple {@link Fragment} subclass.
 */
public class CourseEditFragment extends Fragment {

    private View root;
    private TextInputEditText code;
    private TextInputEditText id;
    private TextInputEditText name;
    private TextInputEditText start;
    private TextInputEditText end;
    private Button save;
    private Course course;
    private CourseEditInterface mCallBack;

    interface CourseEditInterface{
        void savePressed();
    }

    public CourseEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_course_edit, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (CourseEditInterface) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    getString(R.string.ViewInterfaceError));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        root.setBackgroundColor(Color.WHITE);

        code = (TextInputEditText) root.findViewById(R.id.codeEdit);
        code.setText(course.getCourse_code().toString());
        id = (TextInputEditText) root.findViewById(R.id.idEdit);
        id.setText(course.getId().toString());
        name = (TextInputEditText) root.findViewById(R.id.nameEdit);
        name.setText(course.getName().toString());
        start = (TextInputEditText) root.findViewById(R.id.startEdit);
        start.setText(course.getStart_at().toString());
        end = (TextInputEditText) root.findViewById(R.id.endEdit);
        end.setText(course.getEnd_at().toString());
        save = (Button) root.findViewById(R.id.saveBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String courseCode = code.getText().toString();
                final String courseId = id.getText().toString();
                final String courseName = name.getText().toString();
                final String courseStart = start.getText().toString();
                final String courseEnd = end.getText().toString();

                course.setCourse_code(code.getText().toString());
                course.setId(id.getText().toString());
                course.setName(name.getText().toString());
                course.setStart_at(start.getText().toString());
                course.setEnd_at(end.getText().toString());

                code.setText("");
                id.setText("");
                name.setText("");
                start.setText("");
                end.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(getContext())
                                .courseDAO()
                                .editCourse(course);
                    }
                }).start();
                root.clearFocus();
                mCallBack.savePressed();
            }
        });
    }

    public void setCourse (Course course){
        this.course = course;
    }
}
