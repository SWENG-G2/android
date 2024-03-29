package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;

import sweng.campusbirdsguide.xml.slide.Slide;

/**
 * <code>ShapeElement</code> is an interface which mustb be implemented by all presentation elements
 * which are shapes.
 */
public interface ShapeElement {
    /**
     * This method is called when the element is being drawn. The drawing behaviour should be
     * defined within.
     *
     * @param canvas The canvas to draw on.
     * @param slide  The slide where the element is stored.
     */
    void draw(Canvas canvas, Slide slide);
}
