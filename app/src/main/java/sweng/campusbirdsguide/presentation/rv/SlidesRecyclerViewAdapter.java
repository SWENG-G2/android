package sweng.campusbirdsguide.presentation.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.slide.Slide;

public class SlidesRecyclerViewAdapter extends RecyclerView.Adapter<SlideRVViewHolder> {
    private final List<Slide> slides;
    private final ListItemClickListener listItemClickListener;
    private final int horizontalMargin;

    public SlidesRecyclerViewAdapter(List<Slide> slides, ListItemClickListener listItemClickListener, int horizontalMargin) {
        this.slides = slides;
        this.listItemClickListener = listItemClickListener;
        this.horizontalMargin = horizontalMargin;
    }


    @NonNull
    @Override
    public SlideRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide, parent, false);

        return new SlideRVViewHolder(view, listItemClickListener, horizontalMargin);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideRVViewHolder holder, int position) {
        holder.draw(slides.get(position));
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }
}
