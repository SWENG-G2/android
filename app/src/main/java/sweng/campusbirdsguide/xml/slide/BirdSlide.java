package sweng.campusbirdsguide.xml.slide;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import androidx.core.content.ContextCompat;

import sweng.campusbirdsguide.R;

public class BirdSlide extends AbstractSlide {
    public BirdSlide(int width, int height, String title) {
        super(width, height, title);

        // Recalculate width to account for horizontal margin
        this.calculatedWidth = Math.round(this.calculatedWidth - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HORIZONTAL_MARGIN, this.displayMetrics));
    }

    @Override
    public void slideSpecifics(View containerView, Context context) {
        containerView.setBackground(ContextCompat.getDrawable(context, R.drawable.slide_bg));
    }
}
