package com.mdqandroid.trackdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Lay_dispositivos extends Activity{
	Button bton_disp1;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lay_dispositivos);
	LevantarXML();
	botones();
}

private void botones() {
	bton_disp1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intento = new Intent(Lay_dispositivos.this,Lay_principal.class);
			startActivity(intento);
		}
	});
	
}

private void LevantarXML() {
	bton_disp1=(Button) findViewById(R.id.btn_disp1);
	
}
}
