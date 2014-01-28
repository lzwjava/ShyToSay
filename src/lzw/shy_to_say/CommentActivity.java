package lzw.shy_to_say;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lzw.shy_to_say.TopicAdapter.ItemView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

public class CommentActivity extends BaseActivity {
	ListView postList;
	final List<PostItem> postItems = new ArrayList<PostItem>();
	PostAdapter postAdapter;
	EditText contentEdit;
	String parentId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment_activity);
		getWindow().setSoftInputMode(WindowManager.
			LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		postList = (ListView) findViewById(R.id.postList); 
		postAdapter = new PostAdapter(CommentActivity.this, 
			postItems); 
		postList.setAdapter(postAdapter);
		
		Intent intent=getIntent(); 
		parentId=intent.getStringExtra("parentId");
		setPostList(parentId);
		
		contentEdit=(EditText)findViewById(R.id.content); 
	}

	public void reply(View v){
		String author=AVUser.getCurrentUser().getUsername();
		String content=contentEdit.getText().toString();
		int replyNum=0;
		TopicActivity.post(author, content, replyNum, parentId);
		
		setPostList(parentId);
		contentEdit.setText("");
	}
	
	void setPostList(final String parentId) { 
		AVQuery<PostItem> query = AVObject.getQuery(PostItem.class);
		query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.whereEqualTo("parentId", parentId);
		query.findInBackground(new FindCallback<PostItem>() { 
			@Override
			public void done(List<PostItem> items, AVException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					postItems.clear();
					postItems.addAll(items);
					postAdapter.notifyDataSetChanged();
				} else {
					e.printStackTrace();
				}
			}
		});
	}
}
