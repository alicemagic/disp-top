//////////////////////////////////////////////////
//		壁紙を取得して、全画面に表示させるためのクラス			//
/////////////////////////////////////////////////

package jp.com.disptop;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ImageviewActivity extends Activity {
	ImageView img;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		img = (ImageView)findViewById(R.id.imageView1);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		BitmapDrawable wallpaper = (BitmapDrawable) getWallpaper();
//		Bitmap bitmap = wallpaper.getBitmap();
//		img.setImageBitmap(bitmap);
		setContentView(R.layout.image);

	}
}
