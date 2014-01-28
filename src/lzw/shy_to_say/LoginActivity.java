package lzw.shy_to_say;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;

public class LoginActivity extends BaseActivity {
	EditText userName,password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		AVUser currentUser=AVUser.getCurrentUser();
		if(currentUser!=null){
			Intent intent=new Intent(this,TopicActivity.class);
			startActivity(intent);
			LoginActivity.this.finish(); 
		}
		
		userName=(EditText)findViewById(R.id.userName);
		password=(EditText)findViewById(R.id.password);
	}
	
	public void login(View v){
		final String userNameStr=userName.getText().toString();
		if(isEmpty(userNameStr,"请输入用户名")){
			return;
		}
		final String passwordStr = password.getText().toString();
		if(isEmpty(passwordStr,"请输入密码")){
			return;
		}
		AVUser.logInInBackground(userNameStr, passwordStr, new LogInCallback() {
			public void done(AVUser user, AVException e) {
				if (e == null) {
					// 登录成功
					toast("登录成功");
					intentToClass(LoginActivity.this, TopicActivity.class);
				} else {
					// 登录失败
					toast("未联网或者用户名账号错误");
				}
			}	
		});
	}
	
	public void register(View v){
		final String userNameStr=userName.getText().toString();
		if(userNameStr.isEmpty()){
			toast("请输入账号");
			return;
		}
		
		final String passwordStr=password.getText().toString();
		if(passwordStr.isEmpty()){
			toast("请输入密码");
			return;
		}

		AVUser user = new AVUser();
		user.setUsername(userNameStr);
		user.setPassword(passwordStr);
		// 其他属性可以像其他AVObject对象一样使用put方法添加
		user.signUpInBackground(new SignUpCallback() {
			public void done(AVException e) {
				if (e == null) {
					// successfully
					toast("注册成功");
				} else {
					// failed
					toast("用户名已经被注册了");
				}
			}
		});
	}
}
