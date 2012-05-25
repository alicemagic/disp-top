//2012.5.25 画面遷移の動作を確認

package jp.com.disptop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */
	
	//各ボタンを召喚
	Button button_main;
	Button button_sub1;
	Button button_sub2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button_main = (Button) findViewById(R.id.main_button);
		button_sub1 = (Button) findViewById(R.id.sub1_button);
		button_sub2 = (Button) findViewById(R.id.sub2_button);
		
		button_main.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == button_main){
			Intent intent = new Intent(this,menuActivity.class);
			startActivity(intent);
		}
	}

}