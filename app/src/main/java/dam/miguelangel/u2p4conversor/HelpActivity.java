package dam.miguelangel.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Help screen that explains how to use the converter and what the errors mean.
 */
public class HelpActivity extends AppCompatActivity {

    // Tag used for the logs
    private static final String DEBUG_TAG = "LogsAndroid-2";

    // Override the lifecycle methods to log them
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(DEBUG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG_TAG, "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG, "onStop");
    }

    /**
     * Sets up the help screen.
     *
     * @param savedInstanceState the saved state, if the activity is being recreated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(DEBUG_TAG, "onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ayuda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();
    }

    /**
     * Sets up the help screen's button.
     */
    private void setUI() {

        // Find the button
        Button backToConverter = findViewById(R.id.btnVolver);

        // Give it something to do with a click listener
        backToConverter.setOnClickListener(v -> finish());
        // finish() closes this activity so we don't stack unnecessary copies on top
    }
}
