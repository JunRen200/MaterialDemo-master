package comqq.example.asus_pc.materialdemo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hyphenate.chat.EMClient;

import cn.bmob.v3.BmobUser;
import comqq.example.asus_pc.materialdemo.ui.activity.activity_about;
import comqq.example.asus_pc.materialdemo.ui.fragment.Fragment_blog;
import comqq.example.asus_pc.materialdemo.ui.fragment.Fragment_chat;
import comqq.example.asus_pc.materialdemo.ui.fragment.Fragment_shouye;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements setphoto {
    private Button open;
    private Fragment_shouye fragment_shouye;
    private DrawerLayout drawlayout;
    private Fragment_blog fragment_blog;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    private CircleImageView head_img;
    private NavigationView navigationView;
    private Fragment lastfragment;
    private Fragment_chat fragment_chat;
    String a="aa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        for(int i=0;i<20;i++) {
//            arrayList.add("1");
//        }
//        arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,arrayList);
//        list= (ListView) findViewById(R.id.list);
//        list.setAdapter(arrayAdapter);
        initView();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        if (fragment_shouye == null) {
            fragment_shouye = new Fragment_shouye();
        }
        transaction.replace(R.id.fragment_main, fragment_shouye);
        transaction.commit();
        lastfragment = fragment_shouye;
    }

    private void initView() {
        open= (Button) findViewById(R.id.btn_open_navigation);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawlayout.openDrawer(GravityCompat.START);
            }
        });
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        setFragment(fragment_shouye);
                        break;
                    case R.id.navigation_item_blog:
                        if (fragment_blog == null) {
                            fragment_blog = new Fragment_blog();
                            setFirstFragment(fragment_blog);
                        } else {
                            setFragment(fragment_blog);
                        }
                        break;
                    case R.id.navigation_item_about:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, activity_about.class);
                        startActivity(intent);
                        drawlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navigation_item_chat:
                        if (fragment_chat == null) {
                            fragment_chat = new Fragment_chat();
                            setFirstFragment(fragment_chat);
                        } else {
                            setFragment(fragment_chat);
                        }
                        break;
                    case R.id.navigation_item_back:
                        logout();
                        break;
                }
                return false;
            }
        });
    }


    private void logout() {
        BmobUser.logOut();
        EMClient.getInstance().logout(true);
    }

    public void gotophoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1004);
    }

    private void setFirstFragment(Fragment fragment){
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.hide(lastfragment);
        transaction.add(R.id.fragment_main,fragment);
        transaction.commit();
        lastfragment=fragment;
        drawlayout.closeDrawer(GravityCompat.START);
    }
    private void setFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.hide(lastfragment);
        transaction.show(fragment);
        transaction.commit();
        lastfragment = fragment;
        drawlayout.closeDrawer(GravityCompat.START);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1004) {
            Uri imgUri = data.getData();
            //拿到图片后先裁剪
            Intent intent = new Intent();
            intent.setAction("com.android.camera.action.CROP");
            intent.setDataAndType(imgUri, "image/*");
            intent.putExtra("crop", true);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 1003);
        }
        if (requestCode == 1003 && data != null) {
            Bundle bundle = data.getExtras();
            Bitmap image = bundle.getParcelable("data");
            fragment_shouye.setImageBitmap(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
