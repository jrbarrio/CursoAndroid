package com.atos.android.p34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicioWebAEMET {
	
	public static String consultarPronostico(String localidad) throws IOException {
		URL url = new URL("http://www.aemet.es/xml/municipios/localidad_" + localidad + ".xml");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));		
		while (true) {
			String linea = br.readLine();
			if (linea == null) {
				break;
			}
			sb.append(linea);
			sb.append("\n");
		} 
		
		String respuesta = sb.toString();
		Pattern patron = Pattern.compile("<estado_cielo periodo=\".*\" descripcion=\".*\">(.*)</estado_cielo>");
		Matcher matcher = patron.matcher(respuesta);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}
}
