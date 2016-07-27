package com.tomsbee.sortrecyclerviewsample.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.tomsbee.sortrecyclerviewsample.R;
import com.tomsbee.sortrecyclerviewsample.View.SideBarView;
import com.tomsbee.sortrecyclerviewsample.View.WrapContentHeightViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_recycler_view)
    RecyclerView mainRecyclerView;
    @BindView(R.id.main_view_pager)
    WrapContentHeightViewPager mainViewPager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


}
