package com.Matheus.logincomshared;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nome, email, senha;
    Button entrar,novo;
    CheckBox lembrar;
    SharedPreferences preferences;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDados()){
                    if(lembrar.isChecked()){
                        preferences = getSharedPreferences("login", 0);
                        SharedPreferences.Editor dados = preferences.edit();
                        dados.putString("Nome", nome.getText().toString());
                        dados.putString("Email", email.getText().toString());
                        dados.putString("Senha", senha.getText().toString());

                    }
                }
            }
        });

        nome.setText("");
        email.setText("");
        senha.setText("");

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validarDados() {
       boolean retorno = true;
        if (nome.getText().toString().isEmpty()) {
            retorno = false;
            nome.setError("Campo nome não pode ficar vazio");
        }
        if (email.getText().toString().isEmpty()) {
            retorno = false;
            email.setError("Campo email não pode ficar vazio");
        }
        if (senha.getText().toString().isEmpty()) {
            retorno = false;
            senha.setError("Campo senha não pode ficar vazio");
        }
       return retorno;
    }

    private void initComponents() {
       nome    = findViewById(R.id.edt_nome);
       email   = findViewById(R.id.edt_email);
       senha   = findViewById(R.id.edt_senha);
       entrar  = findViewById(R.id.btn_entrar);
       novo    = findViewById(R.id.btn_Novo);
       lembrar = findViewById(R.id.box);
    }
}