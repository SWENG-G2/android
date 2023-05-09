package sweng.campusbirdsguide.presentation.elements;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

/**
 * <code>ImageElement</code> implements the image presentation element with behaviour respectful
 * of SWENG standard v3.
 *
 * This is a contracted, I realise midway. No need to write tests or anything so no need for comments
 */
public class ImageElement extends PresentationElement implements ViewElement {
    private final String url;
    private final int width;
    private final int height;
    private final int rotation;
    private final long delay;
    private final long timeOnScreen;

    /**
     * <code>ImageElement</code> constructor.
     * @param url URL to the image resource.
     * @param width Image width in presentation units.
     * @param height Image height in presentation units.
     * @param x X coordinate on slide.
     * @param y Y coordinate on slide.
     * @param rotation
     * @param delay
     * @param timeOnScreen
     */
    public ImageElement(String url, int width, int height, int x, int y, int rotation, long delay, long timeOnScreen) {
        super(x, y);
        this.url = url;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.delay = delay;
        this.timeOnScreen = timeOnScreen;
    }

    private void applyTimeOnScreen(long initialDelay, ImageView imageView) {
        new Handler().postDelayed(() -> imageView.setVisibility(View.INVISIBLE), initialDelay + timeOnScreen);
    }

    @Override
    public View applyView(View parent, ViewGroup container, Slide slide, int id) {
        ImageView imageView = parent.findViewById(id);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        if (delay > 0) {
            imageView.setVisibility(View.INVISIBLE);

            imageView.postDelayed(() -> imageView.setVisibility(View.VISIBLE), delay);
        }
        if (timeOnScreen > 0)
            applyTimeOnScreen(Math.max(delay, 0), imageView);

        int xPos = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int yPos = dpToPx(y);
        int calculatedWidth = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int calculatedHeight;
        if (height == MATCH_WIDTH_CLIENT_SIDE)
            calculatedHeight = calculatedWidth;
        else
            calculatedHeight = dpToPx(height);

        if (noHorizontalLayoutRulesToApply(layoutParams))
            layoutParams.leftMargin = xPos;
        layoutParams.topMargin = yPos;
        layoutParams.width = calculatedWidth;
        layoutParams.height = calculatedHeight;

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageView.setLayoutParams(layoutParams);

        imageView.setRotation(rotation);

        String serverURL = parent.getContext().getResources().getString(R.string.serverURL);
        Glide.with(parent).load(serverURL + url).into(imageView);

        return imageView;
    }

    @Override
    public String getViewType() {
        return IMAGE_ELEMENT;
    }

    @Override
    public String getSearchableContent() {
        return null;
    }
}
