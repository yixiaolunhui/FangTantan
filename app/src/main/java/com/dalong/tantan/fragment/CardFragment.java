package com.dalong.tantan.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.carview.CardDataItem;
import com.dalong.carview.CardSlidePanel;
import com.dalong.tantan.MainActivity;
import com.dalong.tantan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡片Fragment
 *
 */
@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})
public class CardFragment extends Fragment {

    private CardSlidePanel.CardSwitchListener cardSwitchListener;

    private String imagePaths[] = {"assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg",
            "assets://wall12.jpg", "assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg", "assets://wall12.jpg"}; // 24个图片资源名称

    private String names[] = {"郭富城", "刘德华", "张学友", "李连杰", "成龙", "谢霆锋", "李易峰",
            "霍建华", "胡歌", "曾志伟", "吴孟达", "梁朝伟", "周星驰", "赵本山", "郭德纲", "周润发", "邓超",
            "王祖蓝", "王宝强", "黄晓明", "张卫健", "徐峥", "李亚鹏", "郑伊健"}; // 24个人名

    private List<CardDataItem> dataList = new ArrayList<CardDataItem>();
    public  CardSlidePanel slidePanel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.dalong.carview.R.layout.card_layout, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        slidePanel = (CardSlidePanel) rootView
                .findViewById(com.dalong.carview.R.id.image_slide_panel);
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(View cardView,int index) {
                cardView.findViewById(R.id.card_like_icon).setAlpha(0);
                cardView.findViewById(R.id.card_dislike_icon).setAlpha(0);
                Log.d("CardFragment", "正在显示-" + dataList.get(index).userName);
            }

            @Override
            public void onCardVanish(View changedView,int index, int type) {
                Log.d("CardFragment", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);
                switch (type){
                    case 0:
                        Log.d("CardFragment", "不喜欢" );
                        setViewPressed(getActivity().findViewById(R.id.card_left_btn),200);
                        break;
                    case 1:
                        Log.d("CardFragment", "喜欢" );
                        setViewPressed(getActivity().findViewById(R.id.card_right_btn),200);
                        break;
                }
            }

            @Override
            public void onItemClick(View cardView, int index) {
                Log.d("CardFragment", "卡片点击-" + dataList.get(index).userName);
            }

            @Override
            public void onViewPosition(View changedView,float dx, float dy) {
                changedView.findViewById(R.id.card_like_icon).setAlpha(dx);
                changedView.findViewById(R.id.card_dislike_icon).setAlpha(dy);
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);
        prepareDataList();
        slidePanel.fillData(dataList);
        slidePanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public  void setViewPressed(final View view,long time){
        view.setPressed(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setPressed(false);
            }
        },time);

    }

    private void prepareDataList() {
        int num = imagePaths.length;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < num; i++) {
                CardDataItem dataItem = new CardDataItem();
                dataItem.userName = names[i];
                dataItem.imagePath = imagePaths[i];
                dataItem.likeNum = (int) (Math.random() * 10);
                dataItem.imageNum = (int) (Math.random() * 6);
                dataList.add(dataItem);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addIgnoredView(view.findViewById(R.id.carview1));
        addIgnoredView(view.findViewById(R.id.carview2));
        addIgnoredView(view.findViewById(R.id.carview3));
        addIgnoredView(view.findViewById(R.id.carview4));
        removeIgnoredView(view.findViewById(R.id.card_bottom_layout));
    }

    public void addIgnoredView(View view){
        MainActivity main=(MainActivity)getActivity();
        if(view!=null){
            main.addIgnoredView(slidePanel);
        }
    }
    public void removeIgnoredView(View view){
        MainActivity main=(MainActivity)getActivity();
        if(view!=null){
            main.removeIgnoredView(slidePanel);
        }
    }
}
