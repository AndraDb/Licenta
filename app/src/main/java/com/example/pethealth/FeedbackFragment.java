package com.example.pethealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FeedbackFragment extends Fragment {
    private Button feedform;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("My account frag","Ajunge aicea ");
        View view=inflater.inflate(R.layout.feedback_frag, container, false);

         feedform=(Button)view.findViewById(R.id.feedbtn);
        feedform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser=new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSetf5BZOAVGjaUn5QzFGn-9_F6OXy1mJiLHsqRT5TES22JGvw/viewform"));
                startActivity(browser);
            }
        });

 return view;
    }

}
