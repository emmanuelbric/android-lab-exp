package com.example.tweenanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BirdView extends View {
    private Paint paint;

    public BirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float radius = 50;

        // Draw body
        canvas.drawCircle(cx, cy, radius, paint);

        // Draw wings
        canvas.drawCircle(cx - radius, cy, radius / 2, paint);
        canvas.drawCircle(cx + radius, cy, radius / 2, paint);

        // Draw beak
        paint.setColor(Color.YELLOW);
        canvas.drawRect(cx - radius / 2, cy - radius / 2, cx, cy, paint);
    }
}
