package com.mdqandroid.trackdroid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lay_principal extends Activity {
	
	DataOutputStream Salida;
	DataInputStream Entrada;
	Socket socketCliente;
	Mensaje_data mdata;
	Thread ThreadCliente;
	int puerto;
	Button btn_conectar,btn_EnviarMensaje,btn_Desconectar;
	EditText edit_ipServer, edit_puerto,edit_mensajeCliente;
	TextView text_mensajeServer,text_Status;
	 ObjectOutputStream oos ;
	 ObjectInputStream ois;
	 Mensaje_data msgact ;
	
	
	
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lay_principal);
	Levantar_XML();

	Botones();
	
}

private void Botones() {
	btn_conectar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Connect();
		}
	} );
	btn_Desconectar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Disconnect();
			  
		}
	});
	btn_EnviarMensaje.setOnClickListener(new OnClickListener() {
		 String mensaje=edit_mensajeCliente.getText().toString();
		@Override
		public void onClick(View v) {
			Snd_txt_Msg(mensaje);
		}
	});
	
}


private void Levantar_XML() {
	
	btn_conectar=(Button) findViewById(R.id.btn_Conectar);
	btn_EnviarMensaje=(Button) findViewById(R.id.btn_EnviarMensaje);
	btn_Desconectar=(Button) findViewById(R.id.btn_Desconectar);
	
	edit_ipServer=(EditText)findViewById(R.id.edit_IPServer);
	edit_puerto=(EditText)findViewById(R.id.edit_Puerto);
	edit_mensajeCliente=(EditText)findViewById(R.id.edit_MensajeCliente);
	
	text_mensajeServer=(TextView)findViewById(R.id.text_MensajeServer);
	text_Status=(TextView)findViewById(R.id.text_Status);
}


//Conectamos
	public void Connect() {
		//Obtengo datos ingresados en campos
		String IP = edit_ipServer.getText().toString();
		 puerto = Integer.valueOf(edit_puerto.getText().toString());

		try {//creamos sockets con los valores anteriores
			socketCliente = new Socket("192.168.0.107", 5001);
			text_Status.setText("Conectado");
			
			
		} catch (Exception e) {
			text_Status.setText("Error al Conectar");
			
		}
	}


//Metodo de desconexion
public void Disconnect() {
	try {

		socketCliente.close();

		text_Status.setText("Desconectado");
	} catch (Exception e) {

		text_Status.setText("error al desconectar");
	}


}

public void Snd_txt_Msg(String txt) {

	Mensaje_data mensaje = new Mensaje_data();
	//seteo en texto el parametro  recibido por txt
	mensaje.texto = txt;
	Snd_Msg(mensaje);
	
}


public boolean Snd_Msg(Mensaje_data msg) {

	try {
		//Accedo a flujo de salida
		oos = new ObjectOutputStream(socketCliente.getOutputStream());
		
		Mensaje_data mensaje = new Mensaje_data();

		if (socketCliente.isConnected())// si la conexion continua
		{
			//lo asocio al mensaje recibido
			mensaje = msg;
			//Envio mensaje por flujo
			oos.writeObject(mensaje);
			//envio ok
			return true;

		} else {//en caso de que no halla conexion al enviar el msg
			//Set_txtstatus("Error...", 0);//error
			return false;
		}

	} catch (IOException e) {// hubo algun error
		Log.e("Snd_Msg() ERROR -> ", "" + e);

		return false;
	}
}

}
