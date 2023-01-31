package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import sweng.campusbirdsguide.xml.slide.Slide;


public class LineElement extends PresentationElement implements ShapeElement {
    private final int thickness;
    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;
    private final int colour;

    public LineElement(int thickness, int fromX, int fromY, int toX, int toY, int colour) {
        // Line doesn't have x and y
        super(0, 0);

        this.thickness = dpToPx(thickness);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.colour = colour;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colour);
        paint.setStrokeWidth(thickness);

        float startX = (fromX * canvas.getWidth()) / (float) slide.getWidth();
        float endX = (toX * canvas.getWidth()) / (float) slide.getWidth();
        float startY = dpToPx(fromY);
        float endY = dpToPx(toY);

        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    @Override
    public String getViewType() {
        return null;
    }

    @Override
    public String getSearchableContent() {
        return null;
    }
}
