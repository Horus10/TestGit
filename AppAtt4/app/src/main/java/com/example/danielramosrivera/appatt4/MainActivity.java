package com.example.danielramosrivera.appatt4;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Beans.Clientes;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //Variables del JSON
    ListView listaJson;
    Clientes cliente;
    ObtenerWebService hiloconexion;
    public static String mensaje= ":( ";

    String IP = "http://attstudent.urbantapp.com/pruebas%20daniel";
    String GET = IP+"/consulta%20profesores.php";


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.etUser)
    EditText _userText;
    @Bind(R.id.etClave) EditText _passwordText;
    @Bind(R.id.bAceptar) Button _loginButton;
    @Bind(R.id.bNotifi) Button _notifyButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Variables para el JSON
       // listaJson= (ListView)findViewById(R.id.)




        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               cliente = new Clientes(_userText.getText().toString(),_passwordText.getText().toString());
                try{
                    new ObtenerWebService(MainActivity.this).execute(cliente);
                    login();
                }catch (Exception e){
                    Toast.makeText(getBaseContext(), "NEL PERRO", Toast.LENGTH_LONG).show();
                }


            }
        });

        _notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,notificaciones.class);
                startActivity(i);
            }
        });


    }


    static class ObtenerWebService extends AsyncTask<Clientes,Void,Void>{
        Clientes cli;
    public static String rep="NADA :( ";
       public static String resultado ="fallo";
            String jeje="";


     static Context c;
        public static void getAndSetMyContext(Context c) {
            ObtenerWebService.c = c;
        }
        InputStream is;

        @Override
        protected Void doInBackground(Clientes... params) {


            cli = params[0];
            HttpClient client = new DefaultHttpClient();
            HttpPost enviopost = new HttpPost("http://attstudent.urbantapp.com/pruebas%20daniel/consulta%20profesores.php");
            try{



                List<NameValuePair> nombres = new ArrayList<NameValuePair>();
                nombres.add(new BasicNameValuePair("Usuario", cli.getNombre()));
                nombres.add(new BasicNameValuePair("Contrasena",cli.getEmail()));
                enviopost.setEntity(new UrlEncodedFormEntity(nombres, HTTP.UTF_8));
              
                
                
                
                //CODIGO MIX


                try{
                    HttpResponse response = client.execute(enviopost);
                    rep=response.toString();
                    HttpEntity contenido = response.getEntity();
                    //EntityUtils.toString(contenido, "UTF-8");

                    is = contenido.getContent();
                }catch (ClientProtocolException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

                BufferedReader buferlector = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String linea= null;

                try{
                    while ((linea = buferlector.readLine())!=null){
                        sb.append(linea);
                    }
                } catch(IOException e){
                    e.printStackTrace();
                }
                resultado = sb.toString();

                try {
                   // JSONObject objectJson  =new JSONObject(resultado);
                    JSONArray arrayJason = new JSONArray(resultado);

                    mensaje=arrayJason.getJSONObject(0).getString("Usuario")+" "+arrayJason.getJSONObject(0).getString("Usuario");



                } catch (JSONException e){
                    e.printStackTrace();
                }
                
                //CODIGO MIX
            }catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        private Context mContext;

        public ObtenerWebService(Context context){
            mContext = context;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
          // Toast.makeText(,s, Toast.LENGTH_LONG).show();
         /*   AlertDialog.Builder reorder = new AlertDialog.Builder(mContext);
            reorder.setMessage("Mensaje: "+mensaje+"\n Resultado Directo: "+resultado);
            reorder.setCancelable(true);
            reorder.setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog orderError = reorder.create();
            orderError.show();

*/


         if (resultado.length()==29){
                //  valid= false;

                AlertDialog.Builder reorder = new AlertDialog.Builder(mContext);
                reorder.setMessage("Verifique su clave y/o usuario ");
                reorder.setCancelable(true);
                reorder.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog orderError = reorder.create();
                orderError.show();

          }else{


             mContext.startActivity(new Intent(mContext,opciones.class));
         }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void s) {
            super.onCancelled(s);
        }

       // @Override
     //   protected String doInBackground(String... params) {

//again with same code!! :)




            ////


     /*       if (params[1]=="1"){//consulta por id

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){


                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados
                        JSONArray resultJSON = respuestaJSON.getJSONArray("results");   // results es el nombre del campo en el JSON

                        //Vamos obteniendo todos los campos que nos interesen.

                        String direccion="SIN DATOS de alumnos";
                        if (resultJSON.length()>0){
                            direccion = resultJSON.getJSONObject(0).getString("formatted_address");    // dentro del results pasamos a Objeto la seccion formated_address
                        }
                        devuelve = "Resultados: " + direccion;   // variable de salida que mandaré al onPostExecute para que actualice la UI

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
*/
           // return null;
        //}
    }
    //toast




    ///


  //codigo sujeto a cambio :)
/*
   static class TareaEnviar extends AsyncTask<Clientes,Void,Void>{

        Clientes cli;
        InputStream is;
        public static String usuarioC,passwordC;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Clientes... params) {
            cli = params[0];
            String resultado = "fallo";
            HttpClient client = new DefaultHttpClient();
            HttpPost enviopost = new HttpPost("http://attstudent.urbantapp.com/pruebas%20daniel/consulta%20profesores.php");
            try{
                List<NameValuePair> nombres = new ArrayList<NameValuePair>();
                nombres.add(new BasicNameValuePair("Usuario",cli.getEmail()));
                nombres.add(new BasicNameValuePair("Contrasena",cli.getClave()));
                enviopost.setEntity(new UrlEncodedFormEntity(nombres, HTTP.UTF_8));
                HttpResponse response = client.execute(enviopost);
            }catch (Exception e){
                e.printStackTrace();
            }




            ////
            HttpClient cliente = new DefaultHttpClient();
            HttpGet peticionGet = new HttpGet("http://attstudent.urbantapp.com/pruebas%20daniel/consulta%20profesores.php");
            try{
                HttpResponse response = cliente.execute(peticionGet);
                HttpEntity contenido = response.getEntity();
                is = contenido.getContent();
            }catch (ClientProtocolException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            BufferedReader buferlector = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea= null;

            try{
                while ((linea = buferlector.readLine())!=null){
                    sb.append(linea);
                }
            } catch(IOException e){
                e.printStackTrace();
            }
            resultado = sb.toString();

            try {
                JSONArray arrayJason = new JSONArray(resultado);
                JSONObject objectJson  = arrayJason.getJSONObject(0);
                usuarioC=objectJson.getString("Usuario");
                passwordC=objectJson.getString("Contrasena");

                Toast.makeText(c,"0- UsuarioEdit: "+usuarioC+"\nPassword:  "+passwordC, Toast.LENGTH_LONG).show();
            } catch (JSONException e){
                e.printStackTrace();
            }
            ////



            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);

        }
        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }
    }
*/
    //// :)




    public  void login() {





        Log.d(TAG, "Entrando");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String user = _userText.getText().toString();
        String password = _passwordText.getText().toString();


        // TODO: Implement your own authentication logic here.
           /* if (user!=TareaEnviar.usuarioC || password!=TareaEnviar.passwordC){
                onLoginFailed();
            }*/





        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

// finish();

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Entrada fallida", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _userText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _userText.setError("Ingrese un usuario Valido");
            valid = false;
        } else {
            _userText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Entre 4 y 10 caracteres alfanumericos");
            valid = false;
        } else {
            _passwordText.setError(null);
        }


        return valid;
    }



   /* Button btn_aceptar,btn_notifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_aceptar = (Button)findViewById(R.id.bAceptar);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,opciones.class);
                startActivity(i);
            }
        });

        btn_notifi = (Button)findViewById(R.id.bNotifi);
        btn_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,notificaciones.class);
                startActivity(i);
            }
        });
    }*/
}
