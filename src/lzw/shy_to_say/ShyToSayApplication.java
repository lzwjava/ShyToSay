package lzw.shy_to_say;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

public class ShyToSayApplication extends Application {
    private static final String LOGTAG = ShyToSayApplication.class.getName();
    
    public TopicItem topicItem;
    
		public TopicItem getTopicItem() {
			return topicItem;
		}
		public void setTopicItem(TopicItem topicItem) {
			this.topicItem = topicItem;
		}
		
		@Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.useAVCloudCN();
        AVObject.registerSubclass(TopicItem.class);
        AVObject.registerSubclass(PostItem.class); 
        AVOSCloud.initialize(getApplicationContext(), "9h876ua8gm45fc8yfg9mc61hrew4d5i82llt1vyi70u07cw7",
    	    "u22pkt3pni42tybspv02x6p3uucvo5v01vywhoojmczkx77a");
    }
}
