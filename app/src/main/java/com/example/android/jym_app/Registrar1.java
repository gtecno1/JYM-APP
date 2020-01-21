package com.example.android.jym_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Registrar1 extends AppCompatActivity {
    EditText usuario, contrasea, cedu;
    String Usuario,Contrasea, Cedu;
   String CEDU="null";
    Context ctx=this;


	private static final int DIALOGO_ALERTA = 1;
	private static final int DIALOGO_ALERTA2 = 2;
	private static final int DIALOGO_ALERTA3 = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar1);
       


    }
   
    public void  main_guardasd(View view) {
		Thread nt = new Thread() {
			@Override
			public void run() {
				String Activo="no",Noti="no";
				 usuario = (EditText) findViewById(R.id.txtusuario1);
        contrasea = (EditText) findViewById(R.id.contra34);
        cedu = (EditText) findViewById(R.id.tcdu);
Cedu=cedu.getText().toString();
				try {

					final String res;
					
						res = enviarGet(cedu.getText().toString(), usuario.getText().toString(), contrasea.getText().toString(),Activo,Noti);

					BackGroundotros b = new BackGroundotros();
					b.execute(Cedu);


				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		nt.start();
	}

	public String enviarPost(String cedu, String usuario, String contrasea,String activo,String noti) {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(
				"http://192.168.43.87:80/Android/login/PutData1.php");
		HttpResponse response = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(new BasicNameValuePair("cedu", cedu));
			params.add(new BasicNameValuePair("usuario", usuario));
			params.add(new BasicNameValuePair("contasea", contrasea));
			params.add(new BasicNameValuePair("activo", activo));
			params.add(new BasicNameValuePair("noti", noti));
		
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			response = httpClient.execute(httpPost, localContext);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response.toString();

	}
	public String enviarGet(String cedu, String usuario, String contrasea,String activo,String noti) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpResponse response = null;
		String parametros = "?cedu=" + cedu + "&usuario=" + usuario
				+ "&contrasea=" + contrasea +"&activo=" + activo
				+ "&noti=" + noti ;

		HttpGet httpget = new HttpGet(
				"http://192.168.43.87:80/www/android/login/PutData1.php" + parametros);
		try {
			response = httpClient.execute(httpget, localContext);

		} catch (Exception e) {

		}
		return response.toString();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_inicio, menu);
		return true;
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
		builder.setMessage("Ya esta registrado!!");
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
		builder.setMessage("Su cedula no esta en sistema!!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		return builder.create();
	}






	class BackGroundotros extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String cedu = params[0];

			String data = "";
			int tmp;


			try {


				URL url = new URL("http://192.168.43.87:80/www/android/login/comprocho.php");
				String urlParams = "&cedu=" + cedu ;

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
				CEDU = user_data.getString("cedu");


			} catch (JSONException e) {
				e.printStackTrace();
				err = "Exception: " + e.getMessage();
			}
			if(CEDU.equals("null")){
				showDialog(DIALOGO_ALERTA3);
			}else {

				Intent i = new Intent(ctx, Inicio.class);
				i.putExtra("usuarDF", 1);
				startActivity(i);
				showDialog(DIALOGO_ALERTA2);
			}
		}
	}


}
