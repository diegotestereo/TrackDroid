package com.mdqandroid.trackdroid;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.net.*;
import java.io.*;


import com.mdqandroid.trackdroid.DAOs.SQLHelperAdaptador;
import com.mdqandroid.trackdroid.Objetos.DispositivosClase;


public class MainActivity extends Activity {
	Button bton_disp1,bton_disp2,btn_Agregar;
	ListView listView_Dispositivos;
	SQLHelperAdaptador dao;
	ProgressDialog progress;
	ArrayAdapter<String> adaptadorDispositivos;
	DispositivosClase oDispositivos = new DispositivosClase();

	// final Context context = this;
	 
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	LevantarXML();
	botones();
	setAdaptadores();
}

	private void setAdaptadores(){
		dao = new SQLHelperAdaptador(MainActivity.this, getString(R.string.DataBase), null, 1);
		// los adaptadores recuperan el listado de nombres que luego se asignan
	}


	private void botones() {
	
	bton_disp1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intento = new Intent(MainActivity.this,Lay_Dispositivo.class);
			startActivity(intento);
		}
	});
	
	bton_disp1.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			Intent intento = new Intent(MainActivity.this,Lay_Configuracion.class);
			int Disp=1;
			intento.putExtra("Nombre", Disp);
			startActivity(intento);
			
			return false;
		}
	});
	
	bton_disp2.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			Intent intento = new Intent(MainActivity.this,Lay_Configuracion.class);
			int Disp=2;
			intento.putExtra("Nombre", Disp);
			startActivity(intento);
			return false;
		}
	});
	
	
	btn_Agregar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intento = new Intent(MainActivity.this,Lay_Configuracion.class);
			startActivity(intento);
		}
	});
	
	
	
}

	private void LevantarXML() {
	//Botones
	bton_disp1=(Button) findViewById(R.id.btn_disp1);
	bton_disp2=(Button) findViewById(R.id.btn_disp2);
	btn_Agregar=(Button) findViewById(R.id.btn_Agregar);
	//Lista
	listView_Dispositivos=(ListView) findViewById(R.id.listView_Dispositivos);
	
}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
