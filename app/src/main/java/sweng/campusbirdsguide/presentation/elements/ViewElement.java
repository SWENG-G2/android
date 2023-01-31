package sweng.campusbirdsguide.presentation.elements;

import android.view.View;
import android.view.ViewGroup;

import sweng.campusbirdsguide.xml.slide.Slide;

public interface ViewElement {
    View applyView(View parent, ViewGroup container, Slide slide, int id);
}
