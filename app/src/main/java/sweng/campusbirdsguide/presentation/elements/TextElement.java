package sweng.campusbirdsguide.presentation.elements;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
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
    public void addToLayout(View view, Slide slide, ConstraintLayout constraintLayout) {
        super.addToLayout(view, slide, constraintLayout);

        TextView textView = new TextView(view.getContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        // TODO: View size is 0 here. Calculate this stuff in advance?
        float xPos = (x * view.getWidth()) / (float) slide.getWidth();
        float yPos = (y * view.getHeight()) / (float) slide.getHeight();

        layoutParams.leftMargin = Math.round(xPos);
        layoutParams.topMargin = Math.round(yPos);

        textView.setText(content);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        textView.setTextColor(color);
        textView.setLayoutParams(layoutParams);

        constraintLayout.addView(textView);
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
