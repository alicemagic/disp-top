//2012.5.25 シークバーの実装と、値の引用を実装。　アプリの透視化について調査中
package jp.com.disptop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



public class menuActivity extends Activity implements OnSeekBarChangeListener , OnClickListener{
	SeekBar seek;
	TextView tv;
	Button bt;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		seek =  (SeekBar)findViewById(R.id.seek);
		tv = (TextView)findViewById(R.id.textView1);
		bt = (Button)findViewById(R.id.button1);
		seek.setMax(100);
	    seek.setProgress(0);
	    seek.setOnSeekBarChangeListener(this);
	    
	    bt.setOnClickListener(this);
	}
	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		//TODO 自動生成されたメソッド・スタブ
		tv.setText(String.format("Value = %d", arg1));
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this,ImageviewActivity.class);
		
		startActivity(intent);
		
		
	}

}
