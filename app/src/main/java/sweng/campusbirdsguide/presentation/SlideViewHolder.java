package sweng.campusbirdsguide.presentation;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.xml.Slide;

public class SlideViewHolder extends RecyclerView.ViewHolder {
    private final CanvasView canvas;
    private final View itemView;
    private final ConstraintLayout constraintLayout;
    public SlideViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;

        canvas = itemView.findViewById(R.id.canvas);
        constraintLayout = itemView.findViewById(R.id.slide);
    }

    public void draw(Slide slide) {
        // TODO: Move all this scaling business somewhere else. Can't be good to retrieve screen size for each slide.
        // Get screen width
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        // This is sort of a guess to adjust slide height based on font size and screen density
        float densityScaling = displayMetrics.scaledDensity / displayMetrics.density;
        int width = displayMetrics.widthPixels;
        int height = Math.round(((width * slide.getHeight() * densityScaling) / ((float) slide.getWidth())));


        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;

        itemView.setLayoutParams(layoutParams);

        ArrayList<PresentationElement> shapes = new ArrayList<>();
        for(PresentationElement element : slide.getElements()) {
            if (element.isShape()) shapes.add(element);
            else element.addToLayout(itemView, slide, constraintLayout);
        }

        canvas.setSlide(slide);
        canvas.setShapes(shapes);
        canvas.invalidate();
    }
}
