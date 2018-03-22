package home.smart.fly.scucommunity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.content.Answer;

/**
 * Created by lenovo on 2018/3/13.
 */

public class AnswerAdapter extends ArrayAdapter<Answer> {
    private int resourceId;
    public AnswerAdapter(Context context, int textViewResourId, List<Answer> objects){
        super(context,textViewResourId,objects);
        resourceId = textViewResourId;
    }

  @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Answer answer = getItem(position);//获取当前answer实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView name = (TextView) view.findViewById(R.id.textview_answername);
        TextView context = (TextView) view.findViewById(R.id.textview_answecontext);
        name.setText(answer.getName());
        context.setText(answer.getContent());
        return view;
    }

}
