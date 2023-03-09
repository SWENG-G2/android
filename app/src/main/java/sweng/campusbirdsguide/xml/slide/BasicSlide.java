package sweng.campusbirdsguide.xml.slide;

import android.content.Context;
import android.view.View;

public class BasicSlide extends AbstractSlide {

    public BasicSlide(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void slideSpecifics(View containerView, Context context) {
        // No-op, nothing specific
    }
}
