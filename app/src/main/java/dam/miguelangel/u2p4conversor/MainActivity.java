package dam.miguelangel.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText etResultado = findViewById(R.id.editResultado);
        Button buttonConvertir = findViewById(R.id.buttonConvertir);

        //añadimos el error para mostrar
        TextView errText = findViewById(R.id.errorInfo);

        buttonConvertir.setOnClickListener(v -> {
            try {
                etResultado.setText(convertir(etPulgada.getText().toString()));
                //lo ponemos sin visibilidad por si limpiamos el error
                errText.setVisibility(View.GONE);
            } catch (NumeroMenorException | NingunValorException e) {
                //añadimos el texto al error
                errText.setText(e.getMessage());
                //y lo hacemos visible
                errText.setVisibility(View.VISIBLE);
                Log.e("LogsCOnversor", e.getMessage());
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

    }

    private String convertir(String pulgadaText) {
        //compruebo no esté vacia
        if (pulgadaText.isEmpty()){
            throw new NingunValorException();

        }
        double doubleValue = Double.parseDouble(pulgadaText);
       //controlo sea mayor de 0
        if(doubleValue<1){
            throw new NumeroMenorException();
        }
        double puladaValue = doubleValue * 2.54;

        return String.valueOf(puladaValue);
    }
}