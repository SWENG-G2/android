package sweng.campusbirdsguide.xml.slide;

import android.content.Context;
import android.view.View;

import java.util.List;

import sweng.campusbirdsguide.presentation.elements.PresentationElement;

public interface Slide {
    int HORIZONTAL_MARGIN = 10; // 5 DP each side
    int HORIZONTAL_PADDING = 20; // 10 dp each side
    int STANDARD_TYPE = 0;
    int EXPANDABLE_TYPE = 1;

    // Getters
    int getWidth();

    int getHeight();

    int getCalculatedWidth();

    int getCalculatedHeight();

    int getType();

    int getId();

    String getTitle();

    List<PresentationElement> getElements();

    // Adder
    void addElement(PresentationElement presentationElement);

    // Customisation
    void slideSpecifics(View containerView, Context context);
}
