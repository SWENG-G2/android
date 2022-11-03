package sweng.campusbirdsguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView Adapter intended to be used when displaying list of ducks.
 */
public class DuckListRecyclerViewAdapter extends RecyclerView.Adapter<DuckListViewHolder> {
    // Dummy list of ducks
    private String[] ducks;

    /**
     * DuckListRecyclerViewAdapter constructor.
     *
     * @param ducks A string array containing ducks names.
     */
    public DuckListRecyclerViewAdapter(String[] ducks) {
        this.ducks = ducks;
    }

    // This method creates a new view. Invoked by layout manager.
    // Inflate the desired layout and return the ViewHolder responsible for handling modifiers
    // for the layout.
    @NonNull
    @Override
    public DuckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.duck_list_item, parent, false);

        return new DuckListViewHolder(view);
    }


    // This method uses modifiers provided by the ViewHolder to add data to the layout.
    @Override
    public void onBindViewHolder(@NonNull DuckListViewHolder holder, int position) {
        holder.setDuckName(ducks[position]);
    }

    // Return size of the dataset.
    @Override
    public int getItemCount() {
        return ducks.length;
    }
}
