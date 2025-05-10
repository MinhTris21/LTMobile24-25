package com.example.studentinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_item, parent, false);
        }

        Student student = getItem(position);

        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView fullName = convertView.findViewById(R.id.full_name);
        TextView studentId = convertView.findViewById(R.id.student_id);
        TextView gender = convertView.findViewById(R.id.gender);
        TextView year = convertView.findViewById(R.id.year);

        String avatarName = student.getAvatar();
        int resId = getContext().getResources().getIdentifier(avatarName, "drawable", getContext().getPackageName());
        if (resId != 0) {
            avatar.setImageResource(resId);
        } else {
            avatar.setImageResource(android.R.drawable.ic_menu_info_details);
        }

        fullName.setText(student.getFullName());
        studentId.setText(student.getStudentId());
        gender.setText(student.getGender());
        year.setText(student.getYear());

        return convertView;
    }
}