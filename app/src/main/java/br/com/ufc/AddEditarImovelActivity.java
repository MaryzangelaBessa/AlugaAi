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
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ufc.adapters.LineAdpater;
import br.com.ufc.ui.main.AddImagemImovel;

public class AddEditarImovelActivity extends AppCompatActivity {

    public ImageView imageView;
    public Button button,button3;
    public Button buttonCadastrar;
    public EditText editNomeP,editTelefone, editPreco, editTempo, editBanheiros, editQuartos, editEndereco;
    public RadioGroup radioGroup;
    public RecyclerView recyclerView;
    public Switch editGaragem;
    public String imageEncoded;
    public ArrayList<Uri> mArrayUri;
    public List<String> imagesEncodedList;
    public LineAdpater adpater;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference = database.getReference();
    private FirebaseAuth auth;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    public FirebaseAuth.AuthStateListener mauthlistener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_editar_imovel_layout);
        inicializarComponentes();
        mauthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d("AUTH", "signed_in:" + user.getUid());
                }else {
                    Log.d("AUTH", "signed_out:");
                }
            }
        };
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
                String endereco = editEndereco.getText().toString();
                String tipoB;
                String garagemB;

                if(tipo == 2131361966) tipoB = "Apartamento";
                else tipoB = "Casa";
                if(garagem) garagemB = "Sim";
                else garagemB = "Não";


                HashMap<String,Object> imovel = new HashMap<String, Object>();
                imovel.put("idDono", auth.getUid());
                imovel.put("nomeProprietario", nomeDono);
                imovel.put("nomeEndereco", endereco);
                imovel.put("nomeTelefone", telefone);
                imovel.put("nomeTipo", tipoB);
                imovel.put("nomeValor", valor);
                imovel.put("nomeTempo", tempo);
                imovel.put("quantidadeQuartos", quantidadeQuarto);
                imovel.put("quantidadeBanheiros", getQuantidadeBanheiro);
                imovel.put("garagem",garagemB);
                db.collection("imovel").add(imovel);

                HashMap<String,Object> imagens = new HashMap<String, Object>();
                ArrayList<String> imgs = new ArrayList<String>();
                for(int i= 0; i < mArrayUri.size();i++){

                    Uri imagem = mArrayUri.get(i);
                    StorageReference foto = storageReference.child("images/imoveis"  + auth.getUid() + imagem.getLastPathSegment());
                    UploadTask uploadTask = foto.putFile(imagem);
                    String caminho = "images/imoveis" + auth.getUid() + imagem.getLastPathSegment();
                    imgs.add(caminho);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("LOG_TAG","FALHOU");
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d("LOG_TAG","SUCESSO");
                        }
                    });
                }
                imagens.put("idDono", auth.getUid());
                imagens.put("nomeEndereco", endereco);
                imagens.put("caminhoImagem", imgs);
                db.collection("imagensImoveis").add(imagens);
                finish();
            }
        });

    }
    public void onClickPeril(View view){
        Intent intent = new Intent(this, Perfil_Usuario.class);
        startActivity(intent);
    }


    public void inicializarComponentes(){
        //Firebase

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = storage.getReference().child("Imoveis");

        //layout

        editNomeP = findViewById(R.id.editNomeProprietario);
        radioGroup = findViewById(R.id.radioGroup);
        editGaragem = findViewById(R.id.editGaragem);
        editEndereco = findViewById(R.id.editEndereco);
        editTelefone = findViewById(R.id.editTelefone);
        editPreco = findViewById(R.id.editValor);
        editTempo = findViewById(R.id.editTempo);
        editBanheiros = findViewById(R.id.editQtdBanheiro);
        editQuartos = findViewById(R.id.editQtdQuarto);
        button = findViewById(R.id.buttonAdicionarFoto);
        buttonCadastrar = findViewById(R.id.buttonCadastrarImovel);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mArrayUri = new ArrayList<Uri>();
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

    private void escolherImagem() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Selecione as Imagens"), 1001);

    }
/*


*/
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    try {

        // When an Image is picked
        if (requestCode == 1001 && resultCode == RESULT_OK  && null != data) {
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            imagesEncodedList = new ArrayList<String>();

            if(data.getData()!=null){
                Uri mImageUri=data.getData();
                Cursor cursor = getContentResolver().query(mImageUri, filePathColumn, null, null, null);
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
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mauthlistener);

    }
}
