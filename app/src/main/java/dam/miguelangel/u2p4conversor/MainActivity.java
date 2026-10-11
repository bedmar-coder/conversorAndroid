package dam.miguelangel.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //añadimos el tag de los log
    private static final String DEBUG_TAG = "LogsAndroid-1";

    //sobreescribiumos las clases para poner los logs
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

    private void setUI(){
        EditText etPulgada = findViewById(R.id.pedirPulgadas);
        EditText etResultado = findViewById(R.id.editResultado);
        Button buttonConvertir = findViewById(R.id.buttonConvertir);

        buttonConvertir.setOnClickListener(v -> {
            //añadimos el log del botón aquí
            Log.i(DEBUG_TAG, "Se ha pulsado el botón " + buttonConvertir.getText().toString());
            try {
                etResultado.setText(convertir(etPulgada.getText().toString()));
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

    }

    private String convertir(String pulgadaText){
        double puladaValue = Double.parseDouble(pulgadaText) * 2.54;

        return String.valueOf(puladaValue);
    }
}