package com.Matheus.logincomshared;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText email, senha;
    Button entrar,novo;
    CheckBox box;
    SharedPreferences preferences;

    public static final String PREF_NAME = "login";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SENHA = "senha";
    public static final String REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initComponents();
        entrar.setOnClickListener(v->{
            if (validarDados()) {
                Usuario usuario = new Usuario();

                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                if (box.isChecked()){
                     preferences = getSharedPreferences(PREF_NAME,0);
                     SharedPreferences.Editor dados = preferences.edit();
                    dados.putString(KEY_EMAIL, usuario.getEmail());
                    dados.putString(KEY_SENHA, usuario.getSenha());
                    dados.putBoolean(REMEMBER, true);
                    dados.apply();
                }
                email.setText("");
                senha.setText("");
                Toast.makeText(LoginActivity.this, "Login Efetuado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this, "Digite todos os dados", Toast.LENGTH_SHORT).show();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validarDados() {
        boolean retorno = true;
        if(email.getText().toString().isEmpty()){
            retorno = false;
            email.setError("Campo emaill não pode ficar vazio");
        }
        if (senha.getText().toString().isEmpty()){
            retorno = false;
            senha.setError("Campo senha não pode ficar vazio");
        }
        return retorno;
    }

    private void initComponents() {
        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_senha);
        entrar = findViewById(R.id.btn_entrar);
        novo = findViewById(R.id.btn_Novo);
        box = findViewById(R.id.box);
    }
}