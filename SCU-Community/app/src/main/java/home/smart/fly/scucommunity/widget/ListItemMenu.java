package home.smart.fly.scucommunity.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import home.smart.fly.scucommunity.R;

/**
 * Created by engineer on 2016/9/21.
 */

public class ListItemMenu extends PopupWindow {
    private Context mContext ;



    public ListItemMenu(int width, int height, Context mContext) {

        super(width, height);
        this.mContext = mContext;
        InitView( );
    }

    private void InitView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, null);

        setContentView(view);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(00000000));
        TextView like = (TextView) view.findViewById(R.id.like);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "like it", Toast.LENGTH_SHORT).show();



            }
        });


    }
}
