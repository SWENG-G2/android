package sweng.campusbirdsguide.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import lombok.Setter;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.presentation.elements.ShapeElement;
import sweng.campusbirdsguide.xml.slide.Slide;

public class CanvasView extends View {

    @Setter
    private Slide slide = null;
    @Setter
    private List<ShapeElement> shapes = null;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (slide != null && shapes != null) {
            for (ShapeElement element : shapes) {
                element.draw(canvas, slide);
            }
        }
    }
}
