package co.edu.unab.invunab.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.invunab.R;
import co.edu.unab.invunab.model.Materia;

public class AgregarMateriasActivity extends AppCompatActivity {

    private EditText etAddNombreMat, etAddUrlMat;

    private static final String NOMBRE_COLLECTION = "materias";

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
                Intent intent = new Intent(AgregarMateriasActivity.this,LoginActivity.class);
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
        setContentView(R.layout.activity_agregar_materias);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        referenciarViews();
    }
    public void referenciarViews(){
        etAddNombreMat=findViewById(R.id.et_add_nombre_mat);
        etAddUrlMat=findViewById(R.id.et_add_url_mat);
    }
    public void clickGuardarMateria(View view){
        String nombreMateria=etAddNombreMat.getText().toString();
        String imagenMateria=etAddUrlMat.getText().toString();

        if("".equals(nombreMateria)){
            etAddNombreMat.setError(getString(R.string.validar_error));
            return;
        }
        if("".equals(imagenMateria)){
            etAddNombreMat.setError(getString(R.string.validar_error));
            return;
        }

        Materia nuevaMateria=new Materia(null,nombreMateria,imagenMateria);

        FirebaseFirestore firesore =FirebaseFirestore.getInstance();
        firesore.collection(NOMBRE_COLLECTION).add(nuevaMateria);

        Intent datos=new Intent();

        setResult(RESULT_OK,datos);

        finish();
    }
}