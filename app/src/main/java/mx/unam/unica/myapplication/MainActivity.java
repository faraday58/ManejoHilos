package mx.unam.unica.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnContar;
    Button btnLimpiar;
    TextView txtvContar;
    int sum;
    int contador;
    private Handler manejador= new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContar= findViewById(R.id.btnContar);
        btnLimpiar= findViewById(R.id.btnLimpiar);
        txtvContar= findViewById(R.id.txtvContar);
        btnContar.setOnClickListener(onClickbtnContar);
        btnLimpiar.setOnClickListener(onClickLimpiar);
    }

    View.OnClickListener onClickLimpiar= View ->{
      sum=0;
    };

    View.OnClickListener onClickbtnContar= View ->
    {
     // TareaLarga(); Acceso secuencial
        /*
        Lanzar múltiples hilos anónimos
        contador+=1;
        Log.d("Número de Hilo: "," " + contador);
        Hilo();
         */

        SincronizarHilos();
    };

    private void SincronizarHilos() {

        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                //txtvContar.setText("Pasó un segundo");
                NuevaTareaLarga();

            }
        },1000);
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                //txtvContar.setText("Pasaron 10 segundos");
                NuevaTareaLarga();

            }
        },10000);

    }


    public  void Hilo()
    {
        /*
        Hilo anónimo
         */
        new Thread(new Runnable() {
        @Override
        public void run() {
           // TareaLarga();  Fue utilizado para congelar al hilo principal
            NuevaTareaLarga();
        }
    }).start();
    }


    //Tarea para diferenciar los cálculos obtenidos
    public void NuevaTareaLarga()
    {
        for(int i=0; i < 10; i++)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum+=i;
            Log.d("Tarea", " " + sum);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtvContar.setText(String.valueOf(  sum));
            }
        });
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