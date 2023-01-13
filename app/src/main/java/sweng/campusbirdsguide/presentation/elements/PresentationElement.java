package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import lombok.Getter;
import sweng.campusbirdsguide.xml.slide.Slide;

public abstract class PresentationElement {
    protected static final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
    @Getter
    protected boolean isShape = false;

    abstract public void draw(Canvas canvas, Slide slide);

    abstract public View getView(View parent, Slide slide);

    protected int dpToPx(int input) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input, displayMetrics));
    }
}
