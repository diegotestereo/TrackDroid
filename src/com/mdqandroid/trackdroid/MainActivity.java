package com.mdqandroid.trackdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class MainActivity extends Activity {
	Button bton_disp1,bton_disp2;
	 final Context context = this;
	 
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	LevantarXML();
	botones();
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
			Intent intento = new Intent(MainActivity.this,Lay_Preferencias.class);
			startActivity(intento);
			return false;
		}
	});
	
bton_disp2.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			return false;
		}
	});
	
}

private void LevantarXML() {
	bton_disp1=(Button) findViewById(R.id.btn_disp1);
	bton_disp2=(Button) findViewById(R.id.btn_disp2);
	
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
