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

    //creo estas variables de instancia para controlar los inversores
private boolean pulgada = true;
private boolean centimetro = true;
private boolean yarda = true;

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

        //Añado mis nuevos botones
        EditText etMetro = findViewById(R.id.pedirCM);
        EditText etResultadoMetros = findViewById(R.id.editResultadoM);
        Button buttonConvertirMetros = findViewById(R.id.buttonConvertirM);
        EditText etYarda = findViewById(R.id.pedirY);
        EditText etResultadoYarda = findViewById(R.id.editResultadoY);
        Button buttonConvertirYarda = findViewById(R.id.buttonConvertirY);
        Button buttonInversor = findViewById(R.id.buttonInvertirPulg);
        Button buttonInversorMetros = findViewById(R.id.buttonInvertirM);
        Button buttonINversorYarda = findViewById(R.id.buttonInvertirY);


        //añado listener para los botones de inversión
        buttonInversor.setOnClickListener(v -> {
            pulgada = !pulgada;
            if (pulgada){
                //cambio el texto por lo nuevo que pido
                etPulgada.setText(v.getContext().getString(R.string.textViewHintPedirPg));
            } else {
                etPulgada.setText(v.getContext().getString(R.string.textViewHintPedirCm));
            }
        });

        buttonInversorMetros.setOnClickListener(v -> {
            centimetro = !centimetro;
            if (centimetro){
                etMetro.setText(v.getContext().getString(R.string.textViewHintPedirCm));
            } else {
                etMetro.setText(v.getContext().getString(R.string.textViewHintPedirM));
            }
        });

        buttonINversorYarda.setOnClickListener(v -> {
            yarda = !yarda;
            if (yarda){
                etYarda.setText(v.getContext().getString(R.string.textViewHintPedirY));
            } else {
                etYarda.setText(v.getContext().getString(R.string.textViewHintPedirM));
            }
        });

        buttonConvertir.setOnClickListener(v -> {
            try {
                etResultado.setText(pulgadasACentimetros(etPulgada.getText().toString(), pulgada));

            } catch (NingunValorException | NumeroMenorException e){
                Log.e("LogsConversor", e.getMessage());
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });

        //añado los nuevos botones de convertir
        buttonConvertirMetros.setOnClickListener(v -> {
           try {
               etResultadoMetros.setText(centimetrosAMetros(etMetro.getText().toString(), centimetro));
           } catch (NingunValorException | NumeroMenorException e){
               Log.e("LogsConversor", e.getMessage());
           } catch (Exception e) {
               Log.e("LogsConversor", e.getMessage());
           }
        });

        buttonConvertirYarda.setOnClickListener(v -> {
            try {
                etResultadoYarda.setText(yardasAMetros(etYarda.getText().toString(), yarda));
            } catch (NingunValorException | NumeroMenorException e){
                Log.e("LogsConversor", e.getMessage());
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage());
            }
        });
    }

    private String pulgadasACentimetros(String pulgadaText, Boolean inversor){
        //si el valor está vacio lanzo mi excepción
        if (pulgadaText.isEmpty()){
            throw new NingunValorException();
        }
        double puladaValue;

        //ahora compruebo el número sea igual o mayor a 1
        if (Double.parseDouble(pulgadaText)<1){
            throw new NumeroMenorException();
        }
        if (inversor){
            puladaValue = Double.parseDouble(pulgadaText) * 2.54;
            return String.valueOf(puladaValue);
        }
        puladaValue = Double.parseDouble(pulgadaText) / 2.54;

        return String.valueOf(puladaValue);
    }



    //genero los nuevos metodos de conversión
    //siguiendo la lógica de semáforo empleada en la edición del convertir original
    private String centimetrosAMetros(String cenimetroText, Boolean inversor){
        if (cenimetroText.isEmpty()){
            throw new NingunValorException();
        }
        double puladaValue;
        if (Double.parseDouble(cenimetroText)<1){
            throw new NumeroMenorException();
        }
        if (inversor){
            puladaValue = Double.parseDouble(cenimetroText) / 100;
            return String.valueOf(puladaValue);
        }
        puladaValue = Double.parseDouble(cenimetroText) * 100;

        return String.valueOf(puladaValue);
    }

    private String yardasAMetros(String yardaText, Boolean inversor){
        if (yardaText.isEmpty()){
            throw new NingunValorException();
        }
        double puladaValue;
        if (Double.parseDouble(yardaText)<1){
            throw new NumeroMenorException();
        }
        if (inversor){
            puladaValue = Double.parseDouble(yardaText) * 0.9144;
            return String.valueOf(puladaValue);
        }
        puladaValue = Double.parseDouble(yardaText)  / 0.9144 ;

        return String.valueOf(puladaValue);
    }

    //sobreescribimos el guardar instancia para consevar los datos
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guardamos los semaforos booleanos
        outState.putBoolean("KEY_PULGADA", pulgada);
        outState.putBoolean("KEY_CENTIMETRO", centimetro);
        outState.putBoolean("KEY_YARDA", yarda);

        // Guardamos los valores de los resultados
        EditText etResultado = findViewById(R.id.editResultado);
        EditText etResultadoMetros = findViewById(R.id.editResultadoM);
        EditText etResultadoYarda = findViewById(R.id.editResultadoY);

        outState.putString("KEY_RES_PULGADA", etResultado.getText().toString());
        outState.putString("KEY_RES_METROS", etResultadoMetros.getText().toString());
        outState.putString("KEY_RES_YARDA", etResultadoYarda.getText().toString());
    }

}