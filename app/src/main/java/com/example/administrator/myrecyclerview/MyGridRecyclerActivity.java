package com.example.administrator.myrecyclerview;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myrecyclerview.divider.GridViewDivider;
import com.example.administrator.myrecyclerview.divider.GridViewRowLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
public class MyGridRecyclerActivity extends Activity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private List<Student> list;

    private int img = R.mipmap.content_ic;
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        getPersimmions();
        initView();
    }


    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            if (checkSelfPermission(Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_SETTINGS);
            }

			/*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_SETTINGS)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }


            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void initView() {
        list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            list.add(new Student(img, new StringBuilder("中国").append(String.valueOf(i)).toString()));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.grid_rv);

        /**实现类似gridview的布局方式，需要将布局管理器进行设置**/
        //设置布局管理器为网格，每行4个item
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //item的增减动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        //mRecyclerView.addItemDecoration(new GridViewDivider(this,5));
        mRecyclerView.addItemDecoration(new GridViewRowLine(this, list.size(), 3));


        adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MyGridRecyclerActivity.this, ((Student) v.getTag()).name, Toast.LENGTH_SHORT).show();

    }

    //构造数据class
    public class Student {
        public int imgID;
        public String name;

        public Student(int imgId, String name) {
            this.imgID = imgId;
            this.name = name;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {
        @Override
        public MyAdapter.MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(MyGridRecyclerActivity.this).inflate(R.layout.item_gridview, parent, false);
            MyViewHodler myViewHodler = new MyViewHodler(v);
            v.setOnClickListener(MyGridRecyclerActivity.this);
            return myViewHodler;
        }

        @Override
        public void onBindViewHolder(MyViewHodler holder, int position) {

            holder.imageView.setImageResource(list.get(position).imgID);
            holder.textView.setText(list.get(position).name);

            holder.itemView.setTag(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        public class MyViewHodler extends RecyclerView.ViewHolder {
            private TextView textView;
            private ImageView imageView;

            public MyViewHodler(View itemView) {

                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image);
                textView = (TextView) itemView.findViewById(R.id.grid_tv);

            }
        }


    }
}
