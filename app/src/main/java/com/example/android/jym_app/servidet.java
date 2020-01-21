package com.example.android.jym_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class servidet extends AppCompatActivity {


    String ID2=null, NOMBRE2=null,DINERO=null;
    String IDDD=null, MARCA=null,MODELO=null, COLOR=null, PLACA=null,NOMBRED=null,LACCI=null, DESTINO=null,FALLAS=null,FECHAD=null;
    String Iddd,Marca,Modelo,Color,Placa,Nombred,Lacci,Destino,Fallas,Fechad;
    TextView IdddT,MarcaT,ModeloT,ColorT,PlacaT,NombredT,LacciT,DestinoT,FallasT,FechadT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidet);

        IdddT = (TextView) findViewById(R.id.textcod);
        NombredT = (TextView) findViewById(R.id.textpro);
        MarcaT = (TextView) findViewById(R.id.textView4);
        ColorT = (TextView) findViewById(R.id.textView7);
        ModeloT = (TextView) findViewById(R.id.textView5);
        PlacaT = (TextView) findViewById(R.id.textView10);
        LacciT = (TextView) findViewById(R.id.textView15);
        DestinoT = (TextView) findViewById(R.id.textView16);

        Iddd = getIntent().getStringExtra("max");
        Marca = getIntent().getStringExtra("marca");
        Modelo = getIntent().getStringExtra("modelo");
        Color = getIntent().getStringExtra("color");
        Placa = getIntent().getStringExtra("placa");
        Nombred = getIntent().getStringExtra("nombre1");
        Lacci = getIntent().getStringExtra("Laccidente");
        Destino = getIntent().getStringExtra("Ldestino");
       Fallas = getIntent().getStringExtra("Fallas");
        Fechad = getIntent().getStringExtra("fecha");

        IdddT.setText("CODIGO DE SERVICIO: "+Iddd);
        NombredT.setText("PROPIETARIO: "+Nombred);
        MarcaT.setText("MARCA: "+Marca);
        ColorT.setText("COLOR: "+Color);
        ModeloT.setText("MODELO: "+Modelo);
        PlacaT.setText("PLACA: "+Placa);
        LacciT.setText("LUGAR DE ACCIDENTE: "+Lacci);
        DestinoT.setText("DESTINO: "+Destino);
    }

}
