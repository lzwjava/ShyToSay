package lzw.shy_to_say;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		setActionBarDrawable(R.drawable.bar); 
	}
	
	void setActionBarDrawable(int id) {
		ActionBar actionBar=getActionBar();
		Drawable drawable=this.getResources().getDrawable(id);
	  actionBar.setBackgroundDrawable(drawable);
	}
	
	void toast(String txt){
		Toast.makeText(this,txt,
				Toast.LENGTH_SHORT).show();
	}
	
	void intentToClass(Context ctx, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(ctx, cls);
		startActivity(intent);
	}
	
	boolean isEmpty(String str,String prompt){
		if(str.isEmpty()){
			toast(prompt);
			return true;
		}
		return false;
	}
	
}
