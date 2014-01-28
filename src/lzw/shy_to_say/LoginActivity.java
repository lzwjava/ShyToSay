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
		if(isEmpty(userNameStr,"�������û���")){
			return;
		}
		final String passwordStr = password.getText().toString();
		if(isEmpty(passwordStr,"����������")){
			return;
		}
		AVUser.logInInBackground(userNameStr, passwordStr, new LogInCallback() {
			public void done(AVUser user, AVException e) {
				if (e == null) {
					// ��¼�ɹ�
					toast("��¼�ɹ�");
					intentToClass(LoginActivity.this, TopicActivity.class);
				} else {
					// ��¼ʧ��
					toast("δ���������û����˺Ŵ���");
				}
			}	
		});
	}
	
	public void register(View v){
		final String userNameStr=userName.getText().toString();
		if(userNameStr.isEmpty()){
			toast("�������˺�");
			return;
		}
		
		final String passwordStr=password.getText().toString();
		if(passwordStr.isEmpty()){
			toast("����������");
			return;
		}

		AVUser user = new AVUser();
		user.setUsername(userNameStr);
		user.setPassword(passwordStr);
		// �������Կ���������AVObject����һ��ʹ��put�������
		user.signUpInBackground(new SignUpCallback() {
			public void done(AVException e) {
				if (e == null) {
					// successfully
					toast("ע��ɹ�");
				} else {
					// failed
					toast("�û����Ѿ���ע����");
				}
			}
		});
	}
}
