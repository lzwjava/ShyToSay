package lzw.shy_to_say;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class NewTopicActivity extends BaseActivity {
	EditText titleEdit;
	EditText contentEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_topic_activity);
		titleEdit=(EditText)findViewById(R.id.title);
		contentEdit=(EditText)findViewById(R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.new_topic, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.post:
				Bundle bundle=new Bundle();
				bundle.putString(TopicItem.TITLE,
				  titleEdit.getText().toString());
				bundle.putString(PostItem.CONTENT,
					contentEdit.getText().toString());
				Intent intent=new Intent();
				intent.putExtras(bundle);
				setResult(RESULT_OK,intent);
				finish();
			  break;
		}
		return super.onOptionsItemSelected(item);
	}
}