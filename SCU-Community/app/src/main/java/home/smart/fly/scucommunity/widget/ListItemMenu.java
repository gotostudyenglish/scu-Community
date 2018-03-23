package home.smart.fly.scucommunity.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.content.Question;

/**
 * Created by engineer on 2016/9/21.
 */

public class ListItemMenu extends PopupWindow {
    private Context mContext ;
    private MyClick mc;

    private Question question;


    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            if (mc != null) {
                mc.onClick(v, question);
            }
        }
    };



    public ListItemMenu(int width, int height, Context mContext ,Question question){

        super(width, height);
        this.mContext = mContext;
        this.question = question;
        InitView( );
    }

    private void InitView() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, null);

        setContentView(view);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(00000000));
        TextView like = (TextView) view.findViewById(R.id.like);

        like.setOnClickListener(listener);
        setMyClickListener(new MyClick() {
            @Override
            public void onClick(View v, Question question) {
                int like1=question.getlike();

                like1++;
                question.setlike(like1);

                Toast.makeText(mContext,"点赞成功",Toast.LENGTH_SHORT).show();
                ListItemMenu.super.dismiss();




            }
        });


    }
    private void setMyClickListener (MyClick mc) {
        this.mc = mc;
    }

    private interface MyClick {
        void onClick(View v, Question question);
    }
}
