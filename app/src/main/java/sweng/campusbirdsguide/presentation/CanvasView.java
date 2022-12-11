package sweng.campusbirdsguide.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import sweng.campusbirdsguide.xml.Slide;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;

public class CanvasView extends View {

    @Setter
    private Slide slide = null;
    @Setter
    private List<PresentationElement> shapes = null;

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
        if (slide != null && shapes != null) {

            for (PresentationElement element : shapes) {
                element.draw(canvas, slide);
            }
        }
    }
}
