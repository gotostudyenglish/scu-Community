package home.smart.fly.scucommunity.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import home.smart.fly.scucommunity.Cusinfo;
import home.smart.fly.scucommunity.util.Utility;
import home.smart.fly.scucommunity.content.User;
import home.smart.fly.scucommunity.util.HttpUtil;
import home.smart.fly.scucommunity.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import static home.smart.fly.scucommunity.R.id.headview;

public class FourFragment extends Fragment {
    private View rootView;
    private Context mContext;
    private CollapsingToolbarLayout collapsing_toolbar;
    private FloatingActionButton fab;
    private static final String picUrl = "";
    List<User> userList = new ArrayList<>();
    User info = new User();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_four, container, false);
        InitView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void InitView() {
        collapsing_toolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        fab = (FloatingActionButton) rootView.findViewById(R.id.btn);
        SharedPreferences preferences = getActivity().getSharedPreferences("user_id_data",getActivity().MODE_PRIVATE);
        int user_id = preferences.getInt("user_id",0);
        String tmp = Integer.toString(user_id);
        HttpUtil.postOkHttpgetdata("http://182.149.199.213:3000/users/queryById", "uid", tmp, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String abc = response.body().string();
                userList = Utility.handleUserResponse(abc);
                info = userList.get(0);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        collapsing_toolbar.setTitle(info.getName());
                        TextView intro = (TextView) rootView.findViewById(R.id.intro);
                        intro.setText(info.getDescription());
                        TextView signa = (TextView) rootView.findViewById(R.id.signa);
                        signa.setText(info.getSignature());
                        TextView inter = (TextView) rootView.findViewById(R.id.inter);
                        inter.setText(info.getHabit());
                    }
                });
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Cusinfo.class);
                startActivity(intent);
                Toast.makeText(getContext(),"修改个人资料",Toast.LENGTH_SHORT).show();
            }
        });
        CircleImageView view = (CircleImageView) rootView.findViewById(headview);
        Glide.with(mContext).load(picUrl).placeholder(R.drawable.user).into(view);
    }
}
