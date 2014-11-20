package com.mdqandroid.trackdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Lay_Configuracion extends Activity{
	Button btn_GuardarConfig;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_configuracion);
		LevantarXML();
		Botones();
		
	}

	private void Botones() {
		btn_GuardarConfig.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	private void LevantarXML() {
		btn_GuardarConfig=(Button) findViewById(R.id.btn_GuardarConfig);
		
	}

}
