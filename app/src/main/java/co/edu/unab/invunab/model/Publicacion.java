package co.edu.unab.invunab.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.util.Date;

public class Publicacion {
    public String autor;
    public String fecha;
    public String urlImagenAutor;
    public String titulo;
    public String descripcion;
    public String urlArchivo;
    public String idMateria;

    public Publicacion(String autor, String fecha, String urlImagenAutor, String titulo, String descripcion, String urlArchivo, String idMateria) {
        this.autor = autor;
        this.fecha = fecha;
        this.urlImagenAutor = urlImagenAutor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlArchivo = urlArchivo;
        this.idMateria = idMateria;
    }

    public Publicacion() {}

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrlImagenAutor() {
        return urlImagenAutor;
    }

    public void setUrlImagenAutor(String urlImagenAutor) {
        this.urlImagenAutor = urlImagenAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }
}

