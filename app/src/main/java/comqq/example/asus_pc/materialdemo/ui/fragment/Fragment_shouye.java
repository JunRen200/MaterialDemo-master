package comqq.example.asus_pc.materialdemo.ui.fragment;


import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import comqq.example.asus_pc.materialdemo.R;
import comqq.example.asus_pc.materialdemo.setphoto;

/**
 * Created by asus-pc on 2017/3/21.
 */

public class Fragment_shouye extends Fragment {
    private CoordinatorLayout coordinator;
    private FloatingActionButton btn_fad_search;
    private ImageView img;
    private Button btn1;
    private Button btn2;
    private Fragment_btn1 frag1;
    private Fragment_btn2 frag2;
    private View view;  FragmentManager fm;
    FragmentTransaction transaction;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_shouye,container,false);
        initView();
        setDefaultFragment();
        return view;
    }

    private void setDefaultFragment() {
        if(frag1==null){
            frag1=new Fragment_btn1();
        }
        fm=getFragmentManager();
        transaction=fm.beginTransaction();
        transaction.replace(R.id.fragment_layout,frag1);
        transaction.commit();
    }

    private void initView() {
        img= (ImageView) view.findViewById(R.id.iv_book_image);
        coordinator= (CoordinatorLayout) view.findViewById(R.id.coordinator);
        btn1= (Button) view.findViewById(R.id.btn_fragment1);
        btn2= (Button) view.findViewById(R.id.btn_fragment2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frag1==null){
                    frag1=new Fragment_btn1();
                    transaction=fm.beginTransaction();
                    transaction.replace(R.id.fragment_layout,frag1);
                    transaction.commit();
                }else {
                    transaction=fm.beginTransaction();
                    transaction.replace(R.id.fragment_layout,frag1);
                    transaction.commit();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction transaction=fm.beginTransaction();
                if(frag2==null){
                    frag2=new Fragment_btn2();
                    transaction=fm.beginTransaction();
                    transaction.replace(R.id.fragment_layout,frag2);
                    transaction.commit();
                }else {
                    transaction=fm.beginTransaction();
                    transaction.replace(R.id.fragment_layout,frag2);
                    transaction.commit();
                }
            }
        });
        btn_fad_search= (FloatingActionButton) view.findViewById(R.id.fad_search);
        btn_fad_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinator,"切换图片",Snackbar.LENGTH_LONG).setAction("action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((setphoto) getActivity()).gotophoto();
                    }
                }).show();
            }
        });

    }
    public void setImageBitmap(Bitmap bitmap){
        img.setImageBitmap(bitmap);
    };
}
