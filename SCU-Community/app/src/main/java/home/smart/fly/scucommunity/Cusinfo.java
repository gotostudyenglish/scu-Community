package home.smart.fly.scucommunity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.content.User;
import home.smart.fly.scucommunity.util.HttpUtil;


public class Cusinfo extends AppCompatActivity {
    List<User> userlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusinfo);
        InitView();
    }

    private void InitView() {
        FloatingActionButton button = (FloatingActionButton)findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("user_id_data",MODE_PRIVATE);
                int user_id = preferences.getInt("user_id",0);
                String name="";
                EditText editText =(EditText)findViewById(R.id.edname);
                name=editText.getText().toString();
                String intro="";
                EditText editText1 =(EditText)findViewById(R.id.edintro);
                intro=editText1.getText().toString();
                String signa="";
                EditText editText2 =(EditText)findViewById(R.id.edsigna);
                signa=editText2.getText().toString();
                String inter="";
                EditText editText3 =(EditText)findViewById(R.id.edinter);
                inter=editText3.getText().toString();
                User cusinfo = new User();
                cusinfo.setName(name);
                cusinfo.setUid(user_id);
                cusinfo.setDescription(intro);
                cusinfo.setSignature(signa);
                cusinfo.setHabit(inter);
                userlist.add(cusinfo);
                HttpUtil.postOkHttpRequestion("http://182.149.199.213:3000/users/update",userlist);
                Intent intent = new Intent(Cusinfo.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
