package tcc.com.portus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Controle extends AppCompatActivity {

    Button bt;
    EditText edt;
    Firebase meuFirebase;
    DatabaseReference dados;
    String string_dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle);

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
