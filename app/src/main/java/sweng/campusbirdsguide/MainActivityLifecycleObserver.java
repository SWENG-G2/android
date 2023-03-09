package sweng.campusbirdsguide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivityLifecycleObserver implements DefaultLifecycleObserver {
    private static final String LOAD_BIRD_FROM_STORAGE = "loadBirdFromStorage";


    private final ActivityResultRegistry activityResultRegistry;
    private final Activity activity;
    private ActivityResultLauncher<String> activityResultLauncher;

    public MainActivityLifecycleObserver(@NonNull ActivityResultRegistry activityResultRegistry, Activity activity) {
        this.activityResultRegistry = activityResultRegistry;
        this.activity = activity;
    }

    private void displayXMLFromUri(Uri xmlURI) {
        StringBuilder xmlBuilder = new StringBuilder();
        String xml = null;
        try {
            InputStream inputStream = activity.getContentResolver().openInputStream(xmlURI);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (bufferedReader.ready()) {
                xmlBuilder.append(bufferedReader.readLine());
            }

            xml = xmlBuilder.toString();
            inputStream.close();
        } catch (IOException | OutOfMemoryError e) {
            e.printStackTrace();
        }

        if (xml != null && xml.length() > 0) {
            Intent birdIntent = new Intent(activity.getApplicationContext(), BirdActivity.class);
            birdIntent.putExtra("birdXML", xml);
            try {
                activity.startActivity(birdIntent);
            } catch (RuntimeException runtimeException) {
                runtimeException.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onCreate(owner);

        activityResultLauncher = activityResultRegistry.register(LOAD_BIRD_FROM_STORAGE, owner, new ActivityResultContracts.GetContent(), this::displayXMLFromUri);
    }

    public void loadBirdFromStorage() {
        activityResultLauncher.launch("text/xml");
    }
}
