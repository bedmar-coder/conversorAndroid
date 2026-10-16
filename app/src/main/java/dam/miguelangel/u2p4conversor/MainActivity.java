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

public class MainActivity extends AppCompatActivity {

    //creo estas variables de instancia para controlar los inversores
private boolean pulgada = true;
private boolean centimetro = true;
private boolean yarda = true;

//añadida avriable para controlar el redondeo
    private static final String REDONDEAR = "%.2f";

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


        //necesité hacer el siguiente código para forzar en el hint
        //mayor oscuridad y contraste ya que no se veia sobre el blanco
        //e ignoraba mi xml si no
        etPulgada.setHintTextColor(android.graphics.Color.DKGRAY);
        etPulgada.setTextColor(android.graphics.Color.BLACK);

        etMetro.setHintTextColor(android.graphics.Color.DKGRAY);
        etMetro.setTextColor(android.graphics.Color.BLACK);

        etYarda.setHintTextColor(android.graphics.Color.DKGRAY);
        etYarda.setTextColor(android.graphics.Color.BLACK);

        etResultado.setHintTextColor(android.graphics.Color.GRAY);
        etResultado.setTextColor(android.graphics.Color.BLACK);

        etResultadoMetros.setHintTextColor(android.graphics.Color.GRAY);
        etResultadoMetros.setTextColor(android.graphics.Color.BLACK);

        etResultadoYarda.setHintTextColor(android.graphics.Color.GRAY);
        etResultadoYarda.setTextColor(android.graphics.Color.BLACK);



        //añado el de ayuda
        Button buttonAyuda = findViewById(R.id.Ayuda);



        //añado listener para los botones de inversión
        buttonInversor.setOnClickListener(v -> {
            pulgada = !pulgada;
            if (pulgada){
                //cambio el texto por lo nuevo que pido
                //pero en hint para que desaparezca cuando escribe el usuario
                etPulgada.setHint(v.getContext().getString(R.string.textViewHintPedirPg));
            } else {
                etPulgada.setHint(v.getContext().getString(R.string.textViewHintPedirCm));
            }
        });

        buttonInversorMetros.setOnClickListener(v -> {
            centimetro = !centimetro;
            if (centimetro){
                etMetro.setHint(v.getContext().getString(R.string.textViewHintPedirCm));
            } else {
                etMetro.setHint(v.getContext().getString(R.string.textViewHintPedirM));
            }
        });

        buttonINversorYarda.setOnClickListener(v -> {
            yarda = !yarda;
            if (yarda){
                etYarda.setHint(v.getContext().getString(R.string.textViewHintPedirY));
            } else {
                etYarda.setHint(v.getContext().getString(R.string.textViewHintPedirM));
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

        //añado el de ayuda
        buttonAyuda.setOnClickListener(v -> {
            //lanzamos la activity de ayuda
            startActivity(new Intent(MainActivity.this, AYuda.class));
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
        //Corregida redundancia de returns que había en las ramas previas
        if (inversor){
            puladaValue = Double.parseDouble(pulgadaText) * 2.54;
        } else {
            puladaValue = Double.parseDouble(pulgadaText) / 2.54;
        }


        return String.format(REDONDEAR,puladaValue);
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

        } else {
            puladaValue = Double.parseDouble(cenimetroText) * 100;
        }


        return String.format(REDONDEAR, puladaValue);
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

        } else {
            puladaValue = Double.parseDouble(yardaText)  / 0.9144 ;
        }


        return String.format(REDONDEAR, puladaValue);
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