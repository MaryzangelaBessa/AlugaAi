package br.com.ufc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        textEmail = findViewById(R.id.editEmail);

    }

    public void onCLickCreateCadastro(View view){
        Intent intent = new Intent(this, AddEditarImovelActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //FirebaseUser currentUser = this.auth.getCurrentUser();
    }

}