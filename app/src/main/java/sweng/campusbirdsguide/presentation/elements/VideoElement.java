package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import lombok.AllArgsConstructor;
import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

@AllArgsConstructor
public class VideoElement extends PresentationElement {
    private final String url;
    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final boolean loop;

    @Override
    public void draw(Canvas canvas, Slide slide) {}

    @Override
    public View getView(View parent, Slide slide) {
        VideoView videoView = new VideoView(parent.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        int xPos = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int yPos = dpToPx(y);
        int calculatedWidth = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int calculatedHeight;
        if (height == -1)
            calculatedHeight = calculatedWidth;
        else
            calculatedHeight = dpToPx(height);

        if (x > 0)
            layoutParams.leftMargin = xPos;
        else if (x == -2) { // Server asked for center in parent
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }
        layoutParams.topMargin = yPos;
        layoutParams.width = calculatedWidth;
        layoutParams.height = calculatedHeight;

        videoView.setLayoutParams(layoutParams);

        MediaController mediaController = new MediaController(parent.getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        String serverURL = parent.getContext().getResources().getString(R.string.serverURL);
        Uri videoURI = Uri.parse(serverURL + url);
        videoView.setVideoURI(videoURI);

        return videoView;
    }
}
