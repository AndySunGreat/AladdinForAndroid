package com.aladdin.questionbank.common;

import android.app.Fragment;  
import android.os.Bundle;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import com.aladdin.questionbank.R;

public class FriendFragment extends Fragment  
{  
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  
        return inflater.inflate(R.layout.friend_fragment, container, false);  
    }  
  
}  

