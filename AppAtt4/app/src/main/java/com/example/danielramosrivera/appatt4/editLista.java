package com.example.danielramosrivera.appatt4;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Beans.DatabaseHelper;

public class editLista extends AppCompatActivity {


    DatabaseHelper myDb;
  //  EditText editName,editSurname,editMarks,editId;
    Button btnAddData,btnViewAll,btnUpdate;
    private ListView obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lista);

       // AddData();
        //ViewAll();
       // UpdateData();





    }


   /* public  void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(editLista.this, "Data Update", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(editLista.this, "Data Not Update", Toast.LENGTH_LONG).show();
            }
        });
    }*/
   /* public void AddData(){
        boolean isInserted = myDb.insertData("jose","0");


        if(isInserted=true)
            Toast.makeText(editLista.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(editLista.this, "Data Not Inserted", Toast.LENGTH_LONG).show();


    }*/

    /*public void ViewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0){
                            //Show Message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id : "+res.getString(0)+"\n");
                            buffer.append("Name : "+res.getString(1)+"\n");
                            buffer.append("Surname : "+res.getString(2)+"\n");
                            buffer.append("Marks : "+res.getString(3)+"\n\n");
                        }
                        showMessage("Data",buffer.toString());
                        //show all data
                    }
                }
        );
    }*/

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}
