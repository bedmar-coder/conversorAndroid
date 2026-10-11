package dam.miguelangel.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//respuesta:
//Al cambiar la orientación del dispositivo
//la información que no está guardada o introducida por el usuario
//se pierde y la interfaz vuelve a su estado inicial.
//esto pasa porque al cambiar la pantalla
//Android destruye la Activity para cargar los datos de una nueva
//a meneos se hayan guardado

   private static final String DEBUG_TAG = "Android-1";

   //creamos esta clave para poder encontrar luego los datos
    private static final String KEY_RESULTADO = "KEY_RESULTADO";


    //declaramos estas variables fuera de la ui para poder usarlas en otros metodos
    EditText etPulgada;
    EditText etResultado;
    Button buttonConvertir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
         etPulgada = findViewById(R.id.pedirPulgadas);
         etResultado = findViewById(R.id.editResultado);
         buttonConvertir = findViewById(R.id.buttonConvertir);

        buttonConvertir.setOnClickListener(v -> {
            try {
                etResultado.setText(convertir(etPulgada.getText().toString()));
            } catch (Exception e) {
                Log.e(DEBUG_TAG, e.getMessage());
            }
        });

    }

    private String convertir(String pulgadaText){
        double puladaValue = Double.parseDouble(pulgadaText) * 2.54;

        return String.valueOf(puladaValue);
    }

    //sobreescrivimos onsaveinstance para guardar el resultado
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //informamos
        Log.d(DEBUG_TAG, "onSaveInstanceState guardando el resultado en el Bundle");
        if (etResultado != null) {
            outState.putString(KEY_RESULTADO, etResultado.getText().toString());
        }
    }

    //modificamos onrestore para que añada los datos al generar la instancia
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(DEBUG_TAG, "onRestoreInstanceState Recuperando el resultado");
        String resultadoGuardado = savedInstanceState.getString(KEY_RESULTADO, "");
        if (etResultado != null) {
            etResultado.setText(resultadoGuardado);
        }
    }

}