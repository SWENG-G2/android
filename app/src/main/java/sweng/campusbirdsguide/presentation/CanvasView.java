package sweng.campusbirdsguide.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import lombok.Setter;
import sweng.campusbirdsguide.xml.Slide;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;

public class CanvasView extends View {

    @Setter
    private Slide slide = null;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (slide != null) {

            for (PresentationElement element : slide.getElements()) {
                element.draw(canvas, slide);
                System.out.println("Drawn " + element);
            }
        }
    }
}
