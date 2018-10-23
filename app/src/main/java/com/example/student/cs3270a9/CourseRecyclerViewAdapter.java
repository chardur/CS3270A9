package com.example.student.cs3270a9;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student.cs3270a9.db.Course;

import java.util.List;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder>{

    private final List<Course> courses;
    private final FragmentActivity activity;
    private CourseRecyclerInterface mCallBack;


    interface CourseRecyclerInterface{
        void itemSelected(Course course);
    }

    public CourseRecyclerViewAdapter(List<Course> courses, FragmentActivity activity) {
        this.courses = courses;
        this.activity = activity;
        try {
            mCallBack = (CourseRecyclerInterface) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + activity.getString(R.string.recyclerInterfaceError));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Course course = courses.get(position);
        if (course != null){
            holder.course = course;
            holder.tvLine1.setText(course.getName());
            holder.tvLine2.setText(course.getId());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo user clicked
                    mCallBack.itemSelected(course);

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void addItems(List<Course> courses) {

        this.courses.clear();
        this.courses.addAll(courses);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvLine1, tvLine2;
        public Course course;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvLine1 = (TextView) itemView.findViewById(R.id.line1);
            tvLine2 = (TextView) itemView.findViewById(R.id.line2);

        }
    }

}
