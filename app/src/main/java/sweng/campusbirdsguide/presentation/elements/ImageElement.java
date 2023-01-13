package sweng.campusbirdsguide.presentation.elements;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.AllArgsConstructor;
import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

@AllArgsConstructor
public class ImageElement extends PresentationElement {
    private final String url;
    private final int width;
    private final int height;
    private final int x;
    private final int y;

    @Override
    public void draw(Canvas canvas, Slide slide) {}

    @Override
    public View getView(View parent, Slide slide) {
        ImageView imageView = new ImageView(parent.getContext());
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        int xPos = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int yPos = dpToPx(y);
        int calculatedWidth = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int calculatedHeight;
        if (height == -1)
            calculatedHeight = calculatedWidth;
        else
            calculatedHeight = dpToPx(height);

        layoutParams.topToTop = parent.getId();
        layoutParams.startToStart = parent.getId();
        if (x > 0)
            layoutParams.leftMargin = xPos;
        else if (x == -2) { // Server asked for center in parent
            layoutParams.endToEnd = parent.getId();
        }
        layoutParams.topMargin = yPos;
        layoutParams.width = calculatedWidth;
        layoutParams.height = calculatedHeight;

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //imageView.setAdjustViewBounds(true);

        imageView.setLayoutParams(layoutParams);

        String serverURL = parent.getContext().getResources().getString(R.string.serverURL);
        Glide.with(parent).load(serverURL + url).into(imageView);

        return imageView;
    }

    @NonNull
    @Override
    public String toString() {
        return "ImageElement{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
