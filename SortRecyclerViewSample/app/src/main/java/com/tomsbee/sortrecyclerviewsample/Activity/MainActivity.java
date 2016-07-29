package com.tomsbee.sortrecyclerviewsample.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.tomsbee.sortrecyclerviewsample.Adapter.UserListAdapter;
import com.tomsbee.sortrecyclerviewsample.Bean.UserInfo;
import com.tomsbee.sortrecyclerviewsample.R;
import com.tomsbee.sortrecyclerviewsample.View.CharacterParser;
import com.tomsbee.sortrecyclerviewsample.View.SideBarView;
import com.tomsbee.sortrecyclerviewsample.View.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_recycler_view)
    RecyclerView mainRecyclerView;

    @BindView(R.id.main_point_layout)
    LinearLayout mainPointLayout;
    @BindView(R.id.main_top_layout)
    LinearLayout mainTopLayout;
    @BindView(R.id.main_header)
    RecyclerViewHeader mainHeader;
    @BindView(R.id.main_side_bar)
    SideBarView mainSideBar;
    @BindView(R.id.main_dialog_letter)
    TextView mainDialogLetter;

    private List<UserInfo> dataSource;
    private UserListAdapter adapter;
    private CharacterParser characterParser;

    /**
     * 内部排序
     */
    private class InnerSortComparator implements Comparator<UserInfo>
    {
        @Override
        public int compare(UserInfo o1, UserInfo o2)
        {
            int result = 0;

            if (o1.getSortLetters().equals("@")
                    || o2.getSortLetters().equals("#"))
            {
                result = -1;
            } else if (o1.getSortLetters().equals("#")
                    || o2.getSortLetters().equals("@"))
            {
                result = 1;
            } else
            {
                result = o1.getSortLetters().compareTo(o2.getSortLetters());
            }
            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        updateView();
    }

    private void initData(){
        dataSource =new ArrayList<>();
        dataSource.add(new UserInfo(1,"张飞"));
        dataSource.add(new UserInfo(2,"董卓"));
        dataSource.add(new UserInfo(3,"成都"));
        dataSource.add(new UserInfo(4,"张小三"));
        dataSource.add(new UserInfo(5,"李小五"));
        dataSource.add(new UserInfo(6,"徐帆"));
        dataSource.add(new UserInfo(7,"羊羊有"));
        dataSource.add(new UserInfo(8,"zhou"));
        dataSource.add(new UserInfo(9,"Yaphets"));
        dataSource.add(new UserInfo(10,"xiao 八"));
        dataSource.add(new UserInfo(11,"郑成功"));
        dataSource.add(new UserInfo(12,"K哥"));
        dataSource.add(new UserInfo(13,"路由"));
        dataSource.add(new UserInfo(14,"马操"));
        dataSource.add(new UserInfo(15,"范围"));
        dataSource.add(new UserInfo(16,"看不见"));
        dataSource.add(new UserInfo(17,"刘备"));
        dataSource.add(new UserInfo(18,"赵云"));
        dataSource.add(new UserInfo(19,"逗逼"));
        dataSource.add(new UserInfo(20,"泥煤"));
        for (UserInfo item : dataSource)
        {
            //实例化汉字转拼音类
            characterParser = CharacterParser.getInstance();
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(item.getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]"))
            {
                item.setSortLetters(sortString);
            } else
            {
                item.setSortLetters("#");
            }
        }

        Collections.sort(dataSource, new InnerSortComparator());
    }
    private void initView(){

        adapter =new UserListAdapter(this, new ArrayList<UserInfo>());
        mainRecyclerView.setAdapter(adapter);
    
         //设置header 的字母
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        mainRecyclerView.addItemDecoration(headersDecor);
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainHeader.attachTo(mainRecyclerView);

        mainSideBar.setTextView(mainDialogLetter);
        //设置右侧触摸监听
        mainSideBar.setOnTouchingLetterChangedListener(new SideBarView.OnTouchingLetterChangedListener()
        {
            @Override
            public void onTouchingLetterChanged(String s)
            {
                //该字母首次出现的位置
                if (adapter.getMapIndex().containsKey(s.toUpperCase()))
                {
                    int positionInData = adapter.getMapIndex().get(s.toUpperCase());
                    mainRecyclerView.scrollToPosition(positionInData);
                }

            }
        });
    }

    private void updateView(){
        adapter.setmSourceDateList(dataSource);
        adapter.notifyDataSetChanged();
    }
}
