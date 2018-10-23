package com.example.student.cs3270a9;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.student.cs3270a9.db.Course;

public class MainActivity extends AppCompatActivity implements
        CourseRecyclerViewAdapter.CourseRecyclerInterface, CourseViewFragment.CourseViewInterface,
        CourseEditFragment.CourseEditInterface, DeleteDialogFragment.DeleteDialogInterface{

    private CourseViewFragment courseViewFragment;
    private CourseEditFragment courseEditFragment;
    private DeleteDialogFragment dialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FragmentManager fm = getSupportFragmentManager();
                NewCourseDialogFragment newCoursedialog = new NewCourseDialogFragment();
                fm.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(android.R.id.content, newCoursedialog)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void itemSelected(Course course) {

        setToolbar();
        courseViewFragment = new CourseViewFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.viewFrag, courseViewFragment,"ViewFragment")
                .hide(fm.findFragmentByTag("contentMain"))
                .addToBackStack(null)
                .commit();

            courseViewFragment.setCourse(course);

        dialog = new DeleteDialogFragment();
        dialog.setCourse(course);

    }

    @Override
    public void editPressed(Course course) {
        courseEditFragment = new CourseEditFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.editFrag, courseEditFragment,"EditFragment")
                .hide(fm.findFragmentByTag("ViewFragment"))
                .addToBackStack(null)
                .commit();
        courseEditFragment.setCourse(course);
    }

    @Override
    public void deletePressed() {
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @Override
    public void savePressed() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .show(fm.findFragmentByTag("contentMain"))
                .hide(fm.findFragmentByTag("EditFragment"))
                .commit();
    }

    @Override
    public void deleteConfirmed() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .hide(fm.findFragmentByTag("ViewFragment"))
                .show(fm.findFragmentByTag("contentMain"))
                .commit();

    }
    public void setToolbar(){
        setSupportActionBar(toolbar);
    }
}