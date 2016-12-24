package com.example.administrator.myrecyclerview.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.administrator.myrecyclerview.R;

import java.util.StringTokenizer;

/**
 * @author zhy绘制gridview的分割线
 * getItemOffsets 可以通过outRect.set()为每个Item设置一定的偏移量，主要用于绘制Decorator。
 */

/**
 * 如果是需要设置下划线的样式-渐变，需要创建两个drawable文件，
 * 在drawHorizontal中绘制水平样式，drawVertical绘制锤子样式
 **/
public class GridViewDivider extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    /**
     * 设置绘制下划线属性的drawable，需要设置对应的属性，高度以及对应的颜色
     **/
    private Drawable mDivider;
    private Drawable hDivider;
    private int RowCount;

    public GridViewDivider(Context context, int Count) {
        /*final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();*/
        mDivider = context.getDrawable(R.drawable.protype);
        hDivider = context.getDrawable(R.drawable.protype_two);
        RowCount = Count;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {

        drawHorizontal(c, parent);
        drawVertical(c, parent);

    }

/*
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }
*/

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
        if (childCount % RowCount != 0) {
            for (int j = 0; j <= RowCount - childCount % RowCount; j++) {

                final View lastView = parent.getChildAt(childCount - 1);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) lastView
                        .getLayoutParams();
                final int left = lastView.getLeft() - params.leftMargin + lastView.getWidth() * j;
                final int top = lastView.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                //final int left = lastView.getRight() + params.rightMargin;
                final int right = lastView.getRight() + mDivider.getIntrinsicWidth() + lastView.getWidth() * j;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    /**
     * 垂直方向设置垂直属性
     **/
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + hDivider.getIntrinsicWidth();
            Log.e("top", String.valueOf(top));
            Log.e("bottom", String.valueOf(bottom));
            Log.e("left", String.valueOf(left));
            Log.e("right", String.valueOf(right));
            hDivider.setBounds(left, top, right, bottom);
            hDivider.draw(c);
        }
        if (childCount % RowCount != 0) {
            for (int j = 0; j <= RowCount - childCount % RowCount; j++) {

                final View lastView = parent.getChildAt(childCount - 1);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) lastView
                        .getLayoutParams();
                final int left = lastView.getRight() + lastView.getWidth() * j;
                final int top = lastView.getTop() - params.topMargin;
                final int bottom = lastView.getBottom() + params.bottomMargin;
                //final int left = lastView.getRight() + params.rightMargin;
                final int right = left + hDivider.getIntrinsicWidth();
                hDivider.setBounds(left, top, right, bottom);
                hDivider.draw(c);
            }
        }

    }

    /*private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        *//**GridLayoutManager最后一列**//*
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        }
        *//**StaggeredGridLayoutManager瀑布流**//*
        else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }*/

    /*private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        *//**GridLayoutManager**//*
        if (layoutManager instanceof GridLayoutManager) {
            //
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        }
        */

    /**
     * StaggeredGridLayoutManager瀑布流
     **//*
        else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }
*/
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
       /* int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, hDivider.getIntrinsicHeight());
        }
        //不是最后一行或是最后一列，则需要mDivider，hDivider
        else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                    hDivider.getIntrinsicHeight());
        }
        */
    }
}