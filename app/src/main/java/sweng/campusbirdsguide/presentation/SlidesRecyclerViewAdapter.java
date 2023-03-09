package sweng.campusbirdsguide.presentation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.utils.ListItemClickListener;
import sweng.campusbirdsguide.xml.slide.Slide;

public class SlidesRecyclerViewAdapter extends RecyclerView.Adapter<SlideViewHolder> implements Filterable {
    private final List<Slide> initialSlides;
    private final ListItemClickListener listItemClickListener;
    private final int horizontalMargin;
    private List<Slide> slides;

    public SlidesRecyclerViewAdapter(List<Slide> slides, ListItemClickListener listItemClickListener, int horizontalMargin) {
        this.slides = slides;
        this.initialSlides = slides;
        this.listItemClickListener = listItemClickListener;
        this.horizontalMargin = horizontalMargin;
    }


    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Slide.STANDARD_TYPE)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_slide, parent, false);

        return new SlideViewHolder(view, parent, listItemClickListener, horizontalMargin);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.draw(slides.get(position));
        System.out.println("BIND " + slides.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }

    @Override
    public int getItemViewType(int position) {
        return slides.get(position).getType();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<Slide> filteredSlides = new ArrayList<>();

                if (constraint.length() == 0)
                    return null;

                String lowercaseConstraint = constraint.toString().toLowerCase();

                for (Slide slide : initialSlides) {
                    if (slide.getTitle().toLowerCase().contains(lowercaseConstraint)) {
                        filteredSlides.add(slide);
                        break;
                    }

                    for (PresentationElement element : slide.getElements()) {
                        String searchableContent = element.getSearchableContent();
                        if (searchableContent != null && searchableContent.contains(lowercaseConstraint)) {
                            filteredSlides.add(slide);
                            break;
                        }
                    }
                }

                filterResults.count = filteredSlides.size();
                filterResults.values = filteredSlides;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null)
                    slides = (List<Slide>) results.values;
                else
                    slides = initialSlides;

                notifyDataSetChanged();
            }
        };
    }
}
