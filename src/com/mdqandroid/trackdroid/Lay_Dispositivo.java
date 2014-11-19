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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Lay_Dispositivo extends Activity{
	
	//declaracion de variables
	 
	 BufferedReader entrada ;
	 PrintWriter salida ;
	 
	String mensajeExit="",SelectorGlobal=null;
	//Socket socketCliente;
	
	Thread ThreadCliente;
	int puerto=5001;
	Button btn_conectar,btn_EnviarMensaje,btn_Desconectar,btn_Prueba
	, btn_Sensor1, btn_Sensor2, btn_Sensor3, btn_Sensor4;
	EditText edit_ipServer, edit_puerto,edit_mensajeCliente;
	TextView text_mensajeServer,text_Status,textA1,textA2,textA3,textA4,textPrueba;
	ToggleButton toggleR1,toggleR2,toggleR3,toggleR4,toggleR5,toggleR6,toggleR7,toggleR8;
	 ObjectOutputStream oos ;
	 ObjectInputStream ois;
	String DecoTipo="";
	String DecoValor="";
	String Prueba="";
	Boolean DecoRelay=false;
	 Socket sk ;
	// clienteAsync Cliente;
	int scroll_amount;
	Boolean ExitSocket=false;
	
	
	//StringBuilder sb ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_dispositivo);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
		Levantar_XML();
		//sb= new StringBuilder();
		Botones();
		//conectar();
		TGbuttons();
		
	}

	private void TGbuttons() {
		// TODO Auto-generated method stub
		toggleR1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				String mensaje=null;
				if (isChecked){
    			mensaje="r1v1";}
				else{
					mensaje="r1v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
				
			}
		});
		
		toggleR2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				String mensaje=null;
				if (isChecked){
    			mensaje="r2v1";}
				else{
					mensaje="r2v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
				
			}
		});

		toggleR3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String mensaje=null;
				if (isChecked){
    			mensaje="r3v1";}
				else{
					mensaje="r3v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
			}
		});

		toggleR4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				String mensaje=null;
				if (isChecked){
    			mensaje="r4v1";}
				else{
					mensaje="r4v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
			}
		});

		toggleR5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String mensaje=null;
				if (isChecked){
    			mensaje="r5v1";}
				else{
					mensaje="r5v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
			}
		});

		toggleR6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String mensaje=null;
				if (isChecked){
    			mensaje="r6v1";}
				else{
					mensaje="r6v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
			}
		});

		toggleR7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String mensaje=null;
				if (isChecked){
    			mensaje="r7v1";}
				else{
					mensaje="r7v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);
			}
		});

		toggleR8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String mensaje=null;
				if (isChecked){
    			mensaje="r8v1";}
				else{
					mensaje="r8v0";
				}
				clienteAsync Cliente = new clienteAsync();
				Cliente.execute(mensaje);	
			}
		});

		
	}

	private void desconectar(){
	
		try {
			sk.close();
			btn_Desconectar.setEnabled(false);
			btn_conectar.setEnabled(true);
			btn_EnviarMensaje.setEnabled(false);
			text_Status.setText("Desconectado");
			edit_ipServer.setEnabled(true);
			edit_puerto.setEnabled(true);
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "No se pudo Desconectar al dispositivo", Toast.LENGTH_LONG).show();
		}  
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	private void conectar() {
		 String IpServidor =edit_ipServer.getText().toString();
			int puertito=Integer.parseInt(edit_puerto.getText().toString());
			
			try {
				sk = new Socket(IpServidor,puertito);
				btn_Desconectar.setEnabled(true);
				toggleR1.setEnabled(true);
				toggleR2.setEnabled(true);
				toggleR3.setEnabled(true);
				toggleR4.setEnabled(true);
				toggleR5.setEnabled(true);
				toggleR6.setEnabled(true);
				toggleR7.setEnabled(true);
				toggleR8.setEnabled(true);
				btn_conectar.setEnabled(false);
				btn_EnviarMensaje.setEnabled(true);
				text_Status.setText("Conectado a: "+IpServidor+" : "+puertito);
				edit_ipServer.setEnabled(false);
				edit_puerto.setEnabled(false);
				
				Toast.makeText(getApplicationContext(), "Conectado !", Toast.LENGTH_LONG).show();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "No se pudo conectar al dispositivo", Toast.LENGTH_LONG).show();
				finish();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "No se pudo conectar al dispositivo", Toast.LENGTH_LONG).show();
				finish();
			}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		}
		 
	@Override
	protected void onPause() {
	    	
	    	super.onPause();
	    	clienteAsync Cliente = new clienteAsync();
			Cliente.execute("q");
			Toast.makeText(getApplicationContext(), "Se desconecto...", Toast.LENGTH_LONG).show();
			finish();
	    
	    }
	    
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
		
	}
	
	private void Botones() {
		
	    	btn_conectar.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View v) {
	    			
	    			conectar();
	    			
	    		}
	    	} );
	    	
	    	btn_Desconectar.setOnClickListener(new OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    	
	   	    	// desconectar();
	    			
	    			clienteAsync Cliente = new clienteAsync();
	    			Cliente.execute("q");
	    		
	   	}
	    	});
	    	
	    	btn_EnviarMensaje.setOnClickListener(new OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			String mensaje=edit_mensajeCliente.getText().toString();
	    			mensajeExit=mensaje;
	    		//	Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
	    			clienteAsync Cliente = new clienteAsync();
	    			Cliente.execute(mensaje);
	    		
	    			
	    			
	    		}
	    	});
	    	 	
	    	btn_Prueba.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					DecodificaMensaje decomsg= new DecodificaMensaje();
					decomsg.execute(edit_mensajeCliente.getText().toString());
				}
			});
	    	
	    	btn_Sensor1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clienteAsync Cliente = new clienteAsync();
	    			Cliente.execute("a1");
					
				}
			});
	    	
	    	btn_Sensor2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clienteAsync Cliente = new clienteAsync();
	    			Cliente.execute("a2");
				}
			});
	    	
	    	btn_Sensor3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clienteAsync Cliente = new clienteAsync();
	    			Cliente.execute("a3");
				}
			});
	    	
	    	btn_Sensor4.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clienteAsync Cliente = new clienteAsync();
	    			Cliente.execute("a4");
				}
			});


	
	}

	public class clienteAsync extends AsyncTask<String, Void,Void>{
		
		 
	    	
	    	protected Void doInBackground(String... msg) {
	    		
	    		 String mensajito =msg[0];
	    		
	    		 Log.d("clienteAsync","entro");
	    		 try {
	    			 entrada = new BufferedReader(new InputStreamReader(sk.getInputStream()));
	    			
	    			
				} catch (Exception e) {
					// TODO: handle exception
				}
	    	
	    		 
	    		 try {
	    			 	
	    				 salida = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()),true);
	    				
	    				 salida.println(mensajito);// envia el mensaje
	    				
	    				
	    	 } catch (Exception e) {
	    		 	 }
	    		 
    		
	    		 return null;
	    	}
	    	
	     
	    	
	    	@Override
	      protected void onPostExecute(Void result) {
	    	   
	    	   super.onPostExecute(result);
	    	  
	    	 
	    	  try {
	    		text_mensajeServer.setText(entrada.readLine() + "\n");
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
	    //	Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
       DecodificaMensaje deco=new DecodificaMensaje();
       deco.execute(text_mensajeServer.getText().toString());
	    	  }

	    	
	    }

	 public class DecodificaMensaje extends AsyncTask<String, Void,String>{
		    	
		    	protected void onPreExecute(Void arg0) {
		    	   super.onPreExecute();
		    	 Log.d("deco", "n PREexecute");
		    	   DecoTipo="";
		    	   DecoValor="";
		    	}
		    	
		    	
		    	protected String doInBackground(String... msg) {
		    		
		    		 String selector =msg[0];
		    		 SelectorGlobal=msg[0];
		    		 DecoTipo = selector.substring(0,2);
		    		
		    		 if(selector.substring(0,1).equals("r")){
		    			 DecoValor = selector.substring(3,4);
		    			 if(DecoValor.equals("1"))
		    			 {DecoRelay=true;}
		    			 else{
		    				 DecoRelay=false;}
		    		 }else{
		    		DecoValor = selector.substring(3,selector.length());}
		    		
		    		return DecoTipo;
		    	}
		    	
		       protected Boolean onProgressUpdate() {
		    		// TODO Auto-generated method stub
		    		super.onProgressUpdate();
				       		
		    		
		    		
		    		
					return null;
		    	
		    		
		    	}
		    	
		    	@Override
		    	  protected void onPostExecute(String result) {
		    	   
		    	   super.onPostExecute(result);
		    	switch (result) {
					case "t1":textA1.setText(DecoValor);
						         	break;
					case "t2":textA2.setText(DecoValor);
						
						break;
					case "h1":textA3.setText(DecoValor);
						
						break;
					case "h2":textA4.setText(DecoValor);
						
						break;
					case "r1":toggleR1.setChecked(DecoRelay);
						
						break;
					case "r2":toggleR2.setChecked(DecoRelay);
						
						break;
					case "r3":toggleR3.setChecked(DecoRelay);
						
						break;
					case "r4":toggleR4.setChecked(DecoRelay);
						
						break;
					case "r5":toggleR5.setChecked(DecoRelay);
						
						break;
					case "r6":toggleR6.setChecked(DecoRelay);
						
						break;
					case "r7":toggleR7.setChecked(DecoRelay);
						
						break;
					case "r8":toggleR8.setChecked(DecoRelay);
						
						break;
					default:
						Toast.makeText(getApplicationContext(), "Error comando", Toast.LENGTH_SHORT).show();
						break;
					}

    	  
		    	  }

		    	
		    }
	    
	 private void Levantar_XML() {
	    	
	    	btn_conectar=(Button) findViewById(R.id.btn_Conectar);
	    	btn_EnviarMensaje=(Button) findViewById(R.id.btn_EnviarMensaje);
	    	btn_Desconectar=(Button) findViewById(R.id.btn_Desconectar);
	    	btn_Prueba=(Button) findViewById(R.id.btn_Prueba);
	    	btn_Sensor1=(Button) findViewById(R.id.btn_Sensor1);
	    	btn_Sensor2=(Button) findViewById(R.id.btn_Sensor2);
	    	btn_Sensor3=(Button) findViewById(R.id.btn_Sensor3);
	    	btn_Sensor4=(Button) findViewById(R.id.btn_Sensor4);
	    	
	    	
	    	edit_ipServer=(EditText)findViewById(R.id.edit_IPServer);
	    	edit_puerto=(EditText)findViewById(R.id.edit_puerto);
	    	edit_mensajeCliente=(EditText)findViewById(R.id.edit_MensajeCliente);
	    	text_mensajeServer=(TextView)findViewById(R.id.text_MensajeServer);
	    	text_Status=(TextView)findViewById(R.id.textStatus);
	    	textPrueba=(TextView)findViewById(R.id.textPrueba);
	    	
	    	textA1=(TextView)findViewById(R.id.textA1);
	    	textA2=(TextView)findViewById(R.id.textA2);
	    	textA3=(TextView)findViewById(R.id.textA3);
	    	textA4=(TextView)findViewById(R.id.textA4);
	    	
	    	toggleR1=(ToggleButton)findViewById(R.id.toggleR1);
	    	toggleR2=(ToggleButton)findViewById(R.id.toggleR2);
	    	toggleR3=(ToggleButton)findViewById(R.id.toggleR3);
	    	toggleR4=(ToggleButton)findViewById(R.id.toggleR4);
	    	toggleR5=(ToggleButton)findViewById(R.id.toggleR5);
	    	toggleR6=(ToggleButton)findViewById(R.id.toggleR6);
	    	toggleR7=(ToggleButton)findViewById(R.id.toggleR7);
	    	toggleR8=(ToggleButton)findViewById(R.id.toggleR8);
	    	
	    }
	    
	
}
