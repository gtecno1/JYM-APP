package com.example.android.jym_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Inicio extends AppCompatActivity {
    Animation giro;
ImageView logo,c1,c2,c3;


    EditText usuario, contrasea,id2,nombre2,foto;
    String Usuario, Contrasea,Id2, Nombre2,debuelve;
    int a=0;

    private static final int DIALOGO_ALERTA = 1;
    private static final int DIALOGO_ALERTA2 = 2;
    private static final int DIALOGO_ALERTA3 = 3;
    Context ctx=this;
    String USUARIOS="null", CONTRASEAS="null",ID2=null, NOMBRE2=null,CEDULA=null,NOMBRE=null, APELLIDO=null,EDAD=null,SEXO=null, DIRECCION=null,DINERO=null,IMAGEN=null,FOTO=null,FOTO1=null;
    String CARGO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        final RadioButton chofers = (RadioButton)findViewById(R.id.racho);
        final RadioButton otross = (RadioButton)findViewById(R.id.raotro);
       // final Button bre = (Button)findViewById(R.id.btr);
        logo = (ImageView) findViewById(R.id.imagePRE);
        c1 = (ImageView) findViewById(R.id.imageView3);
        c2 = (ImageView) findViewById(R.id.imageView4);
        c3 = (ImageView) findViewById(R.id.imagered);

        usuario = (EditText) findViewById(R.id.usu);
        contrasea = (EditText) findViewById(R.id.cont);
        id2 = (EditText) findViewById(R.id.usu);
        nombre2 = (EditText) findViewById(R.id.cont);

        Animation td = AnimationUtils.loadAnimation(this,R.anim.traslacion_derecha);
        td.setFillAfter(true);
        logo.startAnimation(td);
        Animation t1 = AnimationUtils.loadAnimation(this,R.anim.traslacion_bajar);
        t1.setFillAfter(true);
        c1.startAnimation(t1);
        Animation t2 = AnimationUtils.loadAnimation(this,R.anim.traslacion_derecha_bajar);
        t2.setFillAfter(true);
        c2.startAnimation(t2);
        Animation t3 = AnimationUtils.loadAnimation(this,R.anim.traslacion_subir_isqui);
        t3.setFillAfter(true);
        c3.startAnimation(t3);
       // usuario.append("\n Texto añadido");


        otross.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a = 2;
                    chofers.setChecked(false);

                }
            }
        });
        chofers.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a=1;
                    otross.setChecked(false);
                }

            }
        });
    }
    public void main_register(View v){
        startActivity(new Intent(this,Registrar1.class));
    }
    public void main_login(View v){
        usuario.getText().toString();
        contrasea.getText().toString();

        if(a==1 && a!=0) {
            Usuario = usuario.getText().toString();
            Contrasea = contrasea.getText().toString();
            if((Usuario.isEmpty())||(Contrasea.isEmpty())){
                showDialog(DIALOGO_ALERTA2);
            }else {
                Usuario = usuario.getText().toString();
                Contrasea = contrasea.getText().toString();

                BackGround b = new BackGround();
                b.execute(Usuario, Contrasea);
            }
        }
        if(a==2 && a!=0) {
            Usuario = usuario.getText().toString();
            Contrasea = contrasea.getText().toString();
            if((Usuario.isEmpty())||(Contrasea.isEmpty())){
                showDialog(DIALOGO_ALERTA2);
            }else {
                Usuario = usuario.getText().toString();
                Contrasea = contrasea.getText().toString();
                BackGroundotros b = new BackGroundotros();
                b.execute(Usuario, Contrasea);
            }
        }
if(a==0) {
    showDialog(DIALOGO_ALERTA);

}
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialogo = null;

        switch(id)
        {
            case DIALOGO_ALERTA:
                dialogo = crearDialogoAlerta();
                break;

            case DIALOGO_ALERTA2:
                dialogo = crearDialogoAlerta2();
                break;

            case DIALOGO_ALERTA3:
                dialogo = crearDialogoAlerta3();
                break;

        }

        return dialogo;
    }
    private Dialog crearDialogoAlerta()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("INFORMACIÓN");
        builder.setMessage("Por favor seleccione un rol.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
    private Dialog crearDialogoAlerta2()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("INFORMACIÓN");
        builder.setMessage("Por favor llene todos los campos.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
    private Dialog crearDialogoAlerta3()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("INFORMACIÓN");
        builder.setMessage("Usuario o contraseña invalida.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String contrasea = params[1];
            String data = "";
            int tmp;


            try {


                URL url = new URL("http://192.168.43.87:80/www/android/login/login.php");
                String urlParams = "usuario=" + usuario + "&contrasea=" + contrasea;

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
               USUARIOS = user_data.getString("usuario");
               CONTRASEAS = user_data.getString("contrasea");
               CEDULA = user_data.getString("cedu");

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
            if((USUARIOS.equals("null"))||(CONTRASEAS.equals("null"))){
                showDialog(DIALOGO_ALERTA3);
            }else {

                BackGround2 c = new BackGround2();
                c.execute(CEDULA);
            }





           // startActivity(i);

        }
    }
    class BackGround2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String contrasea2 = params[0];
            String data = "";
            int tmp;


            try {


                URL url = new URL("http://192.168.43.87:80/www/android/persona.php");
                String urlParams =  "&cedula=" + contrasea2;

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
                ID2 = user_data.getString("id");
                NOMBRE2 = user_data.getString("nombres");
                APELLIDO=user_data.getString("apellidos");
                EDAD=user_data.getString("edad");
                DINERO=user_data.getString("dineroCh");
               FOTO=user_data.getString("nofo");

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }

            if((USUARIOS.equals("null"))||(CONTRASEAS.equals("null"))){
                showDialog(DIALOGO_ALERTA3);
            }else {
                Intent k = new Intent(ctx, Perfil_chof.class);
                k.putExtra("usuario", USUARIOS);
                k.putExtra("contrasea", CONTRASEAS);
                k.putExtra("id", ID2);
                k.putExtra("nombre", NOMBRE2);
                k.putExtra("cedu", CEDULA);
                k.putExtra("apellido", APELLIDO);
                k.putExtra("edad", EDAD);
                k.putExtra("dinero", DINERO);
                k.putExtra("fot", FOTO);


                startActivity(k);
            }
        }
    }



    class BackGroundotros extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String contrasea = params[1];
            String data = "";
            int tmp;


            try {


                URL url = new URL("http://192.168.43.87:80/www/android/login/login2.php");
                String urlParams = "usuario=" + usuario + "&contrasea=" + contrasea;

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
                USUARIOS = user_data.getString("usuario");
                CONTRASEAS = user_data.getString("contrasea");
                CEDULA = user_data.getString("cedula");
                NOMBRE2 = user_data.getString("nombres");
                APELLIDO = user_data.getString("apellidos");
                CARGO = user_data.getString("cargo");
                SEXO = user_data.getString("sexo");
                FOTO1 = user_data.getString("nofo");

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
            if((USUARIOS.equals("null"))||(CONTRASEAS.equals("null"))){
                showDialog(DIALOGO_ALERTA3);
            }else {
                Intent i = new Intent(ctx, Perfil_otros.class);
                i.putExtra("usuario", USUARIOS);
                i.putExtra("contrasea", CONTRASEAS);
                i.putExtra("nombres", NOMBRE2);
                i.putExtra("cargo", CARGO);
                i.putExtra("cedu", CEDULA);
                i.putExtra("apellidos", APELLIDO);
                i.putExtra("sexo", SEXO);
                i.putExtra("foto1", FOTO1);


                startActivity(i);
            }
        }
    }
}
