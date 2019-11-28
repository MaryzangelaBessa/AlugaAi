package br.com.ufc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddEditarImovelActivity extends AppCompatActivity {

    public ImageView imageView;
    public Button button;
    public Button buttonCadastrar;
    public EditText editNomeP, editTipo, editGaragem, editTelefone, editPreco, editTempo, editBanheiros, editQuartos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_editar_imovel_layout);
        Intent intent;
        inicializarComponentes();

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClickImagem(View view){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            String[] permissao = (Manifest.permission.READ_EXTERNAL_STORAGE).split(" ");
            requestPermissions(permissao, 1001);

        }else{
            escolherImagem();
        }
    }

    public void onClickCadastroImovel(View view){
        Intent intent = new Intent(this, FragLocador.class);
        startActivity(intent);
    }
    private void escolherImagem() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent .setType("image/*");
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1000 ){
            imageView.setImageURI(data.getData());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    escolherImagem();
                }else{
                    Toast.makeText(this, "Permissão Negada!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public void inicializarComponentes(){
        editNomeP = findViewById(R.id.editNomeProprietario);
        editTipo = findViewById(R.id.editTipo);
        editGaragem = findViewById(R.id.editGaragem);
        editTelefone = findViewById(R.id.editTelefone);
        editPreco = findViewById(R.id.editValor);
        editTempo = findViewById(R.id.editTempo);
        editBanheiros = findViewById(R.id.editQtdBanheiro);
        editQuartos = findViewById(R.id.editQtdQuarto);
        imageView = findViewById(R.id.iv);
        button = findViewById(R.id.buttonAdicionarFoto);
        buttonCadastrar = findViewById(R.id.buttonCadastrarImovel);
    }



}
