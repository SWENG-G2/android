package sweng.campusbirdsguide.presentation;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.ArrayList;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.presentation.elements.ShapeElement;
import sweng.campusbirdsguide.presentation.elements.ViewElement;
import sweng.campusbirdsguide.utils.ListItemClickListener;
import sweng.campusbirdsguide.xml.slide.Slide;

public class SlideViewHolder extends RecyclerView.ViewHolder {
    private final View itemView;
    private final ViewGroup container;
    private final RelativeLayout relativeLayout;
    private final int horizontalMargin;
    private final ListItemClickListener listItemClickListener;
    private final ArrayList<Integer> audioElementIds;
    private final ArrayList<Integer> imageElementIds;
    private final ArrayList<Integer> textElementIds;
    private final ArrayList<Integer> videoElementIds;

    private static final int AUDIO_BASE_ID = 3000;
    private static final int IMAGE_BASE_ID = 4000;
    private static final int TEXT_BASE_ID = 5000;
    private static final int VIDEO_BASE_ID = 6000;
    private static final int CANVAS_ID = 7000;

    public SlideViewHolder(@NonNull View itemView, ViewGroup container, ListItemClickListener listItemClickListener, int horizontalMargin) {
        super(itemView);

        this.itemView = itemView;
        this.container = container;
        this.listItemClickListener = listItemClickListener;

        relativeLayout = itemView.findViewById(R.id.slide);
        this.horizontalMargin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, horizontalMargin, itemView.getContext().getResources().getDisplayMetrics()));

        this.audioElementIds = new ArrayList<>();
        this.imageElementIds = new ArrayList<>();
        this.textElementIds = new ArrayList<>();
        this.videoElementIds = new ArrayList<>();
    }

    private void setParameters(Slide slide) {
        int slideType = slide.getType();
        int calculatedHeight = slide.getCalculatedHeight();
        if (slideType == Slide.STANDARD_TYPE) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            layoutParams.width = slide.getCalculatedWidth();

            // If not, server requested wrap content
            if (calculatedHeight > 0)
                layoutParams.height = calculatedHeight;
            else
                layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            layoutParams.setMarginStart(horizontalMargin);

            itemView.setLayoutParams(layoutParams);
        } else {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = slide.getCalculatedWidth();

            // If not, server requested wrap content
            if (calculatedHeight > 0)
                layoutParams.height = calculatedHeight;
            else
                layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;

            relativeLayout.setLayoutParams(layoutParams);

            // We need to set the container's margin even if there were none set.
            // In this list a standard slide coexists with details slides.
            // The standard slide doesn't need margin
            RecyclerView.LayoutParams containerLayoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            int mHorizontalMargin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Slide.HORIZONTAL_MARGIN / 2f, itemView.getContext().getResources().getDisplayMetrics()));
            containerLayoutParams.setMarginStart(mHorizontalMargin);
            containerLayoutParams.setMarginEnd(mHorizontalMargin);
        }
    }

    private int canReplace(ArrayList<Integer> ids, int counter) {
        try {
            return ids.get(counter);
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    private void applyView(ViewElement element, Slide slide, int id) {
        element.applyView(relativeLayout, container, slide, id);
    }

    private int adjustId(int counter, ArrayList<Integer> list, int baseId) {
        int id = counter + baseId;
        list.add(id);

        return id;
    }

    private void iterateObjects(Slide slide, ArrayList<ShapeElement> shapes) {
        int audioCounter = 0;
        int imageCounter = 0;
        int textCounter = 0;
        int videoCounter = 0;
        for (PresentationElement element : slide.getElements()) {
            if (element.getClass().isAssignableFrom(ShapeElement.class)) shapes.add((ShapeElement) element);
            else {
                ViewElement viewElement = (ViewElement) element;
                switch (element.getViewType()) {
                    case PresentationElement.AUDIO_ELEMENT: {
                        int id = canReplace(audioElementIds, audioCounter);
                        if (id < 0) {
                            // Audio element uses image buttons
                            ImageButton button = new ImageButton(relativeLayout.getContext());
                            id = adjustId(audioCounter, audioElementIds, AUDIO_BASE_ID);
                            button.setId(id);

                            relativeLayout.addView(button);
                        }
                        applyView(viewElement, slide, id);
                        audioCounter++;
                        break;
                    }
                    case PresentationElement.IMAGE_ELEMENT: {
                        int id = canReplace(imageElementIds, imageCounter);
                        if (id < 0) {
                            // Image element uses image views
                            ImageView image = new ImageView(relativeLayout.getContext());
                            id = adjustId(imageCounter, imageElementIds, IMAGE_BASE_ID);
                            image.setId(id);

                            relativeLayout.addView(image);
                        }
                        applyView(viewElement, slide, id);
                        imageCounter++;
                        break;
                    }
                    case PresentationElement.TEXT_ELEMENT: {
                        int id = canReplace(textElementIds, textCounter);
                        if (id < 0) {
                            // Text element uses text views
                            TextView text = new TextView(relativeLayout.getContext());
                            id = adjustId(textCounter, textElementIds, TEXT_BASE_ID);
                            text.setId(id);

                            relativeLayout.addView(text);
                        }
                        applyView(viewElement, slide, id);
                        textCounter++;
                        break;
                    }
                    case PresentationElement.VIDEO_ELEMENT: {
                        int id = canReplace(videoElementIds, videoCounter);
                        if (id < 0) {
                            // Video Element uses StyledPlayerView
                            LayoutInflater layoutInflater = LayoutInflater.from(relativeLayout.getContext());
                            StyledPlayerView styledPlayerView = (StyledPlayerView) layoutInflater.inflate(R.layout.player_view, null);

                            id = adjustId(videoCounter, videoElementIds, VIDEO_BASE_ID);
                            styledPlayerView.setId(id);

                            relativeLayout.addView(styledPlayerView);
                        }
                        applyView(viewElement, slide, id);
                        videoCounter++;
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    public void draw(Slide slide) {
        if (listItemClickListener != null) {
            itemView.setOnClickListener(view -> listItemClickListener.onItemClick(slide.getId()));
        }

        setParameters(slide);

        ArrayList<ShapeElement> shapes = new ArrayList<>();
        iterateObjects(slide, shapes);

        slide.slideSpecifics(itemView, itemView.getContext());

        relativeLayout.requestLayout();

        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                CanvasView canvas = relativeLayout.findViewById(CANVAS_ID);
                if (canvas == null) {
                    canvas = new CanvasView(relativeLayout.getContext());
                    canvas.setId(CANVAS_ID);

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    canvas.setLayoutParams(layoutParams);

                    relativeLayout.addView(canvas);
                }

                canvas.setSlide(slide);
                canvas.setShapes(shapes);

                // Bring canvas to bottom, other views should be on top
                canvas.setZ(-1);

                relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
