package home.smart.fly.scucommunity.adapter;
//recyclerview 实现界面2
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.scucommunity.AnswerActivity;
import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.content.Question;

/**
 * Created by co-mall on 2016/9/13.
 */
public class SubRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    private View headView;
    private View footerView;
    private int mHeaderCount = 1;
    private int mFooterCount = 1;

    private List<Question> mQuestion = new ArrayList<>();
    private Context mContext;

    private int menuW, menuH;

    public SubRecyclerViewAdapter(Context mContext, List<Question> QuestionList) {
        this.mQuestion = QuestionList;
        this.mContext = mContext;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        menuW = display.widthPixels / 2;
        menuH = LinearLayout.LayoutParams.WRAP_CONTENT;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new SubHeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.sub_list_headview, null));
        }
        if (viewType == TYPE_FOOTER) {
            return new SubFooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.sub_list_footerview, null));
        }
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.sub_list_item, null);
            final MyViewHolder holder = new MyViewHolder(view);
            holder.ItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();

                    Question question = mQuestion.get(position - mHeaderCount);
                    Intent intent = new Intent(mContext, AnswerActivity.class);
                    intent.putExtra("question_id",question.getQuetsion_ID());
                    intent.putExtra("question_uid",question.getuid());
                    intent.putExtra("question_title",question.gettitle());
                    intent.putExtra("question_content",question.getContent());
                    intent.putExtra("question_name",question.getname());
                    intent.putExtra("question_like",question.getlike());
                    mContext.startActivity(intent);

                    Toast.makeText(view.getContext(), "you click" + question.gettitle(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), "you click1" + question.gettitle(), Toast.LENGTH_SHORT).show();
                }

            });

            return holder;
        }
        return null;
    }



    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubHeaderViewHolder) {

        } else if (holder instanceof SubFooterViewHolder) {

        } else if (holder instanceof MyViewHolder) {
            Question question = mQuestion.get(position-mHeaderCount);


            ((MyViewHolder) holder).from.setText(String.valueOf(question.getname()));

            ((MyViewHolder) holder).textView.setText(String.valueOf(question.getContent()));
            ((MyViewHolder) holder).likenum.setText(String.valueOf(question.getlike()));
            ((MyViewHolder) holder).collectnum.setText(String.valueOf(63));
            ((MyViewHolder) holder).title.setText(String.valueOf(question.gettitle()));
            ((MyViewHolder) holder).subnormalShell.setVisibility(View.VISIBLE);


            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        }
    }
    @Override
    public  int getItemViewType(int position){
        int dataItemCount = mQuestion.size();
        if(mHeaderCount!=0&&position<mHeaderCount){
            return TYPE_HEADER;
        }
        else if(mFooterCount !=0 &&position>=(mHeaderCount+dataItemCount)){
            return TYPE_FOOTER;}
        else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mQuestion.size()+mHeaderCount+mFooterCount;
    }
    public class SubFooterViewHolder extends RecyclerView.ViewHolder{
        public  SubFooterViewHolder(View itemView){
            super(itemView);}
    }
    public class SubHeaderViewHolder extends RecyclerView.ViewHolder{
        public  SubHeaderViewHolder(View itemView){
            super(itemView);}
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView from;
        TextView title;
        TextView likenum;
        TextView collectnum;
        TextView textView;
        View ItemView;
        LinearLayout  subnormalShell;

        public MyViewHolder(View itemView) {
            super(itemView);
            ItemView = itemView;
            from = (TextView) itemView.findViewById(R.id.sub_from);
            title = (TextView) itemView.findViewById(R.id.sub_title);
            likenum = (TextView) itemView.findViewById(R.id.sub_likenum);
            collectnum = (TextView) itemView.findViewById(R.id.sub_answernum);
            textView = (TextView) itemView.findViewById(R.id.sub_content);
            subnormalShell = (LinearLayout) itemView.findViewById(R.id.sub_normalList);




        }
    }


}
