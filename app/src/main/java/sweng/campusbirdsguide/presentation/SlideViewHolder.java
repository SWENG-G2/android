package sweng.campusbirdsguide.presentation;

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
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = slide.getCalculatedWidth();
        layoutParams.height = slide.getCalculatedHeight();

        itemView.setLayoutParams(layoutParams);

        ArrayList<PresentationElement> shapes = new ArrayList<>();
        for(PresentationElement element : slide.getElements()) {
            if (element.isShape()) shapes.add(element);
            else constraintLayout.addView(element.getView(itemView, slide));
        }

        canvas.setSlide(slide);
        canvas.setShapes(shapes);
        canvas.invalidate();
    }
}
