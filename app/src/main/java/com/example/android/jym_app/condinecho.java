package com.example.android.jym_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.util.ArrayList;

public class condinecho extends AppCompatActivity {
String coce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condinecho);

        EditText bus=(EditText) findViewById(R.id.editText4);
        Thread  tr = new Thread(){

            public void run(){

                coce = getIntent().getStringExtra("ceduco");
                final String Resultado = leer2(coce);
                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                            }
                        });

            }

        };
        tr.start();
    }
    public void main_bb33(View view){

        Thread  tr = new Thread(){

            public void run(){
                coce = getIntent().getStringExtra("ceduco");
                EditText bus=(EditText) findViewById(R.id.editText4);
                final String Resultado = leer("333" , bus.getText().toString());
                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                            }
                        });

            }

        };
        tr.start();
    }
    public void main_aa33(View view){

        Thread  tr = new Thread(){

            public void run(){


                final String Resultado = leer2("333");
                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                            }
                        });

            }

        };
        tr.start();
    }
    public void cargaListado(ArrayList<String> datos){

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        ListView listado = (ListView) findViewById(R.id.listView2);
        listado.setAdapter(adaptador);
    }
    public String leer(String id1,String id2){
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?idchofer=" + id1+"&idservicio=" + id2;
        HttpGet httpget = new HttpGet("http://192.168.43.87:80/www/android/Getdinc.php"+parametros);
        String resultado=null;
        try {
            HttpResponse response = cliente.execute(httpget,contexto);
            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultado;
    }
    public String leer2(String id1){


        HttpClient cliente = new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?idchofer=" + id1;
        HttpGet httpget = new HttpGet("http://192.168.43.87:80/www/android/Getdincactu.php"+parametros);
        String resultado = null;
        try {
            HttpResponse response = cliente.execute(httpget, contexto);
            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }

        return resultado;
    }
    public ArrayList<String> obtDatosJSON(String response){
        ArrayList<String> listado= new ArrayList<String>();
        try {
            JSONArray json= new JSONArray(response);
            String texto="";
            for (int i=0; i<json.length();i++){
                texto =         json.getJSONObject(i).getString("idservicio") +"               "+
                        json.getJSONObject(i).getString("gananciachofer") +"               "+
                        json.getJSONObject(i).getString("porcentajeganchofer") +"               "+
                        json.getJSONObject(i).getString("fecha");


                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }
}
