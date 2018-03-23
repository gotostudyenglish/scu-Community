package home.smart.fly.scucommunity.adapter;
//recycler view 表示
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import home.smart.fly.scucommunity.AnswerActivity;
import home.smart.fly.scucommunity.R;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.image;
import home.smart.fly.scucommunity.util.ScreenUtil;
import home.smart.fly.scucommunity.widget.ListItemMenu;


public class IndexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int Item_type_header = 0;
    public static final int Item_type_content = 1;
    public static final int Item_type_footer=2;
    private  int mHeaderCount =1;
    private  int mFooterCount =1;

    private List<Question> mQuestion = new ArrayList<>();
    private List<image> mimage = new ArrayList<>();
    private Context mContext;


    private int menuW, menuH;

    public IndexRecyclerViewAdapter(Context mContext,  List<Question> QuestionList, List<image>imageList) {
        this.mQuestion = QuestionList;
        this.mContext = mContext;
        this.mimage = imageList;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        menuW = display.widthPixels / 2;
        menuH = LinearLayout.LayoutParams.WRAP_CONTENT;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==Item_type_header){
            return  new HeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.index_list_headview,null));
        }
        if(viewType==Item_type_footer){
            return  new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.index_list_footerview,null));
        }
        if(viewType==Item_type_content) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.index_list_item, null);
            final MyViewHolder holder = new MyViewHolder(view);
            holder.ItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    Question question = mQuestion.get(position-1);
                    Intent intent = new Intent(mContext, AnswerActivity.class);
                    intent.putExtra("question_id",question.getQuetsion_ID());
                    intent.putExtra("question_uid",question.getuid());
                    intent.putExtra("question_title",question.gettitle());
                    intent.putExtra("question_content",question.getContent());
                    intent.putExtra("question_name",question.getname());
                    intent.putExtra("question_like",question.getlike());
                    mContext.startActivity(intent);
                    Toast.makeText(view.getContext(), "you click" + question.gettitle(), Toast.LENGTH_SHORT).show();
                }
            });

            return holder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder){

        }
        else if (holder instanceof FooterViewHolder){

        }
        else if (holder instanceof MyViewHolder) {

            final Question question = mQuestion.get(position-1);
            image image1 = mimage.get(position-1);


            ((MyViewHolder)holder).text1.setText(String.valueOf(question.getname()));
            ((MyViewHolder)holder).pic.setImageResource(image1.getImageId());
            ((MyViewHolder)holder).profile_pic.setImageResource(image1.getProImageId());
            ((MyViewHolder)holder).textView.setText(String.valueOf(question.getContent()));
            ((MyViewHolder)holder).likenum.setText(String.valueOf(question.getlike()));
            ((MyViewHolder)holder).collectnum.setText(String.valueOf(63));
            ((MyViewHolder)holder).title.setText(String.valueOf(question.gettitle()));
            ((MyViewHolder)holder).normalShell.setVisibility(View.VISIBLE);
            ((MyViewHolder)holder).menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ListItemMenu menu = new ListItemMenu(menuW, menuH, mContext,question );
                    menu.update();
                    int offx = ScreenUtil.dip2px(mContext, 24);
                    int offy = ScreenUtil.dip2px(mContext, 24);
                    menu.setAnimationStyle(R.style.MenuAnim);
                    menu.showAsDropDown(((MyViewHolder)holder).menu, -menuW + offx, -offy);

                    //


                }
            });

            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        }
    }




    @Override
    public int getItemCount() {
        return  mQuestion.size()+mHeaderCount +mFooterCount;
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public  FooterViewHolder(View itemView){
            super(itemView);}
    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        public  HeaderViewHolder(View itemView){
            super(itemView);}
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView title;
        TextView likenum;
        TextView collectnum;
        TextView textView;
        ImageView menu;
        CircleImageView profile_pic;
        ImageView pic;
        LinearLayout normalShell;
        View ItemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ItemView = itemView;
            text1 = (TextView) itemView.findViewById(R.id.text1);
            title = (TextView) itemView.findViewById(R.id.title);
            likenum = (TextView) itemView.findViewById(R.id.likenum);
            collectnum = (TextView) itemView.findViewById(R.id.collectnum);
            textView = (TextView) itemView.findViewById(R.id.textView);
            menu = (ImageView) itemView.findViewById(R.id.menu);
            profile_pic = (CircleImageView) itemView.findViewById(R.id.profile_image);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            normalShell = (LinearLayout) itemView.findViewById(R.id.normalList);

        }
    }
    public boolean isHeaderView(int position){
        return  mHeaderCount!=0 && position<mHeaderCount;
    }
    public  int getItemViewType(int position){
        int dataItemCount = mQuestion.size();
        if(mHeaderCount!=0&&position<mHeaderCount){
            return Item_type_header;
        }
        else if(mFooterCount !=0 &&position>=(mHeaderCount+dataItemCount)){
            return Item_type_footer;}
        else {
            return Item_type_content;
        }
    }
    public void addItem(List<Question> QuestionList,List<image> imageList){
        QuestionList.addAll(mQuestion);
        mQuestion.removeAll(mQuestion);
        mQuestion.addAll(QuestionList);
        imageList.addAll(mimage);
        mimage.removeAll(mimage);
        mimage.addAll(imageList);
        notifyDataSetChanged();
    }


}
