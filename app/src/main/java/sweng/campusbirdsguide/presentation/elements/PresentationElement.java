package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;

import sweng.campusbirdsguide.xml.Slide;

public interface PresentationElement {
    void draw(Canvas canvas, Slide slide);
}
