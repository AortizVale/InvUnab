package co.edu.unab.invunab.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import co.edu.unab.invunab.R;

public class DetallePublicacionActivity extends AppCompatActivity {

    private TextView tvNombrePublicacion, tvNombreAutor, tvFechaPublicacion,  tvTextoPublicacion;
    private ImageView ivUsuarioPublicacion;
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
                Intent intent = new Intent(DetallePublicacionActivity.this,LoginActivity.class);
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
        setContentView(R.layout.activity_detalle_publicacion);

        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        tvNombrePublicacion=findViewById(R.id.tv_nombre_publicacion);
        tvNombreAutor=findViewById(R.id.tv_nombre_autor);
        tvFechaPublicacion=findViewById(R.id.tv_fecha_publicacion);
        tvTextoPublicacion=findViewById(R.id.tv_texto_publicacion);
        ivUsuarioPublicacion=findViewById(R.id.iv_usuario_publicacion);

        String nombreMateria = getIntent().getStringExtra("nombreMateria");
        String publicacionTitulo = getIntent().getStringExtra("publicacionTitulo");
        String publicacionDescripcion = getIntent().getStringExtra("publicacionDescripcion");
        String publicacionAutor = getIntent().getStringExtra("publicacionAutor");
        String publicacionFecha = getIntent().getStringExtra("publicacionFecha");
        String publicacionMateria = getIntent().getStringExtra("publicacionMateria");

        String publicacionUrlImagenAutor = getIntent().getStringExtra("publicacionUrlImagenAutor");

        tvNombrePublicacion.setText(publicacionTitulo);
        tvNombreAutor.setText(publicacionAutor);
        tvFechaPublicacion.setText(publicacionFecha);
        tvTextoPublicacion.setText(publicacionDescripcion);
        Picasso.get()
                .load(publicacionUrlImagenAutor)
                .resize(300,300)
                .centerCrop()
                .into(ivUsuarioPublicacion);

    }

    public void verArchivo (View view){
        String publicacionUrlArchivo = getIntent().getStringExtra("publicacionUrlArchivo");
        Intent intent = new Intent(DetallePublicacionActivity.this,ShowActivity.class);
        intent.putExtra("urlArchivo",publicacionUrlArchivo);
        startActivity(intent);
    }
}