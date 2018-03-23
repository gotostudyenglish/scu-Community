package home.smart.fly.scucommunity.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.adapter.SubRecyclerViewAdapter;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class SecondSubFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SubRecyclerViewAdapter adapter;
    private View rootView;
    private List<Question> QuestionList = new ArrayList<>();
    private String uid;
    private int user_id;
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

        SharedPreferences pref = getActivity().getSharedPreferences("user_id_data",MODE_PRIVATE);
        user_id = pref.getInt("user_id",0);
        uid= Integer.toString(user_id);



        //recycler表示
        initQuestion(uid);
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

    private void initQuestion(String uid){
       HttpUtil.postOkHttpgetdata("http://172.105.196.133:3000/question/queryById", "uid", uid, new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               String ques = response.body().string();
               QuestionList = Utility.handleQuestionResponse(ques);
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       adapter = new SubRecyclerViewAdapter(mContext, QuestionList);
                       recyclerView.setAdapter(adapter);
                   }
               });
           }
       });

    }
}
