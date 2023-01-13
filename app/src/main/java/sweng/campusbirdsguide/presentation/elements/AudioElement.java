package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class AudioElement extends PresentationElement implements View.OnClickListener, View.OnAttachStateChangeListener {
    private final String url;
    private final boolean loop;
    private final int x;
    private final int y;

    private MediaPlayer mediaPlayer;

    private static final int DP_SIZE = 100;

    public AudioElement(String url, boolean loop, int x, int y) {
        this.url = url;
        this.loop = loop;
        this.x = x;
        this.y = y;

        isShape = false;
    }

    @Override
    public void draw(Canvas canvas, Slide slide) {
    }

    @Override
    public View getView(View parent, Slide slide) {
        ImageButton button = new ImageButton(parent.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        float xPos = (x * slide.getCalculatedWidth()) / (float) slide.getWidth();
        float yPos = dpToPx(y);

        Drawable icon = ContextCompat.getDrawable(parent.getContext(), R.drawable.ic_bird);
        button.setBackground(icon);

        if (x == -3)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        else {
            layoutParams.leftMargin = Math.round(xPos);
        }
        layoutParams.topMargin = Math.round(yPos);

        if (icon != null) {
            int size = dpToPx(DP_SIZE);

            layoutParams.width = size;
            layoutParams.height = size;
        }
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(this);
        button.addOnAttachStateChangeListener(this);

        return button;
    }

    @Override
    public void onClick(View view) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
            else
                mediaPlayer.start();
        }
    }

    @Override
    public void onViewAttachedToWindow(View view) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA).build());

        try {
            String serverURL = view.getContext().getString(R.string.serverURL);
            mediaPlayer.setDataSource(serverURL + url);
            mediaPlayer.prepare();

            if (loop)
                mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(mediaPlayer -> mediaPlayer.seekTo(0));
    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
