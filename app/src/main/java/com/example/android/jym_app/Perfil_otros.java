package com.example.android.jym_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Perfil_otros extends AppCompatActivity {
    //IMAGEN
    public static  String URL;
    ImageView imageView;
    TextView imageView2;
    EditText id2,nombre2;
    String Id2, Nombre2;

//auto
    String ID2=null, NOMBRE2=null,DINERO=null;
    String IDDD=null, MARCA=null,MODELO=null, COLOR=null, PLACA=null,NOMBRED=null,LACCI=null, DESTINO=null,FALLAS=null,FECHAD=null;
   String Iddd,Marca,Modelo,Color,Placa,Nombred,Lacci,Destino,Fallas,Fechad;
    TextView IdddT,MarcaT,ModeloT,ColorT,PlacaT,NombredT,LacciT,DestinoT,FallasT,FechadT;
//rol
    String CODIGO,CEDU1,A,B,C,D,E,F;
//dialogo
private static final int DIALOGO_ALERTA = 1;

    EditText usuario, contrasea;
    String Usuario, Contrasea,debuelve,nombre,cedu,ape,sexo,cargo;
    TextView usuario1, contrasea2,nombreTV,ceduTV,apeTV,sexoTV,cargoTV;
    Context ctx=this;
    String USUARIOS=null, CONTRASEAS=null,CEDULA=null, NOMBRE=null, APELLIDO=null,EDAD=null,SEXO=null, DIRECCION=null,IMAGEN=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_otros);
        //FOTO

        String foto1 = getIntent().getStringExtra("foto1");


        URL = "http://192.168.43.87/www/imagen/"+foto1;
        imageView = (ImageView) findViewById(R.id.imafo);
        GetImageTask task = new GetImageTask();
        task.execute(new String[]{URL});
         final ImageButton bts=(ImageButton)findViewById(R.id.imageButton);
        final ImageButton bts2=(ImageButton)findViewById(R.id.imageButton2);
        final ImageButton bts3=(ImageButton)findViewById(R.id.imageButton3);





        usuario1 = (TextView) findViewById(R.id.textnom);
        contrasea2 = (TextView) findViewById(R.id.textEdad);

        nombreTV = (TextView) findViewById(R.id.textnom);
        sexoTV = (TextView) findViewById(R.id.textsexo);
        cargoTV = (TextView) findViewById(R.id.textcar);
        nombre = getIntent().getStringExtra("nombres");
        ape = getIntent().getStringExtra("apellidos");
        sexo = getIntent().getStringExtra("sexo");
        cargo = getIntent().getStringExtra("cargo");
        cedu = getIntent().getStringExtra("cedu");
        nombreTV.setText(nombre+" "+ape);
        sexoTV.setText(sexo);
        cargoTV.setText(cargo);

        Thread  tr = new Thread(){
            @Override
            public void run(){

                runOnUiThread(

                        new Runnable() {

                            @Override
                            public void run() {
                                BackGround1 b = new BackGround1();
                                b.execute(cedu);
                            }
                        });

            }

        };
        tr.start();

bts.setOnClickListener(new View.OnClickListener() {

    public void onClick(View v) {

        if(B.equals("1")) {

            Intent g = new Intent(ctx, conservicio.class);
            g.putExtra("usuariof", "1");
            startActivity(g);
        }
        else{
            showDialog(DIALOGO_ALERTA);
        }
    }
});
        bts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(F.equals("1")) {
                    Intent g = new Intent(ctx, conaudi.class);
                    g.putExtra("usuariof2", "1");
                    startActivity(g);
                }
                else{
                    showDialog(DIALOGO_ALERTA);
                }
            }
        });
        bts3.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                if(D.equals("1")) {
                    Intent g = new Intent(ctx, condinero.class);
                    g.putExtra("usuariof2", "1");
                    startActivity(g);
                }
                else {
                    showDialog(DIALOGO_ALERTA);
                }


            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialogo = null;
        switch(id)
        {
            case DIALOGO_ALERTA:
                dialogo = crearDialogoAlerta();
                break;
        }
        return dialogo;
    }
    private Dialog crearDialogoAlerta()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INFORMACIÓN");
        builder.setMessage("No posee permisos.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }


//FOTO
    private class GetImageTask extends AsyncTask<String, Void, Bitmap> {
        private ProgressDialog progreso;
        @Override protected void onPreExecute() {
            progreso = new ProgressDialog(Perfil_otros.this);
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
            imageView.setImageBitmap(result);

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




//rol
    class BackGround1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String cedula = params[0];
            String data = "";
            int tmp;


            try {


                URL url = new URL("http://192.168.43.87:80/www/android/login/loginrol.php");
                String urlParams =  "&cedula=" + cedula;

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
                CODIGO = user_data.getString("codigo");
                CEDU1 = user_data.getString("cedula");
                A=user_data.getString("a");
                B=user_data.getString("b");
                C=user_data.getString("c");
                D = user_data.getString("d");
                E=user_data.getString("e");
                F=user_data.getString("f");


            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }



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
                String urlParams =  "&chofer=" + chofer;

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
            k.putExtra("max(id)", IDDD);
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


}
