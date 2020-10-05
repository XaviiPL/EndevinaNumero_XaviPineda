package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int randomNum = (int) (Math.random() * (100 - 1)) + 1;
    int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // randomNum -- Numero aleatorio de entre 1 y 100



        // textView -- Texto, titulo del juego.

        final TextView titleTextView = findViewById(R.id.textView);

        // textEditNumber
        // Aqui se crea el campo donde sera introducido el numero, al ser editTextNumber, solo aceptara
        // numeros por defecto. Mostrara un teclado con layout numero tambien.

        final EditText textNumber = findViewById(R.id.editTextNumber);
        textNumber.getText();

        // Botón para salir
        // Aparecera una vez se acierte el numero y si es pulsado
        // la aplicacion se cierra

        final Button btExit = findViewById(R.id.btExit);
        btExit.setVisibility(View.GONE);

        btExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        // Botón para reiniciar la partida.
        // AParecera una vez se acierte el numero y preguntará al jugador si quiere
        // volver a jugar, esto hara que se establezca un nuevo numero aleatorio y el
        // jugador tendrá que volver a acertar.

        final Button btReset = findViewById(R.id.btReset);
        btReset.setVisibility(View.GONE);

        btReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                randomNum = (int) (Math.random() * (100 - 1)) + 1;
                attempts = 0;
                textNumber.getText().clear();
                btReset.setVisibility(View.GONE);
                btExit.setVisibility(View.GONE);
            }
        });


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
                    titleTextView.setText("¡Acertaste! Intentos = "+attempts);
                    btReset.setVisibility(View.VISIBLE);
                    btExit.setVisibility(View.VISIBLE);


                }else{
                    if (numero > randomNum){
                        CharSequence text = "Te has pasado...";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        attempts++;
                        textNumber.getText().clear();
                    }
                    if (numero < randomNum){
                        CharSequence text = "Te has quedado corto...";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        attempts++;
                        textNumber.getText().clear();
                    }
                }
            }
        });

        textNumber.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.FLAG_EDITOR_ACTION)) {
                    button.performClick();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }
}