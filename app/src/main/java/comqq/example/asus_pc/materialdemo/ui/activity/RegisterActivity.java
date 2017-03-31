package comqq.example.asus_pc.materialdemo.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import comqq.example.asus_pc.materialdemo.R;
import comqq.example.asus_pc.materialdemo.utils.StringUtils;

import static comqq.example.asus_pc.materialdemo.R.id.register;


/**
 * Created by asus-pc on 2016/12/28.
 */
public class RegisterActivity extends Activity {
    private EditText edt_name;
    private EditText edt_password;
    private EditText edt_confirm_password;
    private Button btn_register;
    private ProgressDialog mProgressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        edt_name = (EditText) findViewById(R.id.use_name);
        edt_password = (EditText) findViewById(R.id.password);
        edt_confirm_password = (EditText) findViewById(R.id.confirm_password);
        btn_register = (Button) findViewById(register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {
        String name = edt_name.getText().toString();
        String password = edt_password.getText().toString();
        String confirm_password = edt_confirm_password.getText().toString();
        if (StringUtils.isValidUserName(name)) {
            if (StringUtils.isValidPassword(password)) {
                if (password.equals(confirm_password)) {
                    showProgressDialog("注册中...");
                    registerBmob(name, password);
                } else {
                    edt_name.setError("用户名不合法");
                }
            } else {
                edt_password.setError("密码不合法");
            }
        } else {
            edt_confirm_password.setError("密码与确认密码不一致");
        }
    }

    private void registerBmob(final String name, final String password) {

        BmobUser user = new BmobUser();
        user.setUsername(name);
        user.setPassword(password);
        user.signUp(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    Log.d("log", "Bmob注册成功");
                    registerEM(name, password);
                } else {
                    hideProgressDialog();
                    Log.d("log", "Bmob注册失败");
                }
            }
        });
    }


    private void registerEM(final String name, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(name, password);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("log", "EM注册成功");
                            hideProgressDialog();
                            finish();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressDialog();
                            Log.d("log", "EM注册失败");
                        }
                    });
                }
            }
        }).start();

    }

    public void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }
    public void hideProgressDialog() {
        mProgressDialog.hide();
    }
}
