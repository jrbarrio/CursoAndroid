package com.example.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView webView = (WebView) findViewById(R.id.webview);
		//webView.loadUrl("file:///android_asset/www/index.html");
		webView.loadUrl("http://www.google.com");
	}
}
