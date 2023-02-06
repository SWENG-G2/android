package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Typeface;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import lombok.Setter;
import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class TextElement extends PresentationElement implements ViewElement {
    private final String font;
    private final int fontSize;
    private final int color;
    private final int timeOnScreen;

    @Setter
    private String content;

    public TextElement(String font, int fontSize, int color, int x, int y, int timeOnScreen) {
        super(x, y);
        this.font = font;
        this.fontSize = fontSize;
        this.color = color;
        this.timeOnScreen = timeOnScreen;
    }

    @Override
    public View applyView(View parent, ViewGroup container, Slide slide, int id) {
        TextView textView = parent.findViewById(id);
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

        if (timeOnScreen > 0)
            textView.postDelayed(() -> textView.setVisibility(View.INVISIBLE), timeOnScreen);

        return textView;
    }

    @Override
    public String getViewType() {
        return TEXT_ELEMENT;
    }

    @Override
    public String getSearchableContent() {
        return content.toLowerCase();
    }
}
