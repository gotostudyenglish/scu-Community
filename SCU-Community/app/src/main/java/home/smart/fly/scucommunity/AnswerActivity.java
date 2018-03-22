package home.smart.fly.scucommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.adapter.AnswerAdapter;
import home.smart.fly.scucommunity.content.Answer;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AnswerActivity extends AppCompatActivity {

    private List<Answer> answerList = new ArrayList<>();
    private Question question = new Question();
    private int question_id;
    private String question_name;
    private String question_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        Intent intent = getIntent();
        question_id = intent.getIntExtra("question_id",0);
        String qid = Integer.toString(question_id);

        question_name = intent.getStringExtra("question_name");
        TextView name = (TextView)findViewById(R.id.textview_nickname);
        name.setText(question_name);
        TextView nickname= (TextView)findViewById(R.id.text_name);
        nickname.setText(question_name);

        question_content = intent.getStringExtra("question_content");
        TextView content = (TextView)findViewById(R.id.textview_context);
        content.setText(question_content);

        initAnswer(qid);
        AnswerAdapter adapter = new AnswerAdapter(AnswerActivity.this, R.layout.answe_item,answerList);
        ListView listView = (ListView) findViewById(R.id.listview_answer);
        listView.setAdapter(adapter);
    }

    private void initAnswer(String qid){
        HttpUtil.postOkHttpgetdata("http://182.149.199.213:3000/answer/queryById", "Question_ID", qid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String answer = response.body().string();
                answerList = Utility.handleAnswerResponse(answer);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AnswerAdapter adapter = new AnswerAdapter(AnswerActivity.this, R.layout.answe_item,answerList);
                        ListView listView = (ListView) findViewById(R.id.listview_answer);
                        listView.setAdapter(adapter);
                    }
                });

            }
        });
    }


}


