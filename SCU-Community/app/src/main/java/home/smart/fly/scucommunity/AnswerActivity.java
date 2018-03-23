package home.smart.fly.scucommunity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.adapter.AnswerAdapter;
import home.smart.fly.scucommunity.content.Answer;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.User;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AnswerActivity extends AppCompatActivity {

    private List<Answer> answerList = new ArrayList<>();
    private List<Answer> answerListsend = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private Question question = new Question();
    private Answer answer = new Answer();

    private int question_id;
    private String question_name;
    private String question_content;
    private String question_title;
    private static String user_name;
    private int user_id;
    private EditText editText_answer;
    private ImageButton sent_answer;
    private String answer_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        Log.i("1111","第一个位置");

        SharedPreferences pref = getSharedPreferences("user_id_data",MODE_PRIVATE);
        user_id = pref.getInt("user_id",0);
        String uid= Integer.toString(user_id);
        Log.i("1111",uid);
        findname(uid);
        Log.i("2222","第二个位置");

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

        question_title = intent.getStringExtra("question_title");
        TextView title = (TextView)findViewById(R.id.textview_title) ;
        title.setText(question_title);


        editText_answer = (EditText)findViewById(R.id.edit_answer) ;
        answer_content = editText_answer.getText().toString();
        sent_answer = (ImageButton)findViewById(R.id.imageButton);
        sent_answer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                answer.setContent(answer_content);
                answer.setName(user_name);
                answer.setUid(user_id);
                answer.setQuestion_ID(question_id);
                answerListsend.add(answer);
                HttpUtil.postOkHttpRequestion("http://182.149.199.213:3000/answer/update",answerListsend);
            }
        });




        initAnswer(qid);
        AnswerAdapter adapter = new AnswerAdapter(AnswerActivity.this, R.layout.answe_item,answerList);
        ListView listView = (ListView) findViewById(R.id.listview_answer);
        listView.setAdapter(adapter);
    }

    private void findname(String uid){
        HttpUtil.postOkHttpgetdata("http://182.149.199.213:3000/users/queryById", "uid", uid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String user = response.body().string();
                userList = Utility.handleUserResponse(user);
                User useruu = new User();
                useruu = userList.get(0);
                user_name = useruu.getName();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AnswerActivity.this, "打印"+user_name, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
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


