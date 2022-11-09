package co.edu.unab.invunab.view.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import co.edu.unab.invunab.R;
import co.edu.unab.invunab.model.Materia;
import co.edu.unab.invunab.view.adapters.AdaptadorMaterias;

public class MateriasActivity extends AppCompatActivity {

    ArrayList<Materia> listadoMaterias;
    RecyclerView rvMaterias;
    private AdaptadorMaterias adaptador;
    private Button btnIrAgregarMaterias;
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
                Intent intent = new Intent(MateriasActivity.this,LoginActivity.class);
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
        setContentView(R.layout.activity_materias);

        btnIrAgregarMaterias = findViewById(R.id.btn_ir_agregar_materias);
        listadoMaterias = new ArrayList<>();
        cargarDatosBaseDeDatos();
        adaptador = new AdaptadorMaterias(listadoMaterias);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();


        adaptador.setOnItemClickListener(new AdaptadorMaterias.OnItemClickListener() {
            @Override
            public void onItemClick(Materia materia, int posicion) {
                //Toast.makeText(MateriasActivity.this, "click "+materia.getNombre(), Toast.LENGTH_SHORT).show();
                Intent siguiente = new Intent(MateriasActivity.this, PublicacionesActivity.class);
                siguiente.putExtra("idMateria", materia.getDocId());
                siguiente.putExtra("nombreMateria", materia.getNombre());
                startActivity(siguiente);
            }
        });

        rvMaterias = findViewById(R.id.rv_materias);
        rvMaterias.setLayoutManager(new LinearLayoutManager(this));
        rvMaterias.setAdapter(adaptador);
    }

    private void cargarDatosBaseDeDatos() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection(NOMBRE_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    listadoMaterias.clear();

                    for (DocumentSnapshot document : task.getResult()) {
                        Materia miMateria = document.toObject(Materia.class);
                        miMateria.setDocId(document.getId());
                        listadoMaterias.add(miMateria);
                    }

                    adaptador.setListadoDatos(listadoMaterias);

                } else {
                    Log.e("errorFS", task.getException().getMessage());
                }
            }
        });
    }
    public void clickIrFormularioMateria(View view){
        Intent intent = new Intent(MateriasActivity.this,AgregarMateriasActivity.class);
        irFormularioMateria.launch(intent);
    }
    ActivityResultLauncher<Intent> irFormularioMateria=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                cargarDatosBaseDeDatos();
                adaptador.setListadoDatos(listadoMaterias);
            }
        }
    });
}