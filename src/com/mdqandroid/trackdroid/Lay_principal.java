package com.mdqandroid.trackdroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lay_principal extends Activity {
	
		 
	 BufferedReader entrada ;
	 PrintWriter salida ;
	String mensajeExit="";
	//Socket socketCliente;
	
	Thread ThreadCliente;
	int puerto=5001;
	Button btn_conectar,btn_EnviarMensaje,btn_Desconectar;
	EditText edit_ipServer, edit_puerto,edit_mensajeCliente;
	TextView text_mensajeServer,text_Status;
	 ObjectOutputStream oos ;
	 ObjectInputStream ois;
	
	 Socket sk ;
	// clienteAsync Cliente;
	int scroll_amount;
	Boolean ExitSocket=false;
	
	
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lay_dispositivo);
	Levantar_XML();
 	Botones();
 	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.
 	Builder().permitNetwork().build());
 	scroll_amount=8;
 	
   
}

 
 @Override
 	protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	
	
}
    
 
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    
    }
 
 
	private void Botones() {
	btn_conectar.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			String IpServidor =edit_ipServer.getText().toString();
			int puertito=Integer.parseInt(edit_puerto.getText().toString());
			
			try {
				sk = new Socket(IpServidor,puertito);
				btn_Desconectar.setEnabled(true);
				btn_conectar.setEnabled(false);
				btn_EnviarMensaje.setEnabled(true);
				text_Status.setText("Conectado a:"+IpServidor+":"+puertito);
				edit_ipServer.setEnabled(false);
				edit_puerto.setEnabled(false);
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
			String mensaje="q";
			mensajeExit=mensaje;
			 
		}
	});
	btn_EnviarMensaje.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String mensaje=edit_mensajeCliente.getText().toString();
			mensajeExit=mensaje;
		//	ejecutaCliente();
			clienteAsync Cliente = new clienteAsync();
			Cliente.execute(mensaje);
		
			
			
		}
	});
	
}


	private void Levantar_XML() {
	
	//btn_conectar=(Button) findViewById(R.id.btn_Conectar);
	btn_EnviarMensaje=(Button) findViewById(R.id.btn_EnviarMensaje);
//	btn_Desconectar=(Button) findViewById(R.id.btn_Desconectar);
	
	edit_ipServer=(EditText)findViewById(R.id.edit_IPServer);
	//edit_puerto=(EditText)findViewById(R.id.edit_Puerto1);
	edit_mensajeCliente=(EditText)findViewById(R.id.edit_MensajeCliente);
	
	text_mensajeServer=(TextView)findViewById(R.id.text_MensajeServer);
	//text_Status=(TextView)findViewById(R.id.text_Status);
}

	public class clienteAsync extends AsyncTask<String, Boolean,Void>{
	
	protected void onPreExecute(Void arg0) {
	   super.onPreExecute();
	   Log.v("clienteAsync","on preexecute");
	   Toast.makeText(getApplicationContext(), "on preexecute", Toast.LENGTH_SHORT).show();
	  }
	
	
	protected Void doInBackground(String... msg) {
		 String mensajito =msg[0];
		 try {
			 	 entrada = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				 salida = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()),true);
				
				 salida.println(mensajito);
				
				
	 } catch (Exception e) {
		 try {
			text_mensajeServer.append(entrada.readLine() + "\n");
			if(text_mensajeServer.getLineCount() > 5){
                scroll_amount = scroll_amount + text_mensajeServer.getLineHeight();
                text_mensajeServer.scrollTo(0, scroll_amount);
            }

			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				 }
		 
	 
		return null;
	}
	
   protected Boolean onProgressUpdate() {
		// TODO Auto-generated method stub
		super.onProgressUpdate();
		
		if(mensajeExit.equals("exit")){
			ExitSocket=true;
			}else{ExitSocket=false;}
		
		return ExitSocket;
		
		
		
	}
	
	@Override
	  protected void onPostExecute(Void result) {
	   
	   super.onPostExecute(result);
	  try {
		text_mensajeServer.append(entrada.readLine() + "\n");
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
		if(mensajeExit.equals("q")){
			try {
				sk.close();
				btn_Desconectar.setEnabled(false);
				btn_conectar.setEnabled(true);
				btn_EnviarMensaje.setEnabled(false);
				text_Status.setText("Desconectado");
				edit_ipServer.setEnabled(true);
				edit_puerto.setEnabled(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	  
	  
	  }
	


	
	
}


}

