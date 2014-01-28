package lzw.shy_to_say;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

public class TopicActivity extends BaseActivity { 
	public static final String TYPE = "type";
	public static final int ACTIVITY_POST = 1;
	public static final int ACTIVITY_COMMENT = 2;
	
	ListView topicList;
	final List<TopicItem> topicItems = new ArrayList<TopicItem>();
	TopicAdapter postAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_activity);
		topicList = (ListView) findViewById(R.id.topicList); 
		postAdapter = new TopicAdapter(TopicActivity.this, 
			topicItems); 
		topicList.setAdapter(postAdapter);
		setTopicList();
		
		topicList.setOnItemClickListener(new TopicItemClickListener());
		
	}

	class TopicItemClickListener implements AdapterView.OnItemClickListener{ 

		@Override
		public void onItemClick(AdapterView<?> parent,View view,
		 int position,long id){
			TopicItem item=(TopicItem)parent.getItemAtPosition(position);
			((ShyToSayApplication)getApplication()).setTopicItem(item);
			Intent intent=new Intent(TopicActivity.this,PostActivity.class);
			intent.putExtra("parentId", item.getObjectId());
			startActivity(intent);
		}
	}
	
	void setTopicList() { 
		AVQuery<TopicItem> query = AVObject.getQuery(TopicItem.class);
		query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.orderByDescending("createdAt");
		query.findInBackground(new FindCallback<TopicItem>() { 
			@Override
			public void done(List<TopicItem> items, AVException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					topicItems.clear();
					topicItems.addAll(items);
					postAdapter.notifyDataSetChanged();
				} else {
					e.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.new_topic, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		boolean result=super.onOptionsItemSelected(item);
		switch(item.getItemId()){
			case R.id.post:
				Intent intent=new Intent(this,
				  NewTopicActivity.class);
				startActivityForResult(intent, ACTIVITY_POST);
				break;
		}
		return result;
	}
	
	@Override
	protected void onActivityResult(int requestCode,
	 int resultCode,Intent intent){
		super.onActivityResult(requestCode, 
			resultCode, intent);
		if(intent==null) return;
		Bundle bundle=intent.getExtras();
		switch(requestCode){
			case ACTIVITY_POST:
				final String title=bundle.getString("title");
				final String content=bundle.getString("content");
				final TopicItem topicItem=new TopicItem();
				topicItem.setTitle(title);
				topicItem.setReplyNum(0);
				final String author=AVUser.getCurrentUser()
					.getUsername();
				topicItem.setAuthor(author);
				topicItem.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(AVException e) {
						// TODO Auto-generated method stub
						if(e==null){ 
							int replyNum=0;
							post(author, content, replyNum, topicItem.getObjectId());
							toast("发帖成功");
							setTopicList();
						}else{
							toast("发帖失败");
						}
					} 
				});
				break;
		}
	}

	static void post(final String author, final String content, int replyNum,
			final String parentId) {
		PostItem postItem = new PostItem();
		postItem.setAuthor(author);
		postItem.setContent(content);
		postItem.setReplyNum(replyNum);
		postItem.setParentId(parentId);
		postItem.saveInBackground();
	}
}