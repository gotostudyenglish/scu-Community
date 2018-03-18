package home.smart.fly.scucommunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.adapter.AnswerAdapter;
import home.smart.fly.scucommunity.entity.Answer;

public class AnswerActivity extends AppCompatActivity {

    private List<Answer> answerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        initAnswer();
        AnswerAdapter adapter = new AnswerAdapter(AnswerActivity.this,R.layout.answe_item,answerList);
        ListView listView = (ListView) findViewById(R.id.listview_answer);
        listView.setAdapter(adapter);
    }

    private void initAnswer(){
        Answer zhaowei = new Answer();
        answerList.add(zhaowei);
        Answer zengguanhui = new Answer();
        answerList.add(zengguanhui);
        Answer zhouzicheng = new Answer();
        answerList.add(zhouzicheng);
        Answer wangyuxuan = new Answer();
        answerList.add(wangyuxuan);
        Answer chenhang = new Answer();
        answerList.add(chenhang);
    }


}

