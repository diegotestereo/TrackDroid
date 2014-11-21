package com.mdqandroid.trackdroid.Objetos;

public class DispositivosClase {
	
	
	int IP,Puerto,Entradas,Salidas,id_Dispositivo;
	String Nombre,Observaciones;
		
	public int getId_Dispositivo() {
		return id_Dispositivo;
	}
	public void setId_Dispositivo(int id_dispositivo) {
		this.id_Dispositivo = id_dispositivo;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.Observaciones = observaciones;
	}
	public int getIP() {
		return IP;
	}
	public void setIP(int iP) {
		this.IP = iP;
	}
	public int getPuerto() {
		return Puerto;
	}
	public void setPuerto(int puerto) {
		this.Puerto = puerto;
	}
	public int getEntradas() {
		return Entradas;
	}
	public void setEntradas(int entradas) {
		this.Entradas = entradas;
	}
	public int getSalidas() {
		return Salidas;
	}
	public void setSalidas(int salidas) {
		this.Salidas = salidas;
	}

	

}
