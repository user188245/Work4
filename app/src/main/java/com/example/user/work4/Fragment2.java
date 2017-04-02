package com.example.user.work4;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.user.work4.DB.*;

public class Fragment2 extends Fragment{
    LinearLayout dishLayout, dishLayoutInDlg;
    TextView textTableName,textTableTime,textTableCard,textTablePrice;
    Button buttonOrder,buttonModify,buttonReset;
    TextView[] textDish;
    EditText[] editDish;
    EditText dlgYear,dlgMonth,dlgDay,dlgHour,dlgMinute;
    AlertDialog.Builder dlg;
    private Integer selectedTableIndex = null;
    private Table selectedTable = null;
    ArrayAdapter<CharSequence> spinnerArrayAdapter;
    LinearLayout.LayoutParams params;
    Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        init(view);
        return view;
    }

    private void init(View v){
        textTableName = (TextView)v.findViewById(R.id.tableName);
        textTableTime = (TextView)v.findViewById(R.id.tableTime);
        textTableCard = (TextView)v.findViewById(R.id.tableCard);
        textTablePrice= (TextView)v.findViewById(R.id.tablePrice);
        buttonOrder = (Button)v.findViewById(R.id.buttonOrder);
        buttonModify= (Button)v.findViewById(R.id.buttonModify);
        buttonReset = (Button)v.findViewById(R.id.buttonReset);
        ButtonListener buttonListener = new ButtonListener();
        buttonOrder.setOnClickListener(buttonListener);
        buttonModify.setOnClickListener(buttonListener);
        buttonReset.setOnClickListener(buttonListener);

        params =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;

        patchDishList(v);
    }

    private void patchDishList(View v){
        dishLayout = (LinearLayout)v.findViewById(R.id.dishLayout);
        textDish = new TextView[dishList().size()];

        for(int i=0; i<textDish.length; i++){
            textDish[i] = new TextView(getActivity());
            setDishTextViewDefault(textDish[i], dishList().get(i).toString());
            dishLayout.addView(textDish[i]);
        }
    }

    private View constructDlg(){

        View viewDlg = View.inflate(getActivity(),R.layout.my_custom_dlg,null);

        dishLayoutInDlg = (LinearLayout)viewDlg.findViewById(R.id.dishListInDlg);
        dlgYear   = (EditText)viewDlg.findViewById(R.id.dlgYear);
        dlgMonth  = (EditText)viewDlg.findViewById(R.id.dlgMonth);
        dlgDay    = (EditText)viewDlg.findViewById(R.id.dlgDay);
        dlgHour   = (EditText)viewDlg.findViewById(R.id.dlgHour);
        dlgMinute = (EditText)viewDlg.findViewById(R.id.dlgMinute);
        spinner = (Spinner)viewDlg.findViewById(R.id.spinner);
        spinnerArrayAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.card, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(0);

        editDish = new EditText[DB.dishList().size()];

        for(int i = 0; i< editDish.length; i++) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(params);
            tableRow.setPadding(5,5,5,5);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            TextView textViewName = new TextView(getActivity());
            textViewName.setText(DB.dishList().get(i).name());
            textViewName.setLayoutParams(params);
            editDish[i] = new EditText(getActivity());
            editDish[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            editDish[i].setHint("주문 수량 입력");
            editDish[i].setLayoutParams(params);
            tableRow.addView(textViewName);
            tableRow.addView(editDish[i]);
            dishLayoutInDlg.addView(tableRow);
        }
        return viewDlg;
    }

    private void setDishTextViewDefault(TextView textView, String text){
        textView.setText(text);
        textView.setTextSize(15);
        textView.setLayoutParams(params);
        textView.setPadding(5,5,5,5);

    }

    void setSelectedTable(int index,Table table){
        this.selectedTableIndex = new Integer(index);
        this.selectedTable = table;
        textTableName.setText(table.getName());
        textTableTime.setText(table.reservTimeToString());
        textTableCard.setText(table.isEmpty()?"":table.cardType());
        textTablePrice.setText(table.isEmpty()?"":Long.toString(table.getTotalPrice()) + "원");
        for(int i=0;i<textDish.length;i++){
            textDish[i].setText(selectedTable.getDishList().get(i).toString());
        }
    }

    void clear(){
        selectedTable = null;
        selectedTableIndex = null;
        textTableName.setText("");
        textTableTime.setText("");
        textTableCard.setText("");
        textTablePrice.setText("");
        ArrayList<Dish> dishList = DB.dishList();
        for(int i=0;i<textDish.length;i++){
            textDish[i].setText(dishList.get(i).toString());
        }
    }



    private class ButtonListener implements View.OnClickListener,DialogInterface.OnClickListener{
        @Override

        public void onClick(View v) {
            try {
                switch (v.getId()) {
                    case R.id.buttonOrder:
                        if(selectedTable == null){
                            Toast.makeText(getActivity().getApplicationContext(),"테이블을 선택해 주세요.",Toast.LENGTH_SHORT).show();
                        }else{
                            dlg = new AlertDialog.Builder(getActivity());
                            dlg.setView(constructDlg());
                            dlg.setTitle("새 주문");
                            setEnabledDateEditor(true);
                            dlg.setPositiveButton("취소", null);
                            dlg.setNegativeButton("확인", this);
                            dlg.show();
                        }
                        break;
                    case R.id.buttonModify:
                        if(selectedTable == null){
                            Toast.makeText(getActivity().getApplicationContext(),"테이블을 선택해 주세요.",Toast.LENGTH_SHORT).show();
                        }else{
                            if(selectedTable.isEmpty()){
                                Toast.makeText(getActivity().getApplicationContext(),"아직 예약되지 않은 테이블 입니다.",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                dlg = new AlertDialog.Builder(getActivity());
                                dlg.setView(constructDlg());
                                dlg.setTitle("주문 수정");
                                setEnabledDateEditor(false);
                                dlg.setPositiveButton("취소", null);
                                dlg.setNegativeButton("확인", this);
                                dlg.show();
                            }
                        }
                        break;
                    case R.id.buttonReset:
                        Fragment1 fragment1 = (Fragment1)getFragmentManager().findFragmentById(R.id.fragment1);
                        ((TableAdapter)(fragment1.listView.getAdapter())).clearTableList();
                        clear();
                        Snackbar.make(getView(), "초기화 되었습니다.", Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }catch(RuntimeException e){
                Toast.makeText(getActivity().getApplicationContext(),"Error::" + e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            try {
                Fragment1 fragment1 = (Fragment1) getFragmentManager().findFragmentById(R.id.fragment1);
                ArrayList<Dish> dishList = selectedTable.getDishList();
                for (int i = 0; i < dishList.size(); i++) {
                    dishList.get(i).setNum(getInt(editDish[i].getText().toString()));
                }
                int year = getInt(dlgYear.getText().toString());
                int month = getInt(dlgMonth.getText().toString());
                int day = getInt(dlgDay.getText().toString());
                int hour = getInt(dlgHour.getText().toString());
                int minute = getInt(dlgMinute.getText().toString());
                Table.CardType cardType = Table.CardType.values()[spinner.getSelectedItemPosition()];
                selectedTable.setReserv(dishList,cardType,year,month,day,hour,minute);
                setSelectedTable(selectedTableIndex.intValue(),selectedTable);
                ((TableAdapter)fragment1.listView.getAdapter()).notifyDataSetChanged();
                Snackbar.make(getView(), "요청이 완료되었습니다.", Snackbar.LENGTH_SHORT).show();
            }catch(NullPointerException e){
                Toast.makeText(getActivity().getApplicationContext(),"빈 곳을 전부 기입해주세요",Toast.LENGTH_SHORT).show();
                buttonOrder.callOnClick();
            }
        }


        void setEnabledDateEditor(boolean b){
            dlgYear.setEnabled(b);
            dlgMonth.setEnabled(b);
            dlgDay.setEnabled(b);
            dlgHour.setEnabled(b);
            dlgMinute.setEnabled(b);
            if(!b){
                dlgYear.setText(Integer.toString(selectedTable.getReservTime().get(Calendar.YEAR)));
                dlgMonth.setText(Integer.toString(selectedTable.getReservTime().get(Calendar.MONTH)));
                dlgDay.setText(Integer.toString(selectedTable.getReservTime().get(Calendar.DATE)));
                dlgHour.setText(Integer.toString(selectedTable.getReservTime().get(Calendar.HOUR_OF_DAY)));
                dlgMinute.setText(Integer.toString(selectedTable.getReservTime().get(Calendar.MINUTE)));
            }
        }

        public int getInt(String s){
            return s.isEmpty()?null:Integer.parseInt(s);
        }
    }
}
