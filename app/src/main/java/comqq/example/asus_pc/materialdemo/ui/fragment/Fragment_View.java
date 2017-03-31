package comqq.example.asus_pc.materialdemo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import comqq.example.asus_pc.materialdemo.adapter.Myadapter;
import comqq.example.asus_pc.materialdemo.R;

/**
 * Created by asus-pc on 2017/3/22.
 */

public class Fragment_View extends Fragment {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<Integer> heightlist = new ArrayList<Integer>();
    private RecyclerView recyclerView;
    private Myadapter myadapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_box, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_box);
        initdate();
        myadapter = new Myadapter(list, heightlist);
        recyclerView.setAdapter(myadapter);
        StaggeredGridLayoutManager manager1=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        myadapter.setItemonclicklistener(new Myadapter.OnItemClicklistener() {
            @Override
            public void onItemclick(String name, int position) {
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongclick(int position) {

            }
        });
        return view;
    }

    private void initdate() {
        for (int i = 'A'; i < 'Z'; i++) {
            list.add("" + (char) i);
        }
        for (int i = 0; i < list.size(); i++) {
            heightlist.add(100 + (int) (Math.random() * 600));
        }
    }
}



