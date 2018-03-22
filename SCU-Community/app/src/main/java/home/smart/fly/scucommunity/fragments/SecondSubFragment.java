package home.smart.fly.scucommunity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.adapter.SubRecyclerViewAdapter;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.image;

public class SecondSubFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SubRecyclerViewAdapter adapter;
    private View rootView;
    private List<Question> QuestionList = new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_second_sub, null);
        InitView();
        return rootView;
    }

    private void InitView() {
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);


        //recycler表示
        initQuestion();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.subrecyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);


        adapter = new SubRecyclerViewAdapter(mContext, QuestionList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    private void initQuestion(){for (int i=0;i<2;i++) {
        Question q1 = new Question();
        Question q2 = new Question();

        q1.settitle("my 213t title");

        q1.setlike(50);
        q1.setContent("21213312312321");
        q1.setname("122223safas22");
        q2.settitle("my fidfavvvvve");

        q2.setlike(666);
        q2.setContent("213QWRWRQvvvvRQRQ12321");
        q2.setname("12sd3");
        QuestionList.add(q1);
        QuestionList.add(q2);

    }

    }
}
