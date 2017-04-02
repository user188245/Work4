package com.example.user.work4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TableAdapter extends BaseAdapter {

    private List<Table> tableList;
    final int layout;
    final Context context;
    LayoutInflater layoutInflater;

    public TableAdapter(List<Table> tableList, int layout, Context context) {
        super();
        this.tableList = tableList;
        this.layout = layout;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}

    @Override
    public int getCount() {
        return tableList.size();
    }

    @Override
    public Object getItem(int position) {
        return tableList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clearTableList(){
        tableList = DB.tableDB();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(layout,parent,false);
        String state = tableList.get(position).isEmpty()?"(비어있음)":"";
        TextView textTable = (TextView) convertView.findViewById(R.id.textTable);
        textTable.setText(tableList.get(position).toString() + state);
        return convertView;
    }
}
