package br.com.ufc;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ufc.transactions.LineAdpater;
import br.com.ufc.ui.main.AddImagemImovel;

public class AddEditarImovelActivity extends AppCompatActivity {

    public ImageView imageView;
    public Button button,button3;
    public Button buttonCadastrar;
    public EditText editNomeP,editTelefone, editPreco, editTempo, editBanheiros, editQuartos;
    public RadioGroup radioGroup;
    public RecyclerView recyclerView;
    public Switch editGaragem;
    public int PICK_IMAGE_MULTIPLE = 1;
    public String imageEncoded;
    ArrayList<Uri> mArrayUri;
    public List<String> imagesEncodedList;
    private LineAdpater adpater;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_editar_imovel_layout);
        Intent intent;
        inicializarComponentes();


        Button btn = findViewById(R.id.buttonCadastrarImovel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeDono = editNomeP.getText().toString();
                String telefone = editTelefone.getText().toString();
                int tipo = radioGroup.getCheckedRadioButtonId();
                double valor = Double.parseDouble(editPreco.getText().toString());
                int tempo = Integer.parseInt(editTempo.getText().toString());
                int quantidadeQuarto = Integer.parseInt(editQuartos.getText().toString());;
                int getQuantidadeBanheiro = Integer.parseInt(editBanheiros.getText().toString());;
                boolean garagem = editGaragem.getFreezesText();


                HashMap<String,Object> imovel = new HashMap<String, Object>();
                imovel.put("nomeProprietario", nomeDono);
                imovel.put("nomeTelefone", telefone);
                imovel.put("nomeTipo", tipo);
                imovel.put("nomeValor", valor);
                imovel.put("nomeTempo", tempo);
                imovel.put("quantidadeCarros", quantidadeQuarto);
                imovel.put("quantidadeBanheiros", getQuantidadeBanheiro);
                imovel.put("garagem",garagem);

                db.collection("imovel").add(imovel);

                finish();
            }
        });

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
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClickCamera(View view){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},0);
        }else{
            tirarFoto();
        }
    }
    public void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1000);
    }


    private void escolherImagem() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Selecione as Imagens"), 1001);

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1000 ){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagem);
        }else if(resultCode == RESULT_OK && requestCode == 1001 ){
            imageView.setImageURI(data.getData());
        }
    }

*/
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    try {

        // When an Image is picked
        if (requestCode == 1001 && resultCode == RESULT_OK
                && null != data) {
            // Get the Image from data

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            imagesEncodedList = new ArrayList<String>();
            if(data.getData()!=null){

                Uri mImageUri=data.getData();

                // Get the cursor
                Cursor cursor = getContentResolver().query(mImageUri,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imageEncoded  = cursor.getString(columnIndex);
                cursor.close();

            } else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {

                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        mArrayUri.add(uri);
                        // Get the cursor
                        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded  = cursor.getString(columnIndex);
                        imagesEncodedList.add(imageEncoded);
                        cursor.close();

                    }
                    Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                }
            }

        } else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }

    } catch (Exception e) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show();
    }finally {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adpater = new LineAdpater(mArrayUri);
        recyclerView.setAdapter(adpater);

    }

    super.onActivityResult(requestCode, resultCode, data);
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
            case 1000:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    tirarFoto();
                }else{
                    Toast.makeText(this, "Permissão Negada!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    public void inicializarComponentes(){
        editNomeP = findViewById(R.id.editNomeProprietario);
        radioGroup = findViewById(R.id.radioGroup);
        editGaragem = findViewById(R.id.editGaragem);
        editTelefone = findViewById(R.id.editTelefone);
        editPreco = findViewById(R.id.editValor);
        editTempo = findViewById(R.id.editTempo);
        editBanheiros = findViewById(R.id.editQtdBanheiro);
        editQuartos = findViewById(R.id.editQtdQuarto);
        //imageView = findViewById(R.id.iv);
        button = findViewById(R.id.buttonAdicionarFoto);
        buttonCadastrar = findViewById(R.id.buttonCadastrarImovel);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mArrayUri = new ArrayList<Uri>();

    }
    public void onClickAddImagens(View view){
        Intent intent = new Intent(getApplicationContext(), AddImagemImovel.class);
        startActivity(intent);
    }


}
