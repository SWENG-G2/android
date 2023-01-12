package sweng.campusbirdsguide.presentation.rv;

import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.CanvasView;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.xml.slide.Slide;

public class SlideRVViewHolder extends RecyclerView.ViewHolder {
    private final CanvasView canvas;
    private final View itemView;
    private final ConstraintLayout constraintLayout;
    private final int horizontalMargin;

    public SlideRVViewHolder(@NonNull View itemView, ListItemClickListener listItemClickListener, int horizontalMargin) {
        super(itemView);

        this.itemView = itemView;

        if (listItemClickListener != null) {
            itemView.setOnClickListener(view -> listItemClickListener.onItemClick(getAdapterPosition()));
        }

        canvas = itemView.findViewById(R.id.canvas);
        constraintLayout = itemView.findViewById(R.id.slide);
        this.horizontalMargin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, horizontalMargin, itemView.getContext().getResources().getDisplayMetrics()));
    }

    public void draw(Slide slide) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        layoutParams.width = slide.getCalculatedWidth();
        layoutParams.height = slide.getCalculatedHeight();
        layoutParams.setMarginStart(horizontalMargin);

        itemView.setLayoutParams(layoutParams);

        ArrayList<PresentationElement> shapes = new ArrayList<>();
        for (PresentationElement element : slide.getElements()) {
            if (element.isShape()) shapes.add(element);
            else constraintLayout.addView(element.getView(constraintLayout, slide));
        }

        canvas.setSlide(slide);
        canvas.setShapes(shapes);
        canvas.invalidate();

        slide.slideSpecifics(itemView, itemView.getContext());
    }
}
