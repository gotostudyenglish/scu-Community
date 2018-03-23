package home.smart.fly.scucommunity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.adapter.FragmentAdapter;


public class ThirdFragment extends Fragment {
    private View rootView;
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_three, container, false);
        InitView();
        return rootView;
    }


    private void InitView() {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        //初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText("我的提问"));
        List<String> titles = new ArrayList<>();
        titles.add("我的提问");


        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SecondSubFragment());


        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

    }


}