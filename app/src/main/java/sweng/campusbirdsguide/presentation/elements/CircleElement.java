package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import sweng.campusbirdsguide.xml.slide.Slide;

public class CircleElement extends PresentationElement {
    private final int radius;
    private final int colour;
    private final int borderWidth;
    private final int borderColour;
    private final int x;
    private final int y;

    public CircleElement(int radius, int colour, int borderWidth, int borderColour, int x, int y) {
        this.radius = radius;
        this.colour = colour;
        this.borderWidth = borderWidth;
        this.borderColour = borderColour;
        this.x = x;
        this.y = y;

        isShape = true;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int cx = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int cy;

        int calculatedRadius = Math.round((radius * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int border = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderWidth, displayMetrics));

        if (y == -1)
            cy = cx;
        else if (y < 0) {
            // Server asked to pad client side
            // We know something is above in SP so we add that to the circle radius
            int padding = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, Math.abs(y), displayMetrics));
            cy = calculatedRadius + padding;
        } else
            cy = Math.round((y * slide.getCalculatedHeight()) / (float) slide.getHeight());

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(borderColour);
        paint.setStrokeWidth(border);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(cx, cy, calculatedRadius, paint);

        paint.setColor(colour);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(cx, cy, calculatedRadius, paint);
    }

    @Override
    public View getView(View parent, Slide slide) {
        return null;
    }
}
