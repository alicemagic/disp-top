//////////////////////////////////////////////////
//		壁紙を取得して、全画面に表示させるためのクラス			//
/////////////////////////////////////////////////

package jp.com.disp_top;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;


public class ImageviewActivity extends Activity{
	ImageView img;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		
		//イメージビューを召喚
		img = (ImageView)findViewById(R.id.imageView1);
		
		//ディスプレイ情報を取得するための前準備
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();

		//ディスプレイの高さと幅を取得
		int height = display.getHeight();
		int width = display.getWidth();
		
		//設定されている壁紙の取得
		BitmapDrawable wallpaper = (BitmapDrawable) getWallpaper();
		Bitmap bitmap = wallpaper.getBitmap();
		
		//ビットマップに高さと幅を設定
		bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
		
		//イメージビューに取得した壁紙を設定
		img.setImageBitmap(bitmap);

	}
	
	//タッチイベント
	public boolean onTouchEvent(MotionEvent event) {
		 switch (event.getAction()) {
		 	//画面が押された時Activity終了
	        case MotionEvent.ACTION_DOWN:
	        	this.moveTaskToBack(true);
	            break;
	        }
		return super.onTrackballEvent(event);
		
	}
}
