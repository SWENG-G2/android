package sweng.campusbirdsguide.xml.slide;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import sweng.campusbirdsguide.R;

public class DetailSlide extends AbstractSlide implements View.OnClickListener {
    private static final int ANIMATION_DURATION_MS = 200;
    private static final int CHEVRON_ROTATION_ANGLE = 180;
    private RelativeLayout slide;
    private ImageView chevron;
    private int chevronRotation = 0;

    public DetailSlide(int width, int height, String title) {
        super(width, height, title);

        this.type = EXPANDABLE_TYPE;
        // Recalculate width to account for horizontal margin and padding
        this.calculatedWidth = Math.round(this.calculatedWidth - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HORIZONTAL_PADDING + HORIZONTAL_MARGIN, this.displayMetrics));
    }

    @Override
    public void slideSpecifics(View containerView, Context context) {
        containerView.setBackground(ContextCompat.getDrawable(context, R.drawable.slide_bg));

        TextView textView = containerView.findViewById(R.id.title);
        textView.setText(getTitle());

        RelativeLayout titleLayout = containerView.findViewById(R.id.title_layout);
        titleLayout.setOnClickListener(this);

        slide = containerView.findViewById(R.id.slide);
        slide.setVisibility(View.GONE);

        chevron = containerView.findViewById(R.id.chevron);
    }

    @Override
    public void onClick(View view) {
        if (slide != null) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(chevron, "rotation", chevronRotation, chevronRotation + CHEVRON_ROTATION_ANGLE);
            objectAnimator.setDuration(ANIMATION_DURATION_MS);
            objectAnimator.start();
            chevronRotation = (chevronRotation == CHEVRON_ROTATION_ANGLE) ? 0 : CHEVRON_ROTATION_ANGLE;
            if (slide.getVisibility() == View.VISIBLE)
                slide.setVisibility(View.GONE);
            else
                slide.setVisibility(View.VISIBLE);
        }
    }
}
