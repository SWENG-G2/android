package sweng.campusbirdsguide.xml.slide;

import android.content.Context;
import android.view.View;

import java.util.List;

import sweng.campusbirdsguide.presentation.elements.PresentationElement;

public interface Slide {
    int HORIZONTAL_PADDING = 10; // 5 DP each side

    // Getters
    int getWidth();
    int getHeight();
    int getCalculatedWidth();
    int getCalculatedHeight();
    String getTitle();
    List<PresentationElement> getElements();

    // Adder
    void addElement(PresentationElement presentationElement);

    // Customisation
    void slideSpecifics(View containerView, Context context);
}
