package com.example.android.jym_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class conservicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conservicio);

        Thread  tr = new Thread(){

            public void run(){


                final String Resultado = leer2("");
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
    public void  main_Bus(View view){
        Thread  tr = new Thread(){

            public void run(){
                EditText ieee=(EditText) findViewById(R.id.Itxt);

                final String Resultado = leer(ieee.getText().toString());
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
        ListView listado = (ListView) findViewById(R.id.listservicio);
        listado.setAdapter(adaptador);

    }
   public void main_actu(View viw){
       Thread  tr = new Thread(){

           public void run(){
               ;

               final String Resultado = leer2("");
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
    public String leer(String id1){


            HttpClient cliente = new DefaultHttpClient();
            HttpContext contexto = new BasicHttpContext();
        String parametros = "?id=" + id1;
            HttpGet httpget = new HttpGet("http://192.168.43.87:80/www/android/otros/Getservicio.php"+parametros);
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

    public String leer2(String id1){


        HttpClient cliente = new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        String parametros = "?id=" + id1;
        HttpGet httpget = new HttpGet("http://192.168.43.87:80/www/android/otros/Getservicioactu.php"+parametros);
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
                texto =         json.getJSONObject(i).getString("id") +"               "+
                        json.getJSONObject(i).getString("seguro") +"               "+
                        json.getJSONObject(i).getString("marca") +"               "+
                        json.getJSONObject(i).getString("modelo") +"               "+
                        json.getJSONObject(i).getString("color") +"               "+

                        json.getJSONObject(i).getString("placa") +"               "+
                        json.getJSONObject(i).getString("nombre") +"               "+
                        json.getJSONObject(i).getString("telefono") +"               "+
                        json.getJSONObject(i).getString("Laccidente") +"               "+
                        json.getJSONObject(i).getString("Ldestino");




                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }

}
