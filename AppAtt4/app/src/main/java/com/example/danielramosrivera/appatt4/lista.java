package com.example.danielramosrivera.appatt4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Beans.DatabaseHelper;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class lista extends AppCompatActivity  {

    private ZXingScannerView escanerView;
    DatabaseHelper myDb;
    Button btnAddData, btnViewAll;
    public static String nombre;
    ListView li;
    ArrayList<String> lista=new ArrayList<String>();
    final Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState){



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        myDb = new DatabaseHelper(this);

        li = (ListView)findViewById(R.id.lvAgregados);
        btnAddData = (Button)findViewById(R.id.bAgregar);
        ViewAll();
        EscanerQR();
    }

    ///Metodos para scaner

    public void EscanerQR(){//nuevo codigo

        btnAddData.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {


                        IntentIntegrator integrator = new IntentIntegrator(activity);
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                        integrator.setPrompt("Escaneando");
                        integrator.setCameraId(0);
                        integrator.setBeepEnabled(false);
                        integrator.setBarcodeImageEnabled(false);
                        integrator.initiateScan();


                    }
                }
        );



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "Has cancelado el escaneo", Toast.LENGTH_LONG).show();
            }
            else {
               // Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                nombre=result.getContents();
                AddData();
                ViewAll();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

  //  @Override
    //protected void onPause() {
      //  super.onPause();
        //escanerView.stopCamera();
    //}

    //@Override
    //public void handleResult(Result result) {
       // nombre=result.getText().toString();
           // java.util.Date fecha = new Date();
            // Calendar c1 = Calendar.getInstance();
            //DateFormat fHora = new SimpleDateFormat("HH:mm:ss");
            //DateFormat fFecha = new SimpleDateFormat("dd/MM/yyyy");

        //

          /*  AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Resultado del Escaner");


            builder.setMessage("Mensaje: "+result.getText()+"\n Fecha: "+fFecha.format(fecha)+"\n Hora: "+fHora.format(fecha));
            AlertDialog alertdialog = builder.create();
            alertdialog.show();*/
           // escanerView.resumeCameraPreview(this);

    //}


    //Metodos para la bd
    public void AddData(){


        boolean isInserted = myDb.insertData(nombre,"1");

        if(isInserted=true) {
            Toast.makeText(lista.this, "Data Inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(lista.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
        }


    }

    public void ViewAll(){
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0){
                            //Show Message
                           // showMessage("Error","Nothing found");
                            return;
                        }

                       // StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){

                            lista.add(res.getString(1)+" "+res.getString(2));

                        }
                      //  showMessage("Data",buffer.toString());
                        //show all data

        ArrayAdapter<String> adap = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,lista);
        li.setAdapter(adap);
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
