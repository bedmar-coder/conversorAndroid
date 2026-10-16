package dam.miguelangel.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AYuda extends AppCompatActivity {

    //añadimos el tag de los log
    private static final String DEBUG_TAG = "LogsAndroid-2";

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
        setContentView(R.layout.activity_ayuda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();
    }

    //creamos el setui de la activity de ayuda
    private void setUI(){

        //localizamos el botón
        Button volverConversor = findViewById(R.id.btnVolver);

        //le damos funcionalidad con el listener de click
        volverConversor.setOnClickListener(v -> finish());
        //con el finish acbamos la activity para no crear copias innecesarias encima
    }
}