package com.example.cisc482doodle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DoodleView extends View {
    private Paint currentPaint;
    private Path currentPath;

    private List<Stroke> strokes;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentPaint = createNewPaint(0xFF000000, 10, 255);
        currentPath = new Path();
        strokes = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Stroke stroke : strokes) {
            canvas.drawPath(stroke.path, stroke.paint);
        }
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                currentPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                strokes.add(new Stroke(new Path(currentPath), createNewPaint(currentPaint)));
                break;
        }
        invalidate();
        return true;
    }

    public void clearCanvas() {
        strokes.clear();
        currentPath.reset();
        invalidate();
    }

    public void brushSize(int size) {
        currentPaint.setStrokeWidth(size);
    }

    public void brushOpacity(int opacity) {
        opacity = Math.max(0, Math.min(opacity, 255));
        currentPaint.setAlpha(opacity);
    }

    public void changeColor(int color) {
        currentPaint.setColor(color);
    }

    private Paint createNewPaint(int color, float strokeWidth, int alpha) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        paint.setAlpha(alpha);
        return paint;
    }

    private Paint createNewPaint(Paint paint) {
        return new Paint(paint);
    }

    private static class Stroke {
        Path path;
        Paint paint;

        Stroke(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }
}