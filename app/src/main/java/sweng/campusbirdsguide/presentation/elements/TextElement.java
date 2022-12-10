package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;

import lombok.Setter;
import sweng.campusbirdsguide.xml.Slide;

public class TextElement implements PresentationElement {
    private final String font;
    private final int fontSize;
    private final int x;
    private final int y;
    private final int color;

    @Setter
    private String content;

    public TextElement(String font, int fontSize, int x, int y, int color) {
        this.font = font;
        this.fontSize = (int) (fontSize * Resources.getSystem().getDisplayMetrics().scaledDensity);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(fontSize);

        Rect textBounds = new Rect();
        paint.getTextBounds(content, 0, content.length(), textBounds);

        float xPos = (x * canvas.getWidth()) / (float) slide.getWidth();
        float yPos = (y * canvas.getHeight()) / (float) slide.getHeight();

        yPos += textBounds.height();

        canvas.drawText(content, xPos, yPos, paint);
    }

    @NonNull
    @Override
    public String toString() {
        return "PresentationText{" +
                "font='" + font + '\'' +
                ", fontSize=" + fontSize +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                ", content='" + content + '\'' +
                '}';
    }
}
