//2012.5.16 現在バグ発生中

package jp.com.disp_top;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class menuActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */
	
	//各ボタンを召喚
	Button button_main = (Button) findViewById(R.id.main_button);
	Button button_sub1 = (Button) findViewById(R.id.sub1_button);
	Button button_sub2 = (Button) findViewById(R.id.sub2_button);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == button_main){
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
		}
	}

}