package comqq.example.asus_pc.materialdemo.app;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BuildConfig;

/**
 * Created by asus-pc on 2017/3/29.
 */

public class MaterialApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(getApplicationContext(), "502da49ea866e1ecd15f1b270ea807b1");
        initEaseMob();
    }

    private void initEaseMob() {
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);
        EMClient.getInstance().init(MaterialApplication.this, options);
        if (BuildConfig.DEBUG) {
            EMClient.getInstance().setDebugMode(true);
        }
    }
}
