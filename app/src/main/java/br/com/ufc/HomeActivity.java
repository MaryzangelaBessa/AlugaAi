package br.com.ufc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ufc.ui.main.SectionsPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private TextView textEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);
        FirebaseApp.initializeApp(HomeActivity.this);
        auth = FirebaseAuth.getInstance();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        textEmail = findViewById(R.id.editEmail);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_editProfile:
                Toast.makeText(this, "Localização Atual", Toast.LENGTH_SHORT).show();
                Intent ittLA = new Intent(this, CurrentLocation.class);
                startActivity(ittLA);
                return true;

            case R.id.action_2:
                Toast.makeText(this, "Editar perfil", Toast.LENGTH_SHORT).show();
                Intent ittPU = new Intent(this, Perfil_Usuario.class);
                startActivity(ittPU);
                return true;

            case R.id.action_3:
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void onCLickShowCurrentLocation(View view){
        Intent intent = new Intent(this, CurrentLocation.class);
        startActivity(intent);
    }

    public void onCLickCreateCadastro(View view){
        Intent intent = new Intent(this, AddEditarImovelActivity.class);
        startActivity(intent);
    }

    public void onCLickPerfilUsuario(View view){
        Intent intent = new Intent(this, Perfil_Usuario.class);
        startActivity(intent);
    }

    public void onCLickLogout(View view){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

}