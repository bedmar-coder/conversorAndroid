package dam.miguelangel.u2p4conversor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Main screen: three unit converters (inches/cm, cm/m and yards/m),
 * each with a button to swap the direction of the conversion.
 */
public class MainActivity extends AppCompatActivity {

    // Tag used for the logs
    private static final String DEBUG_TAG = "LogsAndroid-1";

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

    // Instance flags that control the direction of each converter
    private boolean inch = true;
    private boolean centimetre = true;
    private boolean yard = true;

    // Format used to round the results
    private static final String ROUND = "%.2f";

    /**
     * Sets up the screen and its buttons.
     *
     * @param savedInstanceState the saved state, if the activity is being recreated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(DEBUG_TAG, "onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();
    }

    /**
     * Finds the views and attaches a click listener to every button.
     */
    private void setUI() {
        EditText etInch = findViewById(R.id.pedirPulgadas);
        EditText etResult = findViewById(R.id.editResultado);

        Button buttonConvert = findViewById(R.id.buttonConvertir);

        // My new buttons
        EditText etMetre = findViewById(R.id.pedirCM);
        EditText etResultMetres = findViewById(R.id.editResultadoM);

        Button buttonConvertMetres = findViewById(R.id.buttonConvertirM);

        EditText etYard = findViewById(R.id.pedirY);
        EditText etResultYard = findViewById(R.id.editResultadoY);

        Button buttonConvertYard = findViewById(R.id.buttonConvertirY);
        Button buttonSwap = findViewById(R.id.buttonInvertirPulg);
        Button buttonSwapMetres = findViewById(R.id.buttonInvertirM);
        Button buttonSwapYard = findViewById(R.id.buttonInvertirY);


        // I had to add the following code to force a darker hint with more
        // contrast, because it couldn't be seen on the white background
        // and my XML was being ignored otherwise
        etInch.setHintTextColor(android.graphics.Color.DKGRAY);
        etInch.setTextColor(android.graphics.Color.BLACK);

        etMetre.setHintTextColor(android.graphics.Color.DKGRAY);
        etMetre.setTextColor(android.graphics.Color.BLACK);

        etYard.setHintTextColor(android.graphics.Color.DKGRAY);
        etYard.setTextColor(android.graphics.Color.BLACK);

        etResult.setHintTextColor(android.graphics.Color.GRAY);
        etResult.setTextColor(android.graphics.Color.BLACK);

        etResultMetres.setHintTextColor(android.graphics.Color.GRAY);
        etResultMetres.setTextColor(android.graphics.Color.BLACK);

        etResultYard.setHintTextColor(android.graphics.Color.GRAY);
        etResultYard.setTextColor(android.graphics.Color.BLACK);


        // Add the help button
        Button buttonHelp = findViewById(R.id.Ayuda);


        // Add listeners for the swap buttons
        buttonSwap.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonSwap.getText().toString());
            inch = !inch;
            if (inch) {
                // Change the text to what I'm now asking for,
                // but as a hint so it disappears when the user types
                etInch.setHint(v.getContext().getString(R.string.textViewHintPedirPg));
            } else {
                etInch.setHint(v.getContext().getString(R.string.textViewHintPedirCm));
            }
        });

        buttonSwapMetres.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonSwapMetres.getText().toString());
            centimetre = !centimetre;
            if (centimetre) {
                etMetre.setHint(v.getContext().getString(R.string.textViewHintPedirCm));
            } else {
                etMetre.setHint(v.getContext().getString(R.string.textViewHintPedirM));
            }
        });

        buttonSwapYard.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonSwapYard.getText().toString());
            yard = !yard;
            if (yard) {
                etYard.setHint(v.getContext().getString(R.string.textViewHintPedirY));
            } else {
                etYard.setHint(v.getContext().getString(R.string.textViewHintPedirM));
            }
        });

        buttonConvert.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonConvert.getText().toString());
            try {
                etResult.setText(inchesToCentimetres(etInch.getText().toString(), inch));

            } catch (NoValueException | NumberTooSmallException e) {
                Log.e("LogsConversor", e.getMessage());
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

        // Add the new convert buttons
        buttonConvertMetres.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonConvertMetres.getText().toString());
            try {
                etResultMetres.setText(centimetresToMetres(etMetre.getText().toString(), centimetre));
            } catch (NoValueException | NumberTooSmallException e) {
                Log.e("LogsConversor", e.getMessage());
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

        buttonConvertYard.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonConvertYard.getText().toString());
            try {
                etResultYard.setText(yardsToMetres(etYard.getText().toString(), yard));
            } catch (NoValueException | NumberTooSmallException e) {
                Log.e("LogsConversor", e.getMessage());
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

        // Help button
        buttonHelp.setOnClickListener(v -> {
            Log.i(DEBUG_TAG, "Button pressed: " + buttonHelp.getText().toString());
            // Open the help activity
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        });
    }

    /**
     * Converts inches to centimetres, or the other way round.
     *
     * @param inchText the value typed by the user
     * @param swap true to go from inches to cm, false to go from cm to inches
     * @return the result rounded to two decimals
     */
    private String inchesToCentimetres(String inchText, Boolean swap) {
        // If the value is empty, throw my exception
        if (inchText.isEmpty()) {
            throw new NoValueException();
        }
        double inchValue;

        // Now check the number is 1 or greater
        if (Double.parseDouble(inchText) < 1) {
            throw new NumberTooSmallException();
        }
        // Removed the redundant returns that the previous branches had
        if (swap) {
            inchValue = Double.parseDouble(inchText) * 2.54;
        } else {
            inchValue = Double.parseDouble(inchText) / 2.54;
        }


        return String.format(ROUND, inchValue);
    }


    // New conversion methods, following the same flag logic used in the original converter

    /**
     * Converts centimetres to metres, or the other way round.
     *
     * @param centimetreText the value typed by the user
     * @param swap true to go from cm to m, false to go from m to cm
     * @return the result rounded to two decimals
     */
    private String centimetresToMetres(String centimetreText, Boolean swap) {
        if (centimetreText.isEmpty()) {
            throw new NoValueException();
        }
        double value;
        if (Double.parseDouble(centimetreText) < 1) {
            throw new NumberTooSmallException();
        }
        if (swap) {
            value = Double.parseDouble(centimetreText) / 100;

        } else {
            value = Double.parseDouble(centimetreText) * 100;
        }


        return String.format(ROUND, value);
    }

    /**
     * Converts yards to metres, or the other way round.
     *
     * @param yardText the value typed by the user
     * @param swap true to go from yards to m, false to go from m to yards
     * @return the result rounded to two decimals
     */
    private String yardsToMetres(String yardText, Boolean swap) {
        if (yardText.isEmpty()) {
            throw new NoValueException();
        }
        double value;
        if (Double.parseDouble(yardText) < 1) {
            throw new NumberTooSmallException();
        }
        if (swap) {
            value = Double.parseDouble(yardText) * 0.9144;

        } else {
            value = Double.parseDouble(yardText) / 0.9144;
        }


        return String.format(ROUND, value);
    }

    /**
     * Saves the converters' direction and the results so they survive a screen rotation.
     *
     * @param outState the bundle where the state is stored
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(DEBUG_TAG, "onSaveInstance");

        // Save the boolean flags
        outState.putBoolean("KEY_PULGADA", inch);
        outState.putBoolean("KEY_CENTIMETRO", centimetre);
        outState.putBoolean("KEY_YARDA", yard);

        // Save the result values
        EditText etResult = findViewById(R.id.editResultado);
        EditText etResultMetres = findViewById(R.id.editResultadoM);
        EditText etResultYard = findViewById(R.id.editResultadoY);

        outState.putString("KEY_RES_PULGADA", etResult.getText().toString());
        outState.putString("KEY_RES_METROS", etResultMetres.getText().toString());
        outState.putString("KEY_RES_YARDA", etResultYard.getText().toString());
    }

}
