package co.edu.unab.invunab.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.invunab.R;
import co.edu.unab.invunab.model.Materia;
import co.edu.unab.invunab.model.Publicacion;
import java.util.Calendar;
import java.util.Date;

public class AgregarPublicacionActivity extends AppCompatActivity {

    private EditText etTituloPub, etDescripcionPub, etUrlArchivoPub;

    private static final String NOMBRE_COLLECTION = "publicaciones";

    private String idMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicacion);
        idMateria = getIntent().getStringExtra("idMateria");
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

        Publicacion nuevaPublicacion = new Publicacion("Andrés Ortiz",dateFacDB,"https://assets.stickpng.com/images/585e4beacb11b227491c3399.png",tituloPub,descripcionPub,urlArchivoPub,idMateria);

        FirebaseFirestore firesore =FirebaseFirestore.getInstance();
        firesore.collection(NOMBRE_COLLECTION).add(nuevaPublicacion);

        Intent datos=new Intent();

        setResult(RESULT_OK,datos);

        finish();
    }
}