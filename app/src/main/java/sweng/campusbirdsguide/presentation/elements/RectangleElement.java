package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;

import sweng.campusbirdsguide.xml.slide.Slide;

public class RectangleElement extends PresentationElement {
    private final int width;
    private final int height;
    private final int colour;
    private final int borderWidth;
    private final int borderColour;
    private final int x;
    private final int y;

    public RectangleElement(int width, int height, int colour, int borderWidth, int borderColour, int x, int y) {
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.borderWidth = borderWidth;
        this.borderColour = borderColour;
        this.x = x;
        this.y = y;

        isShape = true;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        int left = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int right = Math.round(left + (width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int top = dpToPx(y);
        int bottom = top + dpToPx(height);
        int border = dpToPx(borderWidth);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(borderColour);
        paint.setStrokeWidth(border);
        paint.setStyle(Paint.Style.STROKE);

        Rect rectangle = new Rect(left, top, right, bottom);
        canvas.drawRect(rectangle, paint);

        paint.setColor(colour);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(rectangle, paint);
    }

    @Override
    public View getView(View parent, Slide slide) {
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return "RectangleElement{" +
                "width=" + width +
                ", height=" + height +
                ", colour=" + colour +
                ", borderWidth=" + borderWidth +
                ", borderColour=" + borderColour +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
