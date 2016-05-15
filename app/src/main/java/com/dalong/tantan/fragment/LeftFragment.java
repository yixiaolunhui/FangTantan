package com.dalong.tantan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.tantan.MainActivity;
import com.dalong.tantan.R;


/**
 * 左边界面
 */
public class LeftFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sliding_menu_left, container,false);
		return view;
	}

	/**
	 *  点击左侧菜单中切换中间主界面的fragment
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		MainActivity ra = (MainActivity) getActivity();
		ra.switchContent(fragment);

	}

}
