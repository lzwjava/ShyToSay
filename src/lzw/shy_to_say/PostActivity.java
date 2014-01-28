package lzw.shy_to_say;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lzw.shy_to_say.TopicAdapter.ItemView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

public class PostActivity extends BaseActivity {
	ListView postList;
	final List<PostItem> postItems = new ArrayList<PostItem>();
	PostAdapter postAdapter;
	EditText contentEdit;
	String parentId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater=LayoutInflater.from(this);
		View topicItemView=inflater.inflate(R.layout.topic_layout, null);
		ItemView buffer;
		TopicAdapter.findItemView(topicItemView, buffer);
		TopicItem topicItem=((ShyToSayApplication)getApplication()).getTopicItem();
		TopicAdapter.setTextView(buffer, topicItem);
		LinearLayout layout=new LinearLayout(this);
		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
			LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		
		View postLayout=inflater.inflate(R.layout.post_activity, layout);
		layout.setOrientation(1);
		layout.addView(topicItemView);
		layout.addView(postLayout);
		
		setContentView(layout);
		getWindow().setSoftInputMode(WindowManager.
			LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		postList = (ListView) findViewById(R.id.postList); 
		postAdapter = new PostAdapter(PostActivity.this, 
			postItems); 
		postList.setAdapter(postAdapter);
		postList.setOnItemClickListener(new ContentItemOnClickListener());
		
		Intent intent=getIntent();
		parentId=intent.getStringExtra("parentId");
		setPostList(parentId); 
		
		contentEdit=(EditText)findViewById(R.id.content); 
	}

	public void reply(View v){
		String author=AVUser.getCurrentUser().getUsername();
		String content=contentEdit.getText().toString();
		int replyNum=0;
		Date time=new Date();
		TopicActivity.post(author, content, replyNum, parentId);
		
		setPostList(parentId);
		contentEdit.setText("");
	}
	
	class ContentItemOnClickListener implements AdapterView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent,View view,
		 int position,long id){
			intentToClass(PostActivity.this, CommentActivity.class);
		}
	}
	
	void setTopicLayout(TopicItem topicItem){
		View view=findViewById(R.id.topic_layout);
		ItemView buffer=new ItemView();
		TopicAdapter.findItemView(view, buffer);
		TopicAdapter.setTextView(buffer, topicItem);
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
