package mx.unam.unica.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnContar;
    TextView txtvContar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContar= findViewById(R.id.btnContar);
        txtvContar= findViewById(R.id.txtvContar);
        btnContar.setOnClickListener(onClickbtnContar);
    }

    View.OnClickListener onClickbtnContar= View ->
    {
     // TareaLarga(); Acceso secuencial
        Hilo();
    };

    public  void Hilo()
    {
        /*
        Hilo anónimo
         */
        new Thread(new Runnable() {
        @Override
        public void run() {
            TareaLarga();
        }
    }).start();
    }

    public void TareaLarga()
    {
        long sum=0;
        for ( int i=0; i< 1_000_000; i++  )
        {
            sum+=i;
            Log.d("Contar:", " " + i );
        }

        Log.d("Suma:", " " + sum);
    }
}