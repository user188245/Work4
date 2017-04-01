package com.example.user.work4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by user on 2017-03-30.
 */

public class Fragment2 extends Fragment{
    Button button2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    private void init(View v){
        button2 = (Button) v.findViewById(R.id.button);
        button2.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity().getApplicationContext(),"프레그먼트 1",Toast.LENGTH_SHORT).show();
        }
    }
}
