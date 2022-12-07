package sweng.campusbirdsguide.xml;

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
        this.fontSize = fontSize;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw() {

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
