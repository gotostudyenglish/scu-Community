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
        Answer zhaowei = new Answer("赵伟","给他爸爸妈妈打电话吧");
        answerList.add(zhaowei);
        Answer zengguanhui = new Answer("曾光辉","让学校把它开除了");
        answerList.add(zengguanhui);
        Answer zhouzicheng = new Answer("周子晨","鬼鬼，打牌居然不叫我，我要到公安局举报你们,樱樱樱");
        answerList.add(zhouzicheng);
        Answer wangyuxuan = new Answer("王宇轩","我不要，你拿，我的牌有点问题");
        answerList.add(wangyuxuan);
        Answer chenhang = new Answer("陈航","我闷抓");
        answerList.add(chenhang);
    }


}


