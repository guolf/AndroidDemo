package cn.guolf.androiddemo.widgets;

import android.widget.ImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/** 圆形ImageView
 * Created by glf on 2015/4/27.
 */
public class ImageCircleView extends ImageView {
    public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
    Paint paint;

    public ImageCircleView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public ImageCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public ImageCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas cns) {
        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap b = getRoundedCornerBitmap(bitmap);
            final Rect rect1 = new Rect(0, 0, b.getWidth(), b.getHeight());
            final Rect rect2 = new Rect(0, 0, getMeasuredWidth(),
                    getMeasuredHeight());
            paint.reset();
            cns.drawBitmap(b, rect1, rect2, paint);
            b.recycle();
        } else {
            super.onDraw(cns);
        }
    }

    /**
     * 圆形头像
     * @param bitmap
     * @return
     */
    public Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        // Bitmap.Config.ARGB_4444比Bitmap.Config.ARGB_8888更省内存
        Canvas canvas = new Canvas(outBitmap);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPX = bitmap.getWidth() / 2 < bitmap.getHeight() / 2 ? bitmap
                .getWidth() : bitmap.getHeight();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }
}
