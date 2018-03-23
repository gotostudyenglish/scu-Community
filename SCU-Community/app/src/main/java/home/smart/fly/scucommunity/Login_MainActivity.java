package home.smart.fly.scucommunity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.content.User;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Login_MainActivity extends AppCompatActivity {
    List<User> userlist = new ArrayList<>();
    private Button button;
    private TextView textView;
    String str2="";
    User tmp = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();
    }

    private void InitView() {
        button = (Button) findViewById(R.id.login_bt);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String str1="";
                EditText editText1 =(EditText)findViewById(R.id.student_et);
                str1=editText1.getText().toString();
                EditText editText2 =(EditText)findViewById(R.id.password_et);
                str2=editText2.getText().toString();
                HttpUtil.postOkHttpgetdata("http://172.105.196.133:3000/users/queryByStuId", "student_id", str1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String user = response.body().string();
                        userlist = Utility.handleUserResponse(user);
                        tmp = userlist.get(0);
                        if (tmp.getPassword().equals(str2)){
                            SharedPreferences.Editor editor = getSharedPreferences("user_id_data",MODE_PRIVATE).edit();
                            editor.putInt("user_id",tmp.getUid());
                            editor.apply();
                            Intent intent = new Intent(Login_MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Login_MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Login_MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });

            }
        });
        textView = (TextView)findViewById(R.id.register_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Login_MainActivity.this,RegisterActivity.class);
                startActivity(intent1);
            }
        });
    }
}
