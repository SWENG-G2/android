package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class VideoElement extends PresentationElement implements ViewElement {
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
    public View applyView(View parent, ViewGroup container, Slide slide, int id) {
        StyledPlayerView styledPlayerView = parent.findViewById(id);
        ExoPlayer exoPlayer = new ExoPlayer.Builder(parent.getContext()).build();
        styledPlayerView.setPlayer(exoPlayer);
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

        styledPlayerView.setLayoutParams(layoutParams);


        String serverURL = parent.getContext().getResources().getString(R.string.serverURL);
        Uri videoURI = Uri.parse(serverURL + url);
        MediaItem video = MediaItem.fromUri(videoURI);

        exoPlayer.addMediaItem(video);
        exoPlayer.prepare();

        container.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                exoPlayer.release();
            }
        });

        styledPlayerView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(@NonNull View v) {
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull View v) {
                if (exoPlayer.isPlaying())
                    exoPlayer.pause();
            }
        });

        return styledPlayerView;
    }

    @Override
    public String getViewType() {
        return VIDEO_ELEMENT;
    }

    @Override
    public String getSearchableContent() {
        return null;
    }
}
