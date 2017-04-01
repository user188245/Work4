package com.example.user.work4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-30.
 */

public class Fragment1 extends Fragment{
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        ArrayList<Table> tableList = DB.tableList();
        listView = (ListView)view.findViewById(R.id.listView);
        TableAdapter tableAdapter = new TableAdapter(tableList,R.layout.fragment1,getActivity().getApplicationContext());
        listView.setAdapter(tableAdapter);
        return view;
    }

    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity().getApplicationContext(),"프레그먼트 1",Toast.LENGTH_SHORT).show();
        }
    }
}
