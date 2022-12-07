package sweng.campusbirdsguide.xml;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import lombok.Setter;

public class PresentationText implements PresentationElement {
    private final String font;
    private final int fontSize;
    private final int x;
    private final int y;
    private final int color;

    @Setter
    private String content;

    public PresentationText(String font, int fontSize, int x, int y, int color) {
        this.font = font;
        this.fontSize = (int) (fontSize * Resources.getSystem().getDisplayMetrics().scaledDensity);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(fontSize);

        float yPos = canvas.getHeight() - y;

        canvas.drawText(content, x, yPos, paint);
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
