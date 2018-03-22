package home.smart.fly.scucommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AskQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.askquestion);

        Button Qbutton = (Button) findViewById(R.id.button_sent);

        Qbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Qtitle = (EditText) findViewById(R.id.edit_title);
                EditText Qcontent = (EditText) findViewById(R.id.edittext_content);
                String Qti = Qtitle.getText().toString();
                String Qcon = Qcontent.getText().toString();
                Intent intent =new Intent(AskQuestionActivity.this,MainActivity.class);
                intent.putExtra("Qti",Qti);
                intent.putExtra("Qcon",Qcon);
                startActivity(intent);
                Toast.makeText(view.getContext(),"上传成功",Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}
