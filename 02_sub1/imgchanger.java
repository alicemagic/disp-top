package co.jp.shortcutcreater;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {

	private static final int REQUEST_GALLERY = 0;

	List<ResolveInfo> infoes;      //取得したアプリケーションの情報を格納　infomation of getted apprication into

	Button     createbtn;          //ショートカット作成  create shortcut
	TextView   title;              //ショートカットの名前を格納 name of shortcut into
	
	Button     to_homebtn;

	Button     setimagebtn;        //ギャラリーを参照  compare gallery
	TextView   imgurl;             //参照した画像のurlを表示 display a compared image
	String     txt_imgurl;         //参照した画像のurlを格納 house a url of compared image

	String     apppack = "";       //spinnerで取得したパッケージを格納 house 
	String     appname = "";       //　　　　〃　  　　　　　クラスネームを格納

	ImageView  imageview;          //参照した画像を表示

	Bitmap     imgbitmap;          //参照した画像のビットマップを格納

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageview = (ImageView) findViewById(R.id.imageView1);

		title = (TextView) findViewById(R.id.editText1);

		imgurl = (TextView) findViewById(R.id.editText2);
		setimagebtn = (Button) findViewById(R.id.button2);
		setimagebtn.setOnClickListener(this);

		setspinner();
		
		createbtn = (Button) findViewById(R.id.shortcut_btn1);
		createbtn.setOnClickListener(this);
		
		to_homebtn = (Button)findViewById(R.id.to_home);
		to_homebtn.setOnClickListener(this);
		
	}

	public void setspinner() {// アプリケーション選択のspinnerを作成

		// spinnerの要素を取得
		// 起動可能なIntent
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		// デスクトップから可能なIntent(つまり通常のアプリケーション)
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 通常のアプリケーションのリストを取得
		PackageManager manager = getPackageManager();
		infoes = manager.queryIntentActivities(intent, 0);

		// spinnerに設定
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// アイテムを追加します
		for (int i = 0; i < infoes.size(); i++) {
			ResolveInfo info = infoes.get(i);
			ActivityInfo activityinfo = info.activityInfo;

			// アプリ内の取得する情報を取得
			adapter.add(activityinfo.packageName);
		}

		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		// アダプターを設定します
		spinner.setAdapter(adapter);

		//リスナー実装
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				// 選択されたアイテムを取得します
				String item = (String) spinner.getSelectedItem();
				// appurl.setText("");
				apppack = item;
				appname = infoes.get(position).activityInfo.name;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.shortcut_btn1) {//ショートカット作成ボタンが押されたとき
			
			Intent shortcutIntent = new Intent(Intent.ACTION_VIEW);

			shortcutIntent.setClassName(apppack, appname);// デフォルトではカメラが設定される

			// メッセージを設定　Todo　ショートカットが作成されたことを伝える
//			shortcutIntent.putExtra("MESSAGE", "THIS IS TEST MESSAGE!!!");

			// 送信
			if (title.equals("")) {//タイトルが設定されているかどうか
				
				// Todo makeIntentの第一引数でショートカットの下の名前を変更する
				// テキストボックスから文字列を取り出して引数として渡す
				sendBroadcast(makeIntent("shortcut", shortcutIntent));
				
			} else {
				
				sendBroadcast(makeIntent(title.getText().toString(),shortcutIntent));
				
			}
		}
		if (v.getId() == R.id.button2) {//参照ボタンが押されたとき
			
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			//ギャラリーを指定
			startActivityForResult(intent, REQUEST_GALLERY);
			
		}
		
		if (v.getId() == R.id.to_home){
			this.moveTaskToBack (true);
		}
	}
	
	// ショートカットインテントを設定したインテントを作る
	Intent makeIntent(String shortcutName, Intent shortcutIntent) {
		Intent intent = new Intent();

		// ショートカットインテントを設定
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

		// ショートカット名を設定
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);

		// アイコン設定
		Parcelable iconResource = Intent.ShortcutIconResource.fromContext(this,
				R.drawable.icon);

		if (imgbitmap != null) {//アイコンが設定されていなければデフォルトの画像を設定
			Parcelable parcelicon = Bitmap.createScaledBitmap(imgbitmap, 128, 128, true);
			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, parcelicon);
		} else {
			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

		}
		// アクションを設定
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

		return intent;
	}

	// ギャラリーで選択した画像をビットマップに変換して、imgbitmapに格納
	protected void onActivityResult(int requestCode, int resultCode,Intent data) {

		if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
			try {
				InputStream in = getContentResolver().openInputStream(
						data.getData());
				imgbitmap = BitmapFactory.decodeStream(in);
				in.close();
				// 選択した画像を表示

//				①imageviewのbitmapを変更
				imageview.setImageBitmap(imgbitmap);
				
				imgurl.setText("");
				txt_imgurl = data.getData().toString();
				imgurl.setText(txt_imgurl);
				
//				②imageviewのurlを変更
				imageview.setImageURI(data.getData());
				
//				③imageviewのbitmapを変更した後、imageviewのbitmapを取得
				// getDrawableメソッドで取り出したものを、BitmapDrawable形式にキャストして保存。
				BitmapDrawable bd = (BitmapDrawable)imageview.getDrawable();
				// getBitmapメソッドでビットマップファイルを取り出す。
				imgbitmap = bd.getBitmap();
				
			} catch (Exception e) {

			}
		}
	}
}

