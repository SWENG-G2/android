package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class ImageElement extends PresentationElement {
    private final String url;
    private final int width;
    private final int height;

    public ImageElement(String url, int width, int height, int x, int y) {
        super(x, y);
        this.url = url;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
    }

    @Override
    public View getView(View parent, ViewGroup container, Slide slide) {
        ImageView imageView = new ImageView(parent.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

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

        String serverURL = parent.getContext().getResources().getString(R.string.serverURL);
        Glide.with(parent).load(serverURL + url).into(imageView);

        return imageView;
    }

    @Override
    public String getSearchableContent() {
        return null;
    }
}
