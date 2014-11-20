package com.mdqandroid.trackdroid.DAOs;

import java.util.ArrayList;

import com.mdqandroid.trackdroid.Objetos.DispositivosClase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLHelperAdaptador extends SQLiteOpenHelper
{

	public SQLHelperAdaptador(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		// Creo la tabla Dispositivos
		db.execSQL("CREATE TABLE Tabla_Dispositivos (id_Dispositivo INTEGER PRIMARY KEY ,Nombre TEXT"
				+ ",IP INT,Puerto INT,Entradas INT,Salidas INT,observaciones TEXT)");

		
		// / defaults valores

		db.execSQL("INSERT INTO Tabla_Dispositivos (Nombre) VALUES ('Ejemplo')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (IP) VALUES ('192168000005')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (Puerto) VALUES ('5001')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (Entradas) VALUES ('4')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (Salidas) VALUES ('8')");

	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		// borro tabla
		db.execSQL("DROP TABLE IF EXISTS Tabla_Dispositivos");
		// Creo la Tabla_Dispositivos
		db.execSQL("CREATE TABLE Tabla_Dispositivos (id_Dispositivo INTEGER PRIMARY KEY ,Nombre TEXT"
				+ ",IP INT,Puerto INT,Entradas INT,Salidas INT,observaciones TEXT)");

		// / defaults valores

		db.execSQL("INSERT INTO Tabla_Dispositivos (Nombre) VALUES ('Ejemplo')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (IP) VALUES ('192168000005')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (Puerto) VALUES ('5001')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (Entradas) VALUES ('4')");
		db.execSQL("INSERT INTO Tabla_Dispositivos (Salidas) VALUES ('8')");
	
	}

	// ***********************************************************************************************************
	// /////////////////// INSERTAR EN TABLAS
	// //////////////////////////////////////////////////////////////////7

	public void insertarDispositivos(String Nombre, int IP, int Puerto, int Entradas, int Salidas, String observaciones)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("INSERT INTO Tabla_Dispositivos (Nombre,IP,Puerto,Entradas,Salidas, observaciones) " + "VALUES ('" + Nombre + "',"
				+ IP + "," + Puerto + "," + Entradas + "," + Salidas + ",'" + observaciones + "' )");
		baseDatos.close();
	}

	public void actualizarDispositivos(String Nombre, int IP, int Puerto, int Entradas, int Salidas, String observaciones)
	{

		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("UPDATE Tabla_Dispositivos (Nombre,IP,Puerto,Entradas,Salidas, observaciones) " + "VALUES ('" + Nombre + "',"
				+ IP + "," + Puerto + "," + Entradas + "," + Salidas + ",'" + observaciones + "' )");
		baseDatos.close();
	}

	// ////////////////////FIN INSERTAR EN TABLAS
	// //////////////////////////////////////////////////////////////////7

	
	// ////////////////// RECUPERAR CANTIDAD FILAS
	// TABLAS///////////////////////////////////////////////////////

	public int recuperarCantidadReparaciones()
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Tabla_Dispositivos";
		Cursor cursor = baseDatos.rawQuery(sql, null);
		int cantidad = cursor.getCount();
		cursor.close();
		baseDatos.close();
		return cantidad;
	}

	
	// /////////////////FIN RECUPERAR CANTIDAD FILAS

	// /////////////////////// BORRAR FILA TABLAS
	public void borrarReparacion(int id_Dispositivo)
	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		baseDatos.execSQL("DELETE FROM Tabla_Dispositivos WHERE id_Dispositivo = " + id_Dispositivo);
		baseDatos.close();
	}

	// /////////////////////////////////////FIN BORRAR FILA
	// TABLAS/////////////////////////////////

	// ///////////////////////////////////RECUPERAR DATOS DE TABLAS

		public ArrayList<DispositivosClase> recuperarReparaciones()

	{
		SQLiteDatabase baseDatos = getWritableDatabase();
		 String sql = "SELECT * FROM Tabla_Reparaciones";
	//	String sql = "SELECT * FROM Tabla_Reparaciones ORDER BY serial"; // name
																			// LIKE
																			// '%LIM%'

		Cursor cursor = baseDatos.rawQuery(sql, null);
		ArrayList<DispositivosClase> reparacionArray = new ArrayList<DispositivosClase>();

		while (cursor.moveToNext())
		{
			DispositivosClase oDispositivo = new DispositivosClase();
			
			oDispositivo.setId_Dispositivo(cursor.getInt(0));
			oDispositivo.setNombre(cursor.getString(1));
			oDispositivo.setIP(cursor.getInt(2));
			oDispositivo.setPuerto(cursor.getInt(3));
			oDispositivo.setEntradas(cursor.getInt(4));
			oDispositivo.setSalidas(cursor.getInt(5));
			oDispositivo.setObservaciones(cursor.getString(6));
			reparacionArray.add(oDispositivo);
		}
		cursor.close();
		baseDatos.close();
		return reparacionArray;
	}

	

}
