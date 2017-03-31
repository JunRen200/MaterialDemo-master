package comqq.example.asus_pc.materialdemo.ui.fragment;




import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import comqq.example.asus_pc.materialdemo.R;

/**
 * Created by asus-pc on 2017/3/22.
 */

public class Fragment_blog extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentPagerAdapter fAdapter;
    private List<Fragment> list_fragment;
    private List<String> list_title;

    View view;

    public Fragment_blog() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_blog,container,false);
        initView();
        return view;
    }

    private void initView() {
        mTabLayout= (TabLayout) view.findViewById(R.id.tl_title);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_pager);
        list_title = new ArrayList<>();
        list_fragment = new ArrayList<>();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i <3; i++) {
            //加载tab名字列表
            list_title.add("Tab_name");
            //初始化各fragment,并加入列表
            Fragment_View boxBaseFragment = new Fragment_View();
            list_fragment.add(boxBaseFragment);
            //为TabLayout添加tab名称
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        }
        fAdapter = new ContentPagerAdapter(getFragmentManager());
        //viewpager加载adapter
        mViewPager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }
    class ContentPagerAdapter extends FragmentStatePagerAdapter{
        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return list_fragment.get(position);

        }

        @Override
        public int getCount() {
            return list_title.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  list_title.get(position);
        }

    }
}
