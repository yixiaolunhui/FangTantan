package com.dalong.tantan.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.tantan.MainActivity;
import com.dalong.tantan.R;
import com.dalong.tantan.adapter.MyFragmentPagerAdapter;
import com.gigamole.library.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;


/**
 * 右边界面
 */
public class RightFragment extends Fragment{

	private NavigationTabStrip mItemTab;
	private ViewPager mViewpager;
	private List<Fragment> mFragments=new ArrayList<>();
	private MyFragmentPagerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sliding_menu_right, container,false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		mItemTab=(NavigationTabStrip)view.findViewById(R.id.menu_right_tab);
		mViewpager=(ViewPager)view.findViewById(R.id.menu_right_viewpager);
		RightOneFragment mOneFragment=new RightOneFragment();
		RightTwoFragment mTwoFragment=new RightTwoFragment();
		mFragments.add(mOneFragment);
		mFragments.add(mTwoFragment);
		adapter=new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),mFragments);
		mViewpager.setAdapter(adapter);
		mItemTab.setTitles("所有配对","聊天");
		mItemTab.setViewPager(mViewpager);
	}


	/**
	 *  点击右侧菜单中切换中间主界面的fragment
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		MainActivity ra = (MainActivity) getActivity();
		ra.switchContent(fragment);
	}
}
