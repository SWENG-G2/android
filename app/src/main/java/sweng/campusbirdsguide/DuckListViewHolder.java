package sweng.campusbirdsguide;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class DuckListViewHolder extends RecyclerView.ViewHolder {
    private final TextView duckName;
    private final ImageView imageView;

    /**
     * DuckListViewHolder constructor.
     *
     * @param itemView View where the list item is going to be rendered.
     */
    public DuckListViewHolder(@NonNull View itemView) {
        super(itemView);

        duckName = itemView.findViewById(R.id.duck_name);
        imageView = itemView.findViewById(R.id.duck_image);

        // Dummy image load
        imageView.setImageDrawable(
                AppCompatResources.getDrawable(itemView.getContext(), R.drawable.ic_baseline_android_24)
        );
    }

    /**
     * Method to set the duck's name in the list.
     * Also updates image's content description.
     *
     * @param name The duck's name.
     */
    public void setDuckName(String name) {
        duckName.setText(name);
        String contentDescription = imageView.getContentDescription().toString();
        imageView.setContentDescription(String.format(Locale.getDefault(), contentDescription, name));
    }
}
