package lzw.shy_to_say;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("PostItem")
public class PostItem extends AVObject{
	//columns
	/*
	String author;
	String content;
	int replyNum; 
	String parentId;
	*/
	
	public PostItem(){ 
	}
	
	public PostItem(String author,String content,
	 int replyNum, String parentId){
		setAuthor(author);
		setContent(content);
		setReplyNum(replyNum);
		setParentId(parentId);
	}

	public void setContent(String value){
		put("content",value);
	}
	
	public String getContent(){
		return getString("content");
	}
	
	String parentId="parentId";
	public static final String PARENT_ID = "parentId";
	public static final String REPLY_NUM = "replyNum";
	public static final String CONTENT = "content";
	//Post×Ö¶Î
	public static final String AUTHOR = "author";
	public void setParentId(String value){
		put(parentId,value);
	}
	
	public String getParentId(){
		return getString(parentId);
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
