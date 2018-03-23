package home.smart.fly.scucommunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final LinearLayout tv_lin= (LinearLayout) findViewById(R.id.text_lin);//要显示的字体
        final LinearLayout tv_hide_lin= (LinearLayout) findViewById(R.id.text_hide_lin);//所谓的布
        ImageView logo= (ImageView) findViewById(R.id.image);//图片
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash);
        logo.startAnimation(animation);//开始执行动画
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //第一个动画执行完后执行第二个动画就是那个字体显示那部分
                animation=AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.text_splash_position);
                tv_lin.startAnimation(animation);
                animation=AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.text_canvas);
                tv_hide_lin.startAnimation(animation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        handler.sendEmptyMessageDelayed(0,4200);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        Intent intent = new Intent(WelcomeActivity.this, Login_MainActivity.class);
        startActivity(intent);
        finish();
    }
}

