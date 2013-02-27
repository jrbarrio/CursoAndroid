package com.atos.android.p31;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Lienzo extends View {
	
	public Lienzo(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public Lienzo(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Lienzo(Context context) {
		super(context);
	} 
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint brocha = new Paint();	
		
		AssetManager assets = getContext().getAssets();		
		try {
			InputStream is = assets.open("android.png");
			Bitmap bmp = BitmapFactory.decodeStream(is);
			canvas.drawBitmap(bmp, 0, 0, brocha);

		} catch (IOException e) {
			Log.e("Lienzo", "Error de lectura de android.png", e);
		}
	}
}
