package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.view.View;

import lombok.Getter;
import sweng.campusbirdsguide.xml.Slide;

public abstract class PresentationElement {
    @Getter
    protected boolean isShape = false;

    abstract public void draw(Canvas canvas, Slide slide);

    abstract public View getView(View parent, Slide slide);
}
