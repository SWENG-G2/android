package sweng.campusbirdsguide.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import lombok.Setter;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.xml.slide.Slide;

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

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        /*
            This method is called when the whole view has been laid out.
            Hence parent has a size.
         */
        super.onWindowFocusChanged(hasWindowFocus);

        setWillNotDraw(false);
        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = ((View) getParent()).getMeasuredWidth();
        int parentHeight = ((View) getParent()).getMeasuredHeight();
        setMeasuredDimension(parentWidth, parentHeight);
    }
}
