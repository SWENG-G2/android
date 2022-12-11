package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import sweng.campusbirdsguide.xml.Slide;


public class LineElement extends PresentationElement {
    private final int thickness;
    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;
    private final int colour;

    public LineElement(int thickness, int fromX, int fromY, int toX, int toY, int colour) {
        this.thickness = (int) (thickness * Resources.getSystem().getDisplayMetrics().scaledDensity);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.colour = colour;

        isShape = true;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        super.draw(canvas, slide);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colour);
        paint.setStrokeWidth(thickness);

        float startX = (fromX * canvas.getWidth()) / (float) slide.getWidth();
        float endX = (toX * canvas.getWidth()) / (float) slide.getWidth();
        float startY = (fromY * canvas.getHeight()) / (float) slide.getHeight();
        float endY = (toY * canvas.getHeight()) / (float) slide.getHeight();

        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    @NonNull
    @Override
    public String toString() {
        return "PresentationLine{" +
                "thickness=" + thickness +
                ", fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                ", colour=" + colour +
                '}';
    }
}
