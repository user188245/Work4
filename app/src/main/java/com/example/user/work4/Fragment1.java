package com.example.user.work4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment1 extends Fragment{
    ListView listView;
    TableAdapter tableAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        listView = (ListView)view.findViewById(R.id.listView);
        tableAdapter = new TableAdapter(DB.tableDB(),R.layout.fragment1,getActivity().getApplicationContext());
        listView.setAdapter(tableAdapter);
        listView.setOnItemClickListener(new ItemListener());
        return view;
    }

    class ItemListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment2 fragment2 = (Fragment2)getFragmentManager().findFragmentById(R.id.fragment2);
            fragment2.setSelectedTable(position, (Table)tableAdapter.getItem(position));
        }
    }


}
