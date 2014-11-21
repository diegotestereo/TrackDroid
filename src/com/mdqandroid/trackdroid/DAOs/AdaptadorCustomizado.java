package com.mdqandroid.trackdroid.DAOs;

import java.util.ArrayList;
import java.util.Date;

import com.mdqandroid.trackdroid.Lay_Configuracion;
import com.mdqandroid.trackdroid.Lay_Dispositivo;
import com.mdqandroid.trackdroid.MainActivity;
import com.mdqandroid.trackdroid.R;
import com.mdqandroid.trackdroid.Objetos.DispositivosClase;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class AdaptadorCustomizado extends BaseAdapter
{
	private SQLHelperAdaptador dao;
	private ArrayList<DispositivosClase> dispositivos;
	private Activity ac;
	private int IP, Puerto, Entradas, Salidas,id_Dispositivo;
	private String observacion, Nombre;
	
	public AdaptadorCustomizado(ArrayList<DispositivosClase> reparacion, Activity ac)
	{
		this.dispositivos = reparacion;
		this.ac = ac;

	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dispositivos.size();
	}

	@Override
	public DispositivosClase getItem(int position) {
		// TODO Auto-generated method stub
		return dispositivos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


	// creo objeto contenedor
	static class ViewHolder
	{
		TextView text_Titulo;
		TextView text_Descripcion;
		ImageView imagen;
		RelativeLayout ll_row;
		Button btn_borrar, btn_editar;
		CheckBox CheckBox_AutoPull;
	}

	
	
	//***********************************************************////
	////////// alert dialog Eliminar/////////////////////////////
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final DispositivosClase item = getItem(position);

		ViewHolder holder;

		if (convertView == null)// es la primera vez
		{
			LayoutInflater li = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = li.inflate(R.layout.list_customizada, parent, false);

			// aca defino el objeto... lo instancio
			holder = new ViewHolder();
			holder.text_Titulo = (TextView) convertView.findViewById(R.id.tv_titulo);
			holder.text_Descripcion = (TextView) convertView.findViewById(R.id.tv_descripcion);
			holder.imagen = (ImageView) convertView.findViewById(R.id.imv_icono);
			holder.ll_row = (RelativeLayout) convertView.findViewById(R.id.ll_row);
			holder.btn_borrar = (Button) convertView.findViewById(R.id.btn_borrarReparacion);
			holder.btn_editar = (Button) convertView.findViewById(R.id.btn_editarReparacion);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text_Titulo.setText("Dispositivo: " + item.getNombre());
		holder.text_Descripcion.setText(item.getObservaciones());

		holder.ll_row.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// / para hacer no tactil la ventana

				Intent intento = new Intent(ac, Lay_Dispositivo.class);
				intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				id_Dispositivo = item.getId_Dispositivo();
				Nombre =item.getNombre();
				IP = item.getIP();
				Puerto = item.getPuerto();
				Entradas=item.getEntradas();
				Salidas=item.getSalidas();
				observacion = item.getObservaciones();
				

				intento.putExtra("Nombre", Nombre);
				intento.putExtra("IP", IP);
				intento.putExtra("Puerto", Puerto);
				intento.putExtra("Entradas", Entradas);
				intento.putExtra("Salidas", Salidas);
				intento.putExtra("observacion", observacion);
			
				ac.startActivity(intento);
			}
		});

		holder.btn_borrar.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(ac);

				dialog.setMessage("¿Eliminar Reparación?");
				dialog.setCancelable(false);
				dialog.setPositiveButton("Si", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{

						
						// LA BASE DE DATOS ESTA HARDCODEADA.. HAY QUE ARREGLARLO
						dao = new SQLHelperAdaptador(ac,ac.getString(R.string.DataBase), null, 1);

						dao.borrarDispositivo(id_Dispositivo);
					//	Toast.makeText(ac, "Reparacion " + item.getId_Dispositivo() + " Borrada !!! ", Toast.LENGTH_SHORT).show();

						//llamo a la misma asi se vuelve a setear el adapter
						//si, es algo primitivo pero es la forma mÃ¡s eficaz
						
						
						if (dao.recuperarCantidadReparaciones()==0){
							Intent intent = new Intent(ac, MainActivity.class); 
							
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);//limpio la pila de actividades
							
							ac.startActivity(intent);
							
							
						}else{
							

							Intent intent = new Intent(ac, Lay_Dispositivo.class); 
						
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);//limpio la pila de actividades
							
							ac.startActivity(intent);
						}
					

						Toast.makeText(ac, "BORRADO !!!", Toast.LENGTH_SHORT).show();
					}
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
					
						Toast.makeText(ac, "CANCELADO !!!", Toast.LENGTH_SHORT).show();

						dialog.cancel();
					}
				});
				dialog.show();
			
			
			}

		});

		holder.btn_editar.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intento = new Intent(ac, Lay_Configuracion.class);
				intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				Nombre =item.getNombre();
				IP = item.getIP();
				Puerto = item.getPuerto();
				Entradas=item.getEntradas();
				Salidas=item.getSalidas();
				observacion = item.getObservaciones();
				

				intento.putExtra("Nombre", Nombre);
				intento.putExtra("IP", IP);
				intento.putExtra("Puerto", Puerto);
				intento.putExtra("Entradas", Entradas);
				intento.putExtra("Salidas", Salidas);
				intento.putExtra("observacion", observacion);

				ac.startActivity(intento);

			}
		});

		return convertView;
	}

}
