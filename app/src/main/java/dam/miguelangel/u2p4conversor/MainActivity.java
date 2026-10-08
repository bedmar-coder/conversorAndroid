package dam.miguelangel.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        EditText etPulgada = findViewById(R.id.pedirPulgadas);
        EditText etResultadoPulgadas = findViewById(R.id.editResultadoPulgadas);
        Button buttonConvertirPulgadas = findViewById(R.id.buttonConvertirPulgadas);

        //aquí añado mis nuevas cosas añadidas en el layout
        EditText etCentimetro = findViewById(R.id.pedirCentimetros);
        EditText etResultadoCentimetros = findViewById(R.id.editResultadoCentimetros);
        Button buttonConvertirCentietros = findViewById(R.id.buttonConvertirCentimetros);

        buttonConvertirPulgadas.setOnClickListener(v -> {
            try {
                etResultadoPulgadas.setText(convertirEnCentimetros(etPulgada.getText().toString()));
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

        //Le doy funcionalidad a mi boton nuevo
        buttonConvertirCentietros.setOnClickListener(v -> {
            try {
                etResultadoCentimetros.setText(convertirEnPulgadas(etCentimetro.getText().toString()));
            } catch (Exception e){
                Log.e("LogsConversorCentimetros", e.getMessage());
            }
        });
    }

    private String convertirEnCentimetros(String pulgadaText){
        double puladaValue = Double.parseDouble(pulgadaText) * 2.54;

        return String.format("%.2f",puladaValue);
    }

    //Mi metodo para pasar de centímetros a pulgadas
    private String convertirEnPulgadas(String centimetroText){
        double resultadoValue = Double.parseDouble(centimetroText) / 2.54;

        return String.format("%.2f",resultadoValue);
    }
}