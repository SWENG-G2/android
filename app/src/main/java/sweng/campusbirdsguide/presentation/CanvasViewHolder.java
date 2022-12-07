package sweng.campusbirdsguide.presentation;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.Slide;

public class CanvasViewHolder extends RecyclerView.ViewHolder {
    private final CanvasView canvas;
    private final View itemView;
    public CanvasViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;

        canvas = itemView.findViewById(R.id.canvas);
    }

    public void draw(Slide slide) {
        // Get screen width
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = (width * slide.getHeight()) / slide.getWidth();

        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;

        itemView.setLayoutParams(layoutParams);

        // TODO: Move all this scaling business somewhere else. Can't be good to retrieve screen size for each slide.

        canvas.setSlide(slide);
        canvas.invalidate();
    }
}
