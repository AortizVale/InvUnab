package co.edu.unab.invunab.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Materia {

    private String docId;
    private String nombre;
    private String urlImagen;

    //Constructor


    public Materia(String docId, String nombre, String urlImagen) {
        this.docId = docId;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
    }

    public Materia(){}

    //Getters y setters

    @Exclude
    public String getDocId() {return docId; }
    @Exclude
    public void setDocId(String docId) {this.docId = docId; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @PropertyName("url_imagen")
    public String getUrlImagen() {
        return urlImagen;
    }
    @PropertyName("url_imagen")
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
