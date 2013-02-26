package com.atos.android.p24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Divisas {

	public static String convertir(String divisaOrigen, String divisaDestino) throws IOException {
		URL url = new URL("http://www.webservicex.net/CurrencyConvertor.asmx");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.addRequestProperty("SOAPAction", "http://www.webserviceX.NET/ConversionRate");
		con.addRequestProperty("Content-Type", "text/xml");
		con.setDoOutput(true);
		
		PrintStream ps = new PrintStream(con.getOutputStream());
		
		ps.println(
		"<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:web='http://www.webserviceX.NET/'>"
		+   "<soapenv:Header/>"
		+  "<soapenv:Body>"
		+    "<web:ConversionRate>"
		+      "<web:FromCurrency>" + divisaOrigen + "</web:FromCurrency>"
		+     "<web:ToCurrency>" + divisaDestino + "</web:ToCurrency>"
		+ "</web:ConversionRate>"
		+"</soapenv:Body>"
		+"</soapenv:Envelope>");
		
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
		Pattern patron = Pattern.compile("<ConversionRateResult>(.*)</ConversionRateResult>");
		Matcher matcher = patron.matcher(respuesta);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

}
