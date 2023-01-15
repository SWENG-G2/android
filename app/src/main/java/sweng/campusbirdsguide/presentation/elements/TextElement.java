package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import lombok.Setter;
import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class TextElement extends PresentationElement {
    private final String font;
    private final int fontSize;
    private final int color;

    @Setter
    private String content;

    public TextElement(String font, int fontSize, int color, int x, int y) {
        super(x, y);
        this.font = font;
        this.fontSize = fontSize;
        this.color = color;

        isShape = false;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
    }

    @Override
    public View getView(View parent, Slide slide) {
        TextView textView = new TextView(parent.getContext());
        // Match parent to allow text to wrap
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        float xPos = (x * slide.getCalculatedWidth()) / (float) slide.getWidth();
        float yPos = dpToPx(y);

        layoutParams.leftMargin = Math.round(xPos);
        layoutParams.topMargin = Math.round(yPos);

        textView.setText(Html.fromHtml(content));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        textView.setTextColor(color);
        textView.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            textView.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_NONE);
        }


        if (font.equals("mono")) {
            Typeface typeface = ResourcesCompat.getFont(parent.getContext(), R.font.chivo_mono_regular);
            textView.setTypeface(typeface);
        }

        return textView;
    }
}
