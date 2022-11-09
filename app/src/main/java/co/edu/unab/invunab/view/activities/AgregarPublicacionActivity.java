package co.edu.unab.invunab.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.invunab.R;
import co.edu.unab.invunab.model.Publicacion;

import java.util.Calendar;
import java.util.Date;

public class AgregarPublicacionActivity extends AppCompatActivity {

    private EditText etTituloPub, etDescripcionPub, etUrlArchivoPub;

    private static final String NOMBRE_COLLECTION = "publicaciones";

    private String idMateria;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_cerrar_sesion:

                mAuth.signOut();
                Intent intent = new Intent(AgregarPublicacionActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicacion);
        idMateria = getIntent().getStringExtra("idMateria");
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        referenciarViews();
    }
    public void referenciarViews(){
        etTituloPub=findViewById(R.id.et_titulo_publicacion);
        etDescripcionPub=findViewById(R.id.et_descripcion_publicacion);
        etUrlArchivoPub=findViewById(R.id.et_url_archivo_publicacion);
    }
    public void clickGuardarPublicacion(View view){
        String idMateria = getIntent().getStringExtra("idMateria");
        String tituloPub=etTituloPub.getText().toString();
        String descripcionPub=etDescripcionPub.getText().toString();
        String urlArchivoPub=etUrlArchivoPub.getText().toString();

        if("".equals(tituloPub)){
            etTituloPub.setError(getString(R.string.validar_error));
            return;
        }
        if("".equals(descripcionPub)){
            etDescripcionPub.setError(getString(R.string.validar_error));
            return;
        }
        if("".equals(urlArchivoPub)){
            etUrlArchivoPub.setError(getString(R.string.validar_error));
            return;
        }

        Date currentTime = Calendar.getInstance().getTime();
        String dateFacDB = DateFormat.format("yyyy.MM.dd", currentTime).toString();

        Publicacion nuevaPublicacion = new Publicacion("Andres Ortiz",dateFacDB,"https://assets.stickpng.com/images/585e4beacb11b227491c3399.png",tituloPub,descripcionPub,urlArchivoPub,idMateria);

        FirebaseFirestore firestore =FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLLECTION).add(nuevaPublicacion);

        Intent datos=new Intent();

        setResult(RESULT_OK,datos);

        finish();
    }
}