package com.aladdin.questionbank.common;

import com.aladdin.questionbank.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class BottomFragment extends Fragment {

    private ImageButton mButton;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_bottombar, container, false);
        /*mButton = (ImageButton)view.findViewById(R.id.id_title_left_btn);
        mButton.setOnClickListener(new OnClickListener() {
			@Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "i am an ImageButton in TitleFragment ! ",  
                        Toast.LENGTH_SHORT).show();  
            }
        });*/
        return view;
    }

}