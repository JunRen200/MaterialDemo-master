package comqq.example.asus_pc.materialdemo.ui.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import comqq.example.asus_pc.materialdemo.MainActivity;
import comqq.example.asus_pc.materialdemo.R;

/**
 * Created by asus-pc on 2016/12/28.
 */
public class LoginActivity extends Activity {
    private EditText edt_username;
    private EditText edt_password;
    private Button btn_login;
    private TextView txt_new_user;
    private ProgressDialog mProgressDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edt_username= (EditText) findViewById(R.id.edt_use_name);
        edt_password= (EditText) findViewById(R.id.edt_password);
        btn_login= (Button) findViewById(R.id.login);
        txt_new_user= (TextView) findViewById(R.id.new_user);
        txt_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(RegisterActivity.class);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {
        String name=edt_username.getText().toString();
        String password=edt_password.getText().toString();
        if(!name.equals("")){
            if(!password.equals("")){
                showProgressDialog("登录中...");
                loginBmob(name,password);
            }else {
                edt_password.setError("密码不能为空");
            }
        }else {
            edt_username.setError("用户名不能为空");
        }
    }

    private void loginBmob(final String name, final String password) {
        BmobUser.loginByAccount(name, password, new LogInListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if(e==null){
                    Log.d("main", "Bmob登录成功！");
                    LoginEM(name,password);
                }else {
                    Log.d("main", "Bmob登录失败！");
                    hideProgressDialog();
                }
            }
        });
    }

    private void LoginEM(String name, String password) {
        EMClient.getInstance().login(name, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("main", "登录聊天服务器成功！");
                        hideProgressDialog();
                        goTo(MainActivity.class);
                        finish();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("main", "登录聊天服务器失败！");
                        hideProgressDialog();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    public void goTo(Class activity) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        startActivity(intent);
    }
    public void showProgressDialog(String msg){
        if(mProgressDialog==null){
            mProgressDialog=new ProgressDialog(this);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }
    public void hideProgressDialog(){
        mProgressDialog.hide();
    }
}
