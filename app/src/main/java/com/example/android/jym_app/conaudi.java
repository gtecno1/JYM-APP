package com.example.android.jym_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class conaudi extends AppCompatActivity {
Integer bus;
    EditText bus1;
    TextView ber;
    Button busc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conaudi);
       bus1 = (EditText) findViewById(R.id.editText3);
      ber = (TextView) findViewById(R.id.textView);
        Thread  tr = new Thread(){

            public void run(){

                final String Resultado = leer11("");
                final String Resultado2 = leer22("");
                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                                cargaListado2(obtDatosJSON2(Resultado2));
                            }
                        });

            }

        };
        tr.start();





    }
    public void main_bb2(View view){
        Thread  tr = new Thread(){

            public void run(){
                EditText bus=(EditText) findViewById(R.id.editText3);
                final String Resultado = leer(bus.getText().toString());
                final String Resultado2 = leer2(bus.getText().toString());
                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                                cargaListado2(obtDatosJSON2(Resultado2));
                            }
                        });

            }

        };

        tr.start();

    }
    public void main_aa2(View view){
        Thread  tr = new Thread(){

            public void run(){

                final String Resultado = leer11("");
                final String Resultado2 = leer22("");
                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                                cargaListado2(obtDatosJSON2(Resultado2));
                            }
                        });

            }

        };
        tr.start();

    }

    public void cargaListado(ArrayList<String> datos){

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        ListView listado = (ListView) findViewById(R.id.listusu);
        listado.setAdapter(adaptador);
    }
    public String leer(String id){
        bus=bus1.getText().length();
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?ce=" + id;


        String ruta="http://192.168.43.87:80/www/android/otros/Getaudi1.php"+parametros;
        HttpGet httpget = new HttpGet(ruta );
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
    public String leer11(String id){
        bus=bus1.getText().length();
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?ce=" + id;


        String ruta="http://192.168.43.87:80/www/android/otros/Getaudi11.php"+parametros;
        HttpGet httpget = new HttpGet(ruta );
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

    public ArrayList<String> obtDatosJSON(String response){
        ArrayList<String> listado= new ArrayList<String>();
        try {
            JSONArray json= new JSONArray(response);
            String texto="";
            for (int i=0; i<json.length();i++){
                texto =         json.getJSONObject(i).getString("id") +"               "+
                        json.getJSONObject(i).getString("ce") +"               "+
                        json.getJSONObject(i).getString("nombre") +"               "+
                        json.getJSONObject(i).getString("en") +"               "+
                        json.getJSONObject(i).getString("sa") +"               "+
                        json.getJSONObject(i).getString("fecha");


                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }






    public void cargaListado2(ArrayList<String> datos2){

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos2);
        ListView listado = (ListView) findViewById(R.id.listpro);
        listado.setAdapter(adaptador);
    }
    public String leer2(String id){
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?ce=" + id;
        HttpGet httpget = new HttpGet("http://192.168.43.87:80/www/android/otros/Getapro.php"+parametros);
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

    public String leer22(String id){
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?ce=" + id;
        HttpGet httpget = new HttpGet("http://192.168.43.87:80/www/android/otros/Getapro22.php"+parametros);
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

    public ArrayList<String> obtDatosJSON2(String response){
        ArrayList<String> listado= new ArrayList<String>();
        try {
            JSONArray json= new JSONArray(response);
            String texto="";
            for (int i=0; i<json.length();i++){
                texto =

                        json.getJSONObject(i).getString("id") +"               "+
                                json.getJSONObject(i).getString("ce")+"               "+
                                json.getJSONObject(i).getString("pro")+"                "+
                json.getJSONObject(i).getString("act")+"               "+
                json.getJSONObject(i).getString("hor") +"               "+
                        json.getJSONObject(i).getString("fecha");


                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }
}
