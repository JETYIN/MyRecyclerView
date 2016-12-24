package com.example.administrator.myrecyclerview.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.myrecyclerview.R;

/**
 * Created by Administrator on 2016/12/23.
 */
public class GridViewRowLine extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    /**
     * 设置绘制下划线属性的drawable，需要设置对应的属性，高度以及对应的颜色
     **/
    private Drawable mDivider;
    private Drawable hDivider;
    private int RowCount;
    private int AllCount;

    private Context mContext;

    /**传入item的数量（list的size，每行的数量）**/
    public GridViewRowLine(Context context, int all, int Count) {
        /*final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();*/
        mDivider = context.getDrawable(R.drawable.protype);
        hDivider = context.getDrawable(R.drawable.protype_two);
        RowCount = Count;
        AllCount = all;
        mContext = context;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        drawHorizontal(c, parent);
        drawVertical(c, parent);

    }

    /**
     * 获取应该绘制几行下划线
     **/
    private int getRowCount() {

        return AllCount % RowCount == 0 ? AllCount / RowCount : AllCount / RowCount + 1;

    }

    private int getColCount() {
        return AllCount % getRowCount() == 0 ? AllCount / getRowCount() - 1 : AllCount / getRowCount();
    }

    /**
     * 获取当前屏幕宽度
     **/
    private int getWindowWidth() {

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {

        for (int i = 0; i < getRowCount(); i++) {
            final View child = parent.getChildAt(RowCount * i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = 0;
            final int right = getWindowWidth();

            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();

            Log.e("g_left", String.valueOf(left));
            Log.e("g_right", String.valueOf(right));
            Log.e("g_top", String.valueOf(top));
            Log.e("g_bottom", String.valueOf(bottom));

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }

    }

    /**
     * 垂直方向设置垂直属性
     **/
    public void drawVertical(Canvas c, RecyclerView parent) {

        for (int i = 0; i < getColCount(); i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            final int top = 0;
            final int bottom = parent.getChildAt(AllCount - 1).getBottom();

            final int left = child.getRight() + params.rightMargin;
            final int right = left + hDivider.getIntrinsicWidth();
            Log.e("v_left", String.valueOf(left));
            Log.e("v_right", String.valueOf(right));
            Log.e("v_top", String.valueOf(top));
            Log.e("v_bottom", String.valueOf(bottom));
            hDivider.setBounds(left, top, right, bottom);
            hDivider.draw(c);
        }

    }


}
