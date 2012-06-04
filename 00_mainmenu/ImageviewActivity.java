//////////////////////////////////////////////////
//		壁紙を取得して、全画面に表示させるためのクラス			//
/////////////////////////////////////////////////

package jp.com.disptop;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageviewActivity extends Activity{
	ImageView img;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		img = (ImageView)findViewById(R.id.imageView1);
		
		BitmapDrawable wallpaper = (BitmapDrawable) getWallpaper();
		Bitmap bitmap = wallpaper.getBitmap();
		img.setImageBitmap(bitmap);

	}
}
