package comqq.example.asus_pc.materialdemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import comqq.example.asus_pc.materialdemo.MainActivity;
import comqq.example.asus_pc.materialdemo.R;

/**
 * Created by asus-pc on 2016/12/28.
 */

public class SplashActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    protected void init() {
      /*  mSplashpresenter=new SplashPresenterImpl(this);
        mSplashpresenter.checkLoginStatus();*/

        if (checkLoginStatus()) {
            navigateToMain();
        } else {
            navigateTologin();
        }
    }

    private boolean checkLoginStatus() {
        return false;
    }

    private void navigateTologin() {
        goTo(LoginActivity.class);
        finish();
    }

    private void navigateToMain() {
        goTo(MainActivity.class);
        finish();
    }

    public void goTo(Class activity) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        startActivity(intent);
    }
}
