package com.example.administrator.myrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.MyViewHodler>
        implements View.OnClickListener {

    private Context mContext;
    private List mList;


    private RecyclerViewClickListener recyclerViewClickListener;

    public BaseRecyclerViewAdapter(Context context, List list) {
        mContext = context;
        mList = list;
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener listener) {
        recyclerViewClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (null != recyclerViewClickListener) {
            recyclerViewClickListener.onItemClick(v, v.getTag());
        }
    }

    public interface RecyclerViewClickListener {
        void onItemClick(View v, Object data);

        void onItemLongClick();

    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHodler myViewHodler = new MyViewHodler(
                LayoutInflater.from(mContext)
                        .inflate(R.layout.item_recyclerview, parent, false)
        );

        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {

        holder.textView.setText((CharSequence) mList.get(position));
        /**为每个item设置tag，进行回调**/
        holder.itemView.setTag(mList.get(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    //重写封装好的viewhodler
    public class MyViewHodler extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHodler(View itemView) {
            super(itemView);
            //传入实例化的itemview
            textView = (TextView) itemView.findViewById(R.id.tv_wifi);
            //为itemview设置监听
            itemView.setOnClickListener(BaseRecyclerViewAdapter.this);
        }
    }
}
