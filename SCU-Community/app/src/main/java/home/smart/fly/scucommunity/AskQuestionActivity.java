package home.smart.fly.scucommunity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.User;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AskQuestionActivity extends AppCompatActivity {

    List<Question> questionList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    Question question = new Question();
    private String uid;
    private int user_id;
    private static String user_name;
    private String content;
    private String title;
    private EditText editText_title;
    private EditText editText_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.askquestion);

        SharedPreferences pref = getSharedPreferences("user_id_data",MODE_PRIVATE);
        user_id = pref.getInt("user_id",0);
        uid= Integer.toString(user_id);
        findname(uid);

        editText_title = (EditText)findViewById(R.id.edit_title) ;
        editText_content = (EditText) findViewById(R.id.edittext_content);

        Button Qbutton = (Button) findViewById(R.id.button_sent);
        Qbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title  = editText_title.getText().toString();
                content = editText_content.getText().toString();
                question.setUid(user_id);
                question.setContent(content);
                question.setname(user_name);
                question.settitle(title);
                questionList.add(question);
                HttpUtil.postOkHttpRequestion("http://182.149.199.213:3000/question/post",questionList);
                Intent intent =new Intent(AskQuestionActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(),"上传成功",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

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
            }
        });
    }
}
