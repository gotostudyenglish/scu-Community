package home.smart.fly.scucommunity.fragments;
//第一个界面，用了swipeRefreshLayout和RecyclerView，IndexRecyclerViewAdapter
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.AnswerActivity;
import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.adapter.IndexRecyclerViewAdapter;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.image;

public class IndexFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private IndexRecyclerViewAdapter adapter;

    private List<image> imageList = new ArrayList<>();
    private List<Question> QuestionList = new ArrayList<>();
    //
    private View rootView;
    private FloatingActionMenu fam;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_index, null);
        InitView();
        return rootView;
    }

    private void InitView() {

        //
        fam = (FloatingActionMenu) rootView.findViewById(R.id.menu_yellow);
        FloatingActionButton write =(FloatingActionButton) rootView.findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"you write article",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), AnswerActivity.class);
                startActivity(intent);

            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<image> imageList = new ArrayList<>();
                        List<Question> QuestionList = new ArrayList<>();
                        Question m =new Question();
                        QuestionList.add(m);
                        image a =new image();
                        imageList.add(a);

                        adapter.addItem(QuestionList,imageList);
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(mContext,"update 1 questions..",Toast.LENGTH_SHORT).show();
                    }
                },3000);
            }
        });



        //
        initQuestion();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        adapter = new IndexRecyclerViewAdapter(mContext,QuestionList,imageList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 5) {
                    if (dy > 0) {
                        fam.hideMenu(true);
                    } else {
                        fam.showMenu(true);
                    }
                }
            }
        });


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

    //初始化问题信息
    private void initQuestion(){
       /* HttpUtil.sendOkHttpRequest("http://172.105.196.133:3000/question", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String question = response.body().string();
                QuestionList = Utility.handleQuestionResponse(question);

            }
        });*/
    }
}
