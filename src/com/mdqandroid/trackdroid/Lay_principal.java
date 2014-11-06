package com.mdqandroid.trackdroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lay_principal extends Activity {
	
	 DataOutputStream Salida;
	 DataInputStream Entrada;
	//Socket socketCliente;
	Mensaje_data mdata;
	Thread ThreadCliente;
	int puerto=5001;
	Button btn_conectar,btn_EnviarMensaje,btn_Desconectar;
	EditText edit_ipServer, edit_puerto,edit_mensajeCliente;
	TextView text_mensajeServer,text_Status;
	 ObjectOutputStream oos ;
	 ObjectInputStream ois;
	 Mensaje_data msgact ;
	 Socket sk ;
	
	
	
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lay_principal);
	Levantar_XML();
 	Botones();
 	  StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.
 	           Builder().permitNetwork().build());
}

private void Botones() {
	btn_conectar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			try {
				sk = new Socket("192.168.0.153",5001);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		}
	} );
	btn_Desconectar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		}
	});
	btn_EnviarMensaje.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String mensaje=edit_mensajeCliente.getText().toString();
			ejecutaCliente();
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

private void ejecutaCliente() {

	 //String ip="192.168.0.153";

	// int puerto=5001;

	// log(" socket " + ip + " " + puerto);

	 try {

	// Socket sk = new Socket(ip,puerto);

	 BufferedReader entrada = new BufferedReader(new InputStreamReader(sk.getInputStream()));

	 PrintWriter salida = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()),true);

	 log("enviando...");

	 salida.println("Hola Mundo");

	 log("recibiendo ... " + entrada.readLine());

	 //sk.close();

	 } catch (Exception e) {

	 log("error: " + e.toString());

	 }

	  }

private void log(String string) {

	text_mensajeServer.append(string + "\n");
	

	  }

/*
public class MyClientTask extends AsyncTask<Void, Void, Void> {
	  
	  String dstAddress;
	  int dstPort;
	  String response = "";
	  
	  MyClientTask(String addr, int port){
	   dstAddress = addr;
	   dstPort = port;
	  }

	  @Override
	  protected Void doInBackground(Void... arg0) {
	   
	   Socket socket = null;
	   
	   try {
	    socket = new Socket(dstAddress, dstPort);
	    
	    ByteArrayOutputStream byteArrayOutputStream = 
	                  new ByteArrayOutputStream(1024);
	    byte[] buffer = new byte[1024];
	    
	    int bytesRead;
	    InputStream inputStream = socket.getInputStream();
	    
	   
	             while ((bytesRead = inputStream.read(buffer)) != -1){
	                 byteArrayOutputStream.write(buffer, 0, bytesRead);
	                 response += byteArrayOutputStream.toString("UTF-8");
	             }

	   } catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    response = "UnknownHostException: " + e.toString();
	   } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    response = "IOException: " + e.toString();
	   }finally{
	    if(socket != null){
	     try {
	      socket.close();
	     } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
	    }
	   }
	   return null;
	  }

	  @Override
	  protected void onPostExecute(Void result) {
	   textResponse.setText(response);
	   super.onPostExecute(result);
	  }
	  
	 }
*/


	}

