package lzw.shy_to_say;

import java.util.List;

import lzw.shy_to_say.utils.Util;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class PostAdapter extends BaseAdapter{ 
	
	class ItemView{
		TextView authorView,timeView,
		  contentView;
	}
	
	Context context;
	List<PostItem> items;
	public PostAdapter(Context context,
	 List<PostItem>items){
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		if(convertView!=null){
			view=convertView;
		}else{
			LayoutInflater inflater=LayoutInflater.from(context);
			view=inflater.inflate(R.layout.post_layout, null);
			
			ItemView buffer=new ItemView();
			buffer.contentView=(TextView)view.findViewById(R.id.content);
			buffer.authorView=(TextView)view.findViewById(R.id.author);
			buffer.timeView=(TextView)view.findViewById(R.id.time);
			
			view.setTag(buffer);
		}
		
		PostItem item=items.get(position);
		ItemView buffer=(ItemView)view.getTag(); 
		buffer.authorView.setText(item.getAuthor());
		buffer.timeView.setText(Util.agoTime(item.getCreatedAt()));
		buffer.contentView.setText(item.getContent());
		return view;
	}
}