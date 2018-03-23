package home.smart.fly.scucommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
/**
 * Created by MWM on 2018/3/23.
 */

public class SearchActivity extends AppCompatActivity{
    private SearchView sv;
    private ListView lv;

    private final String[] mString = {
            "Bei jing",
            "Shang hai", "Chang sha", "Chang chun", "Nan jing",
            "Dong jing", "Ji nan", "Qing dao", "Xiang tan",
            "Zhu zhou", "Heng yang"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        sv = (SearchView)findViewById(R.id.sv);
        lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, mString));
        //这里开启ListView的过滤功能，必须开启，否则不会过滤
        lv.setTextFilterEnabled(true);
        //设置SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        //设置SearchView组件的内默认的显示提示文本
        sv.setQueryHint("查找");
        //设置该SearchView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        //为SearchView设置监听器
        sv.setOnQueryTextListener(new OnQueryTextListener() {
            //用户输入文字激发该方法
            @Override
            //实际应用中应该在该方法中执行实际查询
            public boolean onQueryTextSubmit(String newtext) {
                Intent intent =new Intent(SearchActivity.this,MainActivity.class);
                intent.putExtra("search_item",newtext);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索内容改变触发

                if (!TextUtils.isEmpty(newText)){
                    lv.setFilterText(newText);
                }else{
                    lv.clearTextFilter();
                }
                return false;

            }
        });
    }

}
