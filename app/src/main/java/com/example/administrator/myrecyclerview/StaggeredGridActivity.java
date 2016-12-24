package com.example.administrator.myrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/12/23.
 */
public class StaggeredGridActivity extends Activity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.grid_rv);


    }
}
