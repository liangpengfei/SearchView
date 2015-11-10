package xhome.uestcfei.com.searchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Email : luckyliangfei@gmail.com
 * Created by fei on 15/11/9.
 */
public class SearchView extends View {

    private static final String TAG = "SearchView";
    private Paint circlePaint;
    private Paint arcPaint;
    private Paint linePaint;

    private int centerAngle;
    private int sweepAngle;
    private int radius;
    private int barLength;

    private STATUS status;

    public SearchView(Context context) {
        super(context);
        init();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint(){
            {
                setColor(Color.WHITE);
                setStyle(Style.STROKE);
                setStrokeWidth(10);
            }
        };
        arcPaint = new Paint(){
            {
                setColor(Color.WHITE);
                setStyle(Style.STROKE);
                setStrokeWidth(10);
            }
        };
        linePaint = new Paint(){
            {
                setColor(Color.WHITE);
                setStrokeWidth(10);
            }
        };
        centerAngle = 45;
        sweepAngle = 10;
        status = STATUS.STOP;
        radius = 60;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width / 2, height / 2, radius, circlePaint);
        RectF rectF = new RectF();
        rectF.left = width / 2 - radius;
        rectF.right = width / 2 + radius;
        rectF.top = height / 2 - radius;
        rectF.bottom = height / 2 + radius;

        //draw the moving arc
        canvas.drawArc(rectF, centerAngle - sweepAngle / 2, sweepAngle, false, arcPaint);

        Log.d(TAG, String.valueOf(status));
        if (sweepAngle == 45) {
            status = STATUS.SEARCHING;
        }else if (sweepAngle == 0) {
            status = STATUS.ENDING2;
        }
        //draw the different view and animation
        switch (status) {
            case STARTING:
                arcPaint.setColor(Color.WHITE);
                circlePaint.setColor(Color.parseColor("#67CBFF"));
                barLength = barLength -10;
                canvas.drawLine((float) ((float) width / 2 + (Math.cos(45) * radius + radius) / 2), (float) ((float) height / 2 + (Math.cos(45) * radius + radius) / 2), (float) (width / 2 + barLength * Math.sin(45)), (float) (height / 2 + barLength * Math.sin(45)), linePaint);
                if (barLength < radius) {
                    status = STATUS.STARTING2;
                }
                invalidate();
                break;
            case STARTING2:
                sweepAngle = sweepAngle + 1;
                centerAngle = centerAngle + 5;
                invalidate();
                break;
            case SEARCHING:
                centerAngle = centerAngle + 5;
                invalidate();
                break;
            case ENDING:
                sweepAngle = sweepAngle - 1;
                centerAngle = centerAngle + 5;
                invalidate();
                break;
            case ENDING2:
                arcPaint.setColor(Color.WHITE);
                canvas.drawArc(rectF, 45 - 10 / 2, 10, false, arcPaint);
                canvas.drawLine((float) ((float) width / 2 + (Math.cos(45) * radius + radius) / 2), (float) ((float) height / 2 + (Math.cos(45) * radius + radius) / 2), (float) (width / 2 + barLength * Math.sin(45)), (float) (height / 2 + barLength * Math.sin(45)), linePaint);
                barLength = barLength + 10;
                if (barLength > radius * 2 + radius / 2) {
                    status = STATUS.STOP;
                    //reset
                    init();
                    invalidate();
                } else {
                    invalidate();
                }
                break;
            case STOP:
                barLength = radius * 2 + radius / 2;
                canvas.drawLine((float) ((float) width / 2 + (Math.cos(45) * radius + radius) / 2), (float) ((float) height / 2 + (Math.cos(45) * radius + radius) / 2), (float) (width / 2 + barLength * Math.sin(45)), (float) (height / 2 + barLength * Math.sin(45)), linePaint);
                canvas.drawArc(rectF, 45 - 10 / 2, 10, false, arcPaint);
                break;
            default:
                break;
        }
    }

    //start search
    public void startSearch() {
        status = STATUS.STARTING;
        invalidate();
    }

    //stop search
    public void stopSearch() {
        status = STATUS.ENDING;
        sweepAngle = sweepAngle - 1;
        invalidate();
    }
    private enum STATUS {
        STARTING,ENDING,SEARCHING,STOP,STARTING2,ENDING2
    }
}
