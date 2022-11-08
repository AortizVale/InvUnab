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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.invunab.R;
import co.edu.unab.invunab.model.Materia;
import co.edu.unab.invunab.model.Publicacion;
import co.edu.unab.invunab.view.adapters.AdaptadorMaterias;
import co.edu.unab.invunab.view.adapters.AdaptadorPublicaciones;

public class PublicacionesActivity extends AppCompatActivity {

    ArrayList<Publicacion> listadoPublicaciones;
    RecyclerView rvPublicacion;
    TextView tvNombreMateriaPub;
    private AdaptadorPublicaciones adaptador;
    private static final String NOMBRE_COLLECTION = "publicaciones";
    private String idMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones);

        idMateria = getIntent().getStringExtra("idMateria");
        String nombreMateria = getIntent().getStringExtra("nombreMateria");
        tvNombreMateriaPub=findViewById(R.id.tv_nombre_materia_pub);
        tvNombreMateriaPub.setText(nombreMateria);

        listadoPublicaciones = new ArrayList<>();
        cargarDatosBaseDeDatos();
        adaptador = new AdaptadorPublicaciones(listadoPublicaciones);

        adaptador.setOnItemClickListener(new AdaptadorPublicaciones.OnItemClickListener() {
            @Override
            public void onItemClick(Publicacion publicacion, int posicion) {
                Toast.makeText(PublicacionesActivity.this, "click "+publicacion.getTitulo(), Toast.LENGTH_SHORT).show();
                Intent siguiente = new Intent(PublicacionesActivity.this, DetallePublicacionActivity.class);
                siguiente.putExtra("nombreMateria",nombreMateria);
                siguiente.putExtra("publicacionTitulo",publicacion.getTitulo());
                siguiente.putExtra("publicacionDescripcion",publicacion.getDescripcion());
                siguiente.putExtra("publicacionAutor",publicacion.getAutor());
                siguiente.putExtra("publicacionFecha",publicacion.getFecha());
                siguiente.putExtra("publicacionMateria",publicacion.getIdMateria());
                siguiente.putExtra("publicacionUrlArchivo",publicacion.getUrlArchivo());
                siguiente.putExtra("publicacionUrlImagenAutor",publicacion.getUrlImagenAutor());

                startActivity(siguiente);
            }
        });

        rvPublicacion=findViewById(R.id.rv_publicaciones);
        rvPublicacion.setLayoutManager(new LinearLayoutManager(this));
        rvPublicacion.setAdapter(adaptador);
    }
    private void cargarDatosBaseDeDatos() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection(NOMBRE_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    listadoPublicaciones.clear();

                    for (DocumentSnapshot document : task.getResult()) {
                        Publicacion miPublicacion = document.toObject(Publicacion.class);
                        if ((miPublicacion.getIdMateria()).equals(idMateria)){
                            listadoPublicaciones.add(miPublicacion);
                        }

                    }

                    adaptador.setListadoDatos(listadoPublicaciones);

                } else {
                    Log.e("errorFS", task.getException().getMessage());
                }
            }
        });
    }
    public void clickIrFormularioPublicacion(View view){
        String idMateria = getIntent().getStringExtra("idMateria");
        Intent intent = new Intent(PublicacionesActivity.this,AgregarPublicacionActivity.class);
        intent.putExtra("idMateria",idMateria);
        irFormularioPublicaciones.launch(intent);
    }
    ActivityResultLauncher<Intent> irFormularioPublicaciones=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                cargarDatosBaseDeDatos();
                adaptador.setListadoDatos(listadoPublicaciones);
            }
        }
    });
}