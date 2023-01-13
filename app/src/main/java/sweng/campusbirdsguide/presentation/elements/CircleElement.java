package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import sweng.campusbirdsguide.xml.slide.Slide;

public class CircleElement extends PresentationElement {
    private final int radius;
    private final int colour;
    private final int borderWidth;
    private final int borderColour;

    public CircleElement(int radius, int colour, int borderWidth, int borderColour, int x, int y) {
        super(x, y);
        this.radius = radius;
        this.colour = colour;
        this.borderWidth = borderWidth;
        this.borderColour = borderColour;

        isShape = true;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        int cx = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int cy;

        int calculatedRadius = dpToPx(radius);
        int border = dpToPx(borderWidth);

        if (x == ALIGN_CENTER_OF_PARENT) {
            cx = canvas.getWidth() / 2;
        }

        if (y == MATCH_X_CLIENT_SIDE)
            cy = cx;
        else if (y < 0) {
            // Server asked to pad client side
            // We know something is above in DP so we add that to the circle radius
            int padding = dpToPx(Math.abs(y));
            cy = calculatedRadius + padding;
        } else
            cy = dpToPx(y);


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
