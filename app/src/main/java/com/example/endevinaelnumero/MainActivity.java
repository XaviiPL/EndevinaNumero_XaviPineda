package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // randomNum -- Numero aleatorio de entre 1 y 100

        final int randomNum = (int) (Math.random() * (100 - 1)) + 1;

        // textView -- Texto, titulo del juego.d

        final TextView titleTextView = findViewById(R.id.textView);

        // textEditNumber
        // Aqui se crea el campo donde sera introducido el numero, al ser editTextNumber, solo aceptara
        // numeros por defecto. Mostrara un teclado con layout numero tambien.

        final EditText textNumber = findViewById(R.id.editTextNumber);
        textNumber.getText();

        // Botón para enviar el numero.
        // Transforma el numero introducido en el editText (String)
        // a INT utilizando "Integer.parseInt". Una vez obtenido el numero, el programa comprueba si
        // el numero es igual al numero aleatorio anteriormente generado "randomNum". Si el numero
        // es exactamente igual mostrara un Toast dando la enhorabuena.

        // Si el numero es mayor o menor a randomNum, este mostrara otro Toast dependiendo si es mayor
        // o menor, ademas borrará el valor introducido y lo dejara vació listo para introducir otro numero


        final Button button = findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int numero = Integer.parseInt(textNumber.getText().toString());

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                if (numero == randomNum){
                    CharSequence text = "SI! Enhorabuena";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    titleTextView.setText("SI! Enhorabuena");
                }else{
                    if (numero > randomNum){
                        CharSequence text = "Te has pasado...";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        textNumber.getText().clear();
                    }
                    if (numero < randomNum){
                        CharSequence text = "Te has quedado corto...";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        textNumber.getText().clear();
                    }
                }
            }
        });

        textNumber.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    button.performClick();
                    return true;
                }
                return false;
            }
        });
    }
}