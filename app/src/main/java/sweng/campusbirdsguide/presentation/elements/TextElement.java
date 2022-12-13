package sweng.campusbirdsguide.presentation.elements;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import lombok.Setter;
import sweng.campusbirdsguide.xml.Slide;

public class TextElement extends PresentationElement {
    private final String font;
    private final int fontSize;
    private final int x;
    private final int y;
    private final int color;

    @Setter
    private String content;

    public TextElement(String font, int fontSize, int x, int y, int color) {
        this.font = font;
        this.fontSize = fontSize;
        this.x = x;
        this.y = y;
        this.color = color;

        isShape = false;
    }

    @Override
    public View getView(View parent, Slide slide) {
        super.getView(parent, slide);

        TextView textView = new TextView(parent.getContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        float xPos = (x * slide.getCalculatedWidth()) / (float) slide.getWidth();
        float yPos = (y * slide.getCalculatedHeight()) / (float) slide.getHeight();

        layoutParams.topToTop = parent.getId();
        layoutParams.startToStart = parent.getId();
        layoutParams.leftMargin = Math.round(xPos);
        layoutParams.topMargin = Math.round(yPos);

        textView.setText(content);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        textView.setTextColor(color);
        textView.setLayoutParams(layoutParams);

        return textView;
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