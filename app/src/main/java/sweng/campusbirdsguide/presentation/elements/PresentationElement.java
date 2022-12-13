package sweng.campusbirdsguide.presentation.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import lombok.Getter;
import sweng.campusbirdsguide.xml.Slide;

public abstract class PresentationElement {
    @Getter
    protected boolean isShape;

    public void draw(Canvas canvas, Slide slide) {
        // No-OP by default
    }

    public View getView(View parent, Slide slide) {
        return null;
    }
}