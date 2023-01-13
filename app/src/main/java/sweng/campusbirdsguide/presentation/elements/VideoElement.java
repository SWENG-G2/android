package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class VideoElement extends PresentationElement {
    private final String url;
    private final int width;
    private final int height;
    private final boolean loop;

    public VideoElement(String url, int width, int height, int x, int y, boolean loop) {
        super(x, y);
        this.url = url;
        this.width = width;
        this.height = height;
        this.loop = loop;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
        // No-op, not a shape
    }

    @Override
    public View getView(View parent, Slide slide) {
        VideoView videoView = new VideoView(parent.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        int xPos = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int yPos = dpToPx(y);
        int calculatedWidth = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int calculatedHeight = dpToPx(height);

        if (noHorizontalLayoutRulesToApply(layoutParams))
            layoutParams.leftMargin = xPos;
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
