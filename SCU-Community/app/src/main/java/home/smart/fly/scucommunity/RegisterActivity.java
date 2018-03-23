package home.smart.fly.scucommunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.content.User;
import home.smart.fly.scucommunity.util.HttpUtil;

public class RegisterActivity extends AppCompatActivity {

    List<User> userlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Initview();
    }

    private void Initview(){
        Button button = (Button) findViewById(R.id.activity_register_register);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String str1="";
                EditText editText1 =(EditText)findViewById(R.id.activity_register_username);
                str1=editText1.getText().toString();
                String str2="";
                EditText editText2 =(EditText)findViewById(R.id.activity_register_password);
                str2=editText2.getText().toString();
                String str3="";
                EditText editText3 =(EditText)findViewById(R.id.activity_register_id);
                str3=editText3.getText().toString();
                User user = new User();
                user.setName(str1);
                user.setPassword(str2);
                user.setStudent_id(str3);
                userlist.add(user);
                HttpUtil.postOkHttpRequestion("http://182.149.199.213:3000/users/register",userlist);
                Intent intent = new Intent(RegisterActivity.this, Login_MainActivity.class);
                startActivity(intent);
            }
        });
        TextView textView = (TextView)findViewById(R.id.activity_register_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,Login_MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
