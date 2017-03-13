package com.sevenpeakssoftware.fott.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by razir on 1/4/2017.
 */

public class LayoutDivider extends RecyclerView.ItemDecoration {
    private int height;
    private View view;

    public LayoutDivider(Context context, int layout) {
        view = LayoutInflater.from(context).inflate(layout, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        height = view.getMeasuredHeight();
    }

    private static Bitmap getBitmapFromView(View view, int width, int height) {
        Bitmap returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = height;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        Bitmap bitmap = getBitmapFromView(view, dividerRight - dividerLeft, height);
        Paint paint = new Paint();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int dividerTop = child.getBottom() + params.bottomMargin;
            canvas.drawBitmap(bitmap, dividerLeft, dividerTop, paint);
        }
        bitmap.recycle();
    }
}
