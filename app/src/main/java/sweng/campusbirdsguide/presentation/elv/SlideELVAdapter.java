package sweng.campusbirdsguide.presentation.elv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.CanvasView;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.xml.slide.Slide;

public class SlideELVAdapter extends BaseExpandableListAdapter {
    private final List<Slide> slides;
    private final Context context;

    public SlideELVAdapter(List<Slide> slides, Context context) {
        this.slides = slides;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return slides.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return slides.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return slides.get(i).getElements();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        // Every child in every parent has a unique id this way
        return i1 * 100L + i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title = ((Slide) getGroup(i)).getTitle();

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.elv_item, null);
        }

        TextView textView = view.findViewById(R.id.slide_title);

        textView.setText(title);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Slide slide = (Slide) getGroup(i);

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.slide, null);
        }

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        layoutParams.width = slide.getCalculatedWidth();
        layoutParams.height = slide.getCalculatedHeight();

        view.setLayoutParams(layoutParams);

        ConstraintLayout constraintLayout = view.findViewById(R.id.slide);
        CanvasView canvas = view.findViewById(R.id.canvas);

        ArrayList<PresentationElement> shapes = new ArrayList<>();
        for (PresentationElement element : slide.getElements()) {
            if (element.isShape()) shapes.add(element);
            else constraintLayout.addView(element.getView(constraintLayout, slide));
        }

        canvas.setSlide(slide);
        canvas.setShapes(shapes);
        canvas.invalidate();

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
