package com.dalong.tantan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.tantan.R;


/**
 * 主界面
 */
public class MainFragment extends Fragment {

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.sliding_menu_main, container, false);
    	return view;
    }

}
