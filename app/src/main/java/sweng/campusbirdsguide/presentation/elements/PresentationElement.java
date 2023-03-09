package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import lombok.Getter;
import sweng.campusbirdsguide.xml.slide.Slide;

public abstract class PresentationElement {
    protected static final DisplayMetrics displayMetrics
            = Resources.getSystem().getDisplayMetrics();
    protected static final int MATCH_X_CLIENT_SIDE = -1;
    protected static final int MATCH_WIDTH_CLIENT_SIDE = -1;
    protected static final int ALIGN_CENTER_OF_PARENT = -2;
    private static final int ALIGN_END_OF_PARENT = -3;
    protected static final int MATCH_PARENT = -4;
    protected static final int WRAP_CONTENT = -5;

    // View types
    public static final String AUDIO_ELEMENT = "audio";
    public static final String IMAGE_ELEMENT = "image";
    public static final String TEXT_ELEMENT = "text";
    public static final String VIDEO_ELEMENT = "video";


    protected final int x, y;

    protected PresentationElement(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    abstract public String getViewType();

    abstract public String getSearchableContent();

    protected int dpToPx(int input) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input,
                displayMetrics));
    }

    protected boolean noHorizontalLayoutRulesToApply(RelativeLayout.LayoutParams layoutParams) {
        switch (x) {
            case ALIGN_END_OF_PARENT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                return false;
            case ALIGN_CENTER_OF_PARENT:
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                return false;
            default:
                return true;
        }
    }
}
