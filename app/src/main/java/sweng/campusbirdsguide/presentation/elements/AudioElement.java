package sweng.campusbirdsguide.presentation.elements;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class AudioElement extends PresentationElement implements View.OnClickListener, ViewElement {
    private static final int DP_SIZE = 100;
    private final String url;
    private final boolean loop;
    private MediaPlayer mediaPlayer;

    public AudioElement(String url, boolean loop, int x, int y) {
        super(x, y);
        this.url = url;
        this.loop = loop;

        isShape = false;
    }

    @Override
    public View applyView(View parent, ViewGroup container, Slide slide, int id) {
        ImageButton button = parent.findViewById(id);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        float xPos = (x * slide.getCalculatedWidth()) / (float) slide.getWidth();
        float yPos = dpToPx(y);

        Drawable icon = ContextCompat.getDrawable(parent.getContext(), R.drawable.ic_bird);
        button.setBackground(icon);

        if (noHorizontalLayoutRulesToApply(layoutParams))
            layoutParams.leftMargin = Math.round(xPos);
        layoutParams.topMargin = Math.round(yPos);

        int size = dpToPx(DP_SIZE);
        layoutParams.width = size;
        layoutParams.height = size;

        button.setLayoutParams(layoutParams);

        button.setOnClickListener(this);
        setUpMediaPlayer(container);

        button.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(@NonNull View v) {
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                }
            }
        });

        return button;
    }

    @Override
    public String getViewType() {
        return AUDIO_ELEMENT;
    }

    @Override
    public String getSearchableContent() {
        return null;
    }

    private void setUpMediaPlayer(ViewGroup container) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA).build());


        String serverURL = container.getContext().getString(R.string.serverURL);

        // Prepare media player in the background
        Runnable runnable = () -> {
            try {
                mediaPlayer.setDataSource(serverURL + url);
                mediaPlayer.prepare();

                if (loop)
                    mediaPlayer.setLooping(true);

                // Seek to start once media has been played
                mediaPlayer.setOnCompletionListener(mediaPlayer -> mediaPlayer.seekTo(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();

        container.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(@NonNull View v) {
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
            }
        });
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
}
