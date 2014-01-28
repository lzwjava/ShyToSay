package lzw.shy_to_say;

import java.util.List;

import lzw.shy_to_say.utils.Util;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class TopicAdapter extends BaseAdapter{ 
	
	public static class ItemView{
		TextView titleView,replyNumView,
		  authorView,timeView;
	}
	
	Context context;
	List<TopicItem> items;
	public TopicAdapter(Context context,
	 List<TopicItem>items){
		this.context=context;
		this.items=items;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static ItemView findItemView(View view,ItemView buffer) {
		buffer.titleView = (TextView) view.findViewById(R.id.title);
		buffer.replyNumView = (TextView) view.findViewById(R.id.replyNum);
		buffer.authorView = (TextView) view.findViewById(R.id.author);
		buffer.timeView = (TextView) view.findViewById(R.id.time);
		return buffer;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		if(convertView!=null){
			view=convertView;
		}else{
			LayoutInflater inflater=LayoutInflater.from(context);
			view=inflater.inflate(R.layout.topic_layout, null);
			ItemView buffer = new ItemView();
			findItemView(view,buffer);
			
			view.setTag(buffer);
		}
		
		TopicItem item=items.get(position);
		ItemView buffer=(ItemView)view.getTag();
		setTextView(buffer, item);
		return view;
	}

	static void setTextView(ItemView buffer, TopicItem item) {
		buffer.titleView.setText(item.getTitle());
		buffer.replyNumView.setText(item.getReplyNum()+"");
		buffer.authorView.setText(item.getAuthor());
		buffer.timeView.setText(Util.agoTime(item.getCreatedAt()));
	}
}