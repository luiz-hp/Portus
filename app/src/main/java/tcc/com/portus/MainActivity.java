package tcc.com.portus;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    Button bt;
    EditText edt;
    Firebase meuFirebase;
    DatabaseReference dados;
    String string_dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Executor executor = Executors.newSingleThreadExecutor();
        final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Autenticação de Digital")
                .setSubtitle("Subtítulo")
                .setDescription("Descrição")
                .setNegativeButton("Cancelar", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).build();

        Button btAutenticar = findViewById(R.id.botaoautenticar);

        final MainActivity activity = this;

        btAutenticar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Autenticado", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

        dados = FirebaseDatabase.getInstance().getReference();

        Firebase.setAndroidContext(this);

        bt = findViewById(R.id.botao);
        edt = findViewById(R.id.entrada_dados);

        meuFirebase = new Firebase("https://fir-teste1-ce231.firebaseio.com/");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                string_dados = edt.getText().toString();
                Firebase no_1 = meuFirebase.child("led");
                no_1.setValue(string_dados);
            }
        });
    }
}
