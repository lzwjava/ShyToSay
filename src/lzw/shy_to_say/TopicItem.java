package lzw.shy_to_say;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("TopicItem")
public class TopicItem extends AVObject{
	/*TopicItem 
	String title;
	int replyNum;
	String author;
	*/
	
	//Topic×Ö¶Î
	public static final String TITLE = "title";
	public static final String REPLY_NUM = "replyNum";
	public static final String AUTHOR = "author";

	public TopicItem(){ 
	}
	
	public TopicItem(String title,int replyNum,
	 String author){
		setTitle(title);
		setReplyNum(replyNum);
		setAuthor(author);
	}
	
	public void setTitle(String value){
		put("title",value);
	}
	
	public String getTitle(){
		return getString("title");
	}

	public void setReplyNum(int value){
		put("replyNum",value);
	}
	
	public int getReplyNum(){
		return getInt("replyNum");
	}
	
	public void setAuthor(String value){
		put("author",value);
	}
	
	public String getAuthor(){
		return getString("author");
	}
};
