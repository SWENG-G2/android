package sweng.campusbirdsguide.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.xml.Slide;

public class SlidesRecyclerViewAdapter extends RecyclerView.Adapter<SlideViewHolder> {
    private final List<Slide> slides;

    public SlidesRecyclerViewAdapter(List<Slide> slides) {
        this.slides = slides;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide, parent, false);

        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.draw(slides.get(position));
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }
}
