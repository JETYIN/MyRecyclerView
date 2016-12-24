package com.example.administrator.myrecyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myrecyclerview.divider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
public class MyRecyclerViewActivity extends Activity
        implements BaseRecyclerViewAdapter.RecyclerViewClickListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecyclerviewactivity);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        /**recyclerview需要高度解耦，需要通过布局管理器设置起对应的属性，**/
        //实现listview功能，设置线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //分割线可以自己重写divider为引入的库
        /**设置color，size-设置下划线的高度,margin-设置左右的距离**/
       /* recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.RED)
                        .size(1)
                       *//* .margin( //getResources().getDimensionPixelSize(R.dimen.leftmargin),
                                getResources().getDimensionPixelSize(R.dimen.rightmargin))*//*
                        .build());*/
        /**设置渐变的下划线**/
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider_bg).build());
        /**设置item增加或是移除的动画**/

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        /**可以设置画笔
         *
         * Paint paint = new Paint();
         paint.setStrokeWidth(5);
         paint.setColor(Color.BLUE);
         paint.setAntiAlias(true);
         paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
         recyclerView.addItemDecoration(
         new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());**/

        /**设置drawable文件
         *
         * RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
         recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
         .drawable(R.drawable.sample)
         .size(15)
         .build());**/
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new StringBuilder("object").append(String.valueOf(i)).toString());
        }

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
      /*  BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(this, list);
        //设置监听事件
        adapter.setRecyclerViewClickListener(this);
        recyclerView.setAdapter(adapter);*/

    }

    @Override
    public void onItemClick(View v, Object data) {
        Toast.makeText(MyRecyclerViewActivity.this, (String) data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick() {

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(MyRecyclerViewActivity.this, (String) v.getTag(), Toast.LENGTH_SHORT).show();
    }

/**内部类中不能有static声明-interface是static声明**/
    /**
     * recyclerview中使用的adapter需要对RecyclerView.Adapter进行重写并且传入其中的viewhodler
     **/
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {

       /* public interface RecylerViewListener {
            public void onItemClick();

            public void onLongItemClick();
        }*/


        //针对viewhodler进行封装
        @Override
        public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            /**需要将inflate的view传到viewhodler中去**/
            View view = LayoutInflater.from(MyRecyclerViewActivity.this)
                    .inflate(R.layout.item_recyclerview, parent, false);
            view.setOnClickListener(MyRecyclerViewActivity.this);

            MyViewHodler myViewHodler = new MyViewHodler(view);


            return myViewHodler;
        }

        //将adapter和viewhodler进行绑定
        @Override
        public void onBindViewHolder(MyViewHodler holder, int position) {

            holder.textView.setText(list.get(position));
            holder.itemView.setTag(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        /**
         * 需要自行设置监听事件
         **/

        //重写封装好的viewhodler
        public class MyViewHodler extends RecyclerView.ViewHolder {

            public TextView textView;

            public MyViewHodler(View itemView) {
                super(itemView);
                //传入实例化的itemview
                textView = (TextView) itemView.findViewById(R.id.tv_wifi);

            }
        }


    }

}
