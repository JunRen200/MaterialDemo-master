package comqq.example.asus_pc.materialdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import comqq.example.asus_pc.materialdemo.R;

/**
 * Created by asus-pc on 2017/3/29.
 */

public class Fragment_chat extends Fragment{
    private BottomBar bottomBar;
    private BottomBarTab nearby;
    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_chat,container,false);
        initView();
        return view;
    }

    private void initView() {
        bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // 选择指定 id 的标签
                    nearby = bottomBar.getTabWithId(R.id.tab_favorites);
                    nearby.setBadgeCount(5);
                }
            }
        });

    }
}
