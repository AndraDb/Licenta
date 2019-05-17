package com.example.pethealth;

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
        return inflater.inflate(R.layout.feedback_frag, container, false);
        //feedform=(Button)findViewById(R.id.feedbtn);


    }

}
