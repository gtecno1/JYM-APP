package com.example.android.jym_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Perfil_chof extends AppCompatActivity {
    //IMAGEN
     public static  String URL;

    ImageView imageView1;
    TextView imageView2;
    EditText id2,nombre2;
    String Id2, Nombre2;


    String ID2=null, NOMBRE2=null,DINERO=null;
    String IDDD=null, MARCA=null,MODELO=null, COLOR=null, PLACA=null,NOMBRED=null,LACCI=null, DESTINO=null,FALLAS=null,FECHAD=null;
   String Iddd,Marca,Modelo,Color,Placa,Nombred,Lacci,Destino,Fallas,Fechad;
    TextView IdddT,MarcaT,ModeloT,ColorT,PlacaT,NombredT,LacciT,DestinoT,FallasT,FechadT;


    EditText usuario, contrasea;
    String Usuario, Contrasea,debuelve,nombre,cedu,ape, edad, din;
    TextView usuario1, contrasea2,nombreTV,ceduTV,apeTV, edadTV, dinTV;
    Context ctx=this;
    String USUARIOS=null, CONTRASEAS=null,CEDULA=null, NOMBRE=null, APELLIDO=null,EDAD=null,SEXO=null, DIRECCION=null,IMAGEN=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_chof);
        //FOTO

       String foto1 = getIntent().getStringExtra("fot");


        URL = "http://192.168.43.87/www/imagen/"+foto1;

        imageView1 = (ImageView) findViewById(R.id.imafo);
        GetImageTask task = new GetImageTask();
        task.execute(new String[]{URL});
         final ImageButton bts=(ImageButton)findViewById(R.id.imageButton);
        final ImageButton bts2=(ImageButton)findViewById(R.id.imageButton2);
        final ImageButton bts3=(ImageButton)findViewById(R.id.imageButton3);





        usuario1 = (TextView) findViewById(R.id.textnom);
        contrasea2 = (TextView) findViewById(R.id.textEdad);

        nombreTV = (TextView) findViewById(R.id.textnom);
        edadTV = (TextView) findViewById(R.id.textEdad);
        dinTV = (TextView) findViewById(R.id.textView);
        nombre = getIntent().getStringExtra("nombre");
        ape = getIntent().getStringExtra("apellido");
        edad = getIntent().getStringExtra("edad");
        din = getIntent().getStringExtra("dinero");

        cedu = getIntent().getStringExtra("cedula");
        nombreTV.setText(nombre+" "+ape);
        edadTV.setText(edad);
        dinTV.setText(din);
        
        Thread  tr = new Thread(){

            public void run(){

                runOnUiThread(
                        new Runnable() {

                            @Override
                            public void run() {
                                estado();
                            }
                        });
            }

        };
        tr.start();


bts.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent g = new Intent(ctx, consercho.class);
        g.putExtra("ceduco", cedu);

        startActivity(g);
    }
});
        bts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g= new Intent(  ctx, condinecho.class);
                g.putExtra("ceduco", cedu);
                startActivity(g);
            }
        });
        bts3.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                BackGround3 b = new BackGround3();
                b.execute(cedu);

            }
        });
    }


//FOTO
    private class GetImageTask extends AsyncTask<String, Void, Bitmap> {
        private ProgressDialog progreso;
        @Override protected void onPreExecute() {
            progreso = new ProgressDialog(Perfil_chof.this);
            progreso.setMessage("Descargando...");
            progreso.setCancelable(false);
            progreso.show();
        }
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }
        protected void onPostExecute(Bitmap result) {
            progreso.dismiss();
            imageView1.setImageBitmap(result);

        }
        private Bitmap downloadImage(String url) {
            Bitmap bitmap1 = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
            try {
                stream = getHttpConnection(url);
                bitmap1 = BitmapFactory.decodeStream(stream, null, bmOptions);
                bitmap1 = ImageHelper.cropBitmapToSquare(bitmap1);
                bitmap1 = ImageHelper.getRoundedCornerBitmap(
                        bitmap1, 200);

                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap1;
        }
        private InputStream getHttpConnection(String urlString) throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
    class BackGround3 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String chofer = params[0];
            String data = "";
            int tmp;


            try {


                URL url = new URL("http://192.168.43.87:80/www/android/sercd.php");
                String urlParams =  "?chofer=" + chofer;

                HttpURLConnection ur = (HttpURLConnection) url.openConnection();
                ur.setDoOutput(true);
                OutputStream os = ur.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = ur.getInputStream();


                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                ur.disconnect();

                return data;


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err = null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                IDDD = user_data.getString("max(id)");
                MARCA = user_data.getString("marca");
                MODELO=user_data.getString("modelo");
                COLOR=user_data.getString("color");
                PLACA=user_data.getString("placa");
                NOMBRED = user_data.getString("nombre");
                LACCI=user_data.getString("Laccidente");
               DESTINO=user_data.getString("Ldestino");
                FALLAS=user_data.getString("Fallas");
                FECHAD=user_data.getString("fecha");

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }

            Intent k = new Intent(ctx, servidet.class);
            k.putExtra("max", IDDD);
            k.putExtra("marca", MARCA);
            k.putExtra("modelo", MODELO);
            k.putExtra("color", COLOR);
            k.putExtra("placa", PLACA);
            k.putExtra("nombre1", NOMBRED);
            k.putExtra("Laccidente", LACCI);
            k.putExtra("Ldestino", DESTINO);
            k.putExtra("Fallas", FALLAS);
            k.putExtra("fecha", FECHAD);




            startActivity(k);

        }
    }
    public void  estado() {

        Thread nt = new Thread() {
            @Override
            public void run() {



String Cedu;
                try {


                    final String res;

                    res = enviarGet(Cedu="44");


                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        };
        nt.start();
    }

    public String enviarGet(String cedu22) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?cedu=" + 44  ;

        HttpGet httpget = new HttpGet(
                "http://192.168.43.87:80/www/android/login/chofernoti.php" + parametros);
        try {
            response = httpClient.execute(httpget, localContext);

        } catch (Exception e) {

        }
        return response.toString();
    }
}
