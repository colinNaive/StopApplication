package com.ctrip.stopapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ComplexAdapter mComplexAdapter;

    private List<String> mContentList = new ArrayList<>();

    private View mFakeTabView;
    private LinearLayoutManager mLayoutManager;
    private TextView mTvTabName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }
    private void initData() {
        for (int i = 0; i < 40; i++) {
            mContentList.add("Item " + i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFakeTabView = findViewById(R.id.fake_tab_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mComplexAdapter = new ComplexAdapter(mContentList);
        mRecyclerView.setAdapter(mComplexAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
                if (firstPosition >= 2) {
                    mFakeTabView.setVisibility(View.VISIBLE);
                } else {
                    mFakeTabView.setVisibility(View.GONE);
                }
                int tabIndex = (firstPosition - 2) / 10 + 1;
                mTvTabName.setText("Tab " + tabIndex);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mTvTabName = (TextView) findViewById(R.id.tv_tab_name);
    }
}
