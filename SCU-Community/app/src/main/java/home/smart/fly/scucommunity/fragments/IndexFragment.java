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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.AskQuestionActivity;
import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.adapter.IndexRecyclerViewAdapter;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.image;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private int judge ;
    private String serch;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_index, null);
        judge = 0;
        Intent intent = getActivity().getIntent();
        serch = intent.getStringExtra("search_item");
        judge = intent.getIntExtra("judge",0);
        for (int i=0;i<5;i++) {
            image i1=new image();
            image i2=new image();
            i1.setImageId(R.drawable.image1);
            i2.setImageId(R.drawable.image2);
            imageList.add(i1);
            imageList.add(i2);
        }
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
                Toast.makeText(mContext,"you ask",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), AskQuestionActivity.class);
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
                        judge = 0;
                        initQuestion();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(mContext,"更新成功",Toast.LENGTH_SHORT).show();
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
        if(judge == 0){
            HttpUtil.sendOkHttpRequest("http://172.105.196.133:3000/question", new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {

                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    String question = response.body().string();
                    QuestionList = Utility.handleQuestionResponse(question);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new IndexRecyclerViewAdapter(mContext,QuestionList,imageList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }
            });
        }
        else{
            HttpUtil.postOkHttpgetdata("http://172.105.196.133:3000/question/queryByTitle", "title", serch, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String question = response.body().string();
                   Log.i("11111",question);
                    QuestionList = Utility.handleQuestionResponse(question);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new IndexRecyclerViewAdapter(mContext,QuestionList,imageList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }
            });
        }


    }
}
