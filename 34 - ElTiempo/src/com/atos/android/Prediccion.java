package com.atos.android;

public class Prediccion {
	public String temperaturaMaxima;
	public String temperaturaMinima;
	public String cielo;
	public String icono;
	public String fecha;
	public int xMapa;
	public int yMapa;
	
	public String toString() {
		return " TMin = "+temperaturaMinima+" TMax = "+temperaturaMaxima+" cielo = "+cielo;
	}
}
