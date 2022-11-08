package co.edu.unab.invunab.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.invunab.model.Publicacion;
import co.edu.unab.invunab.R;

public class AdaptadorPublicaciones extends RecyclerView.Adapter<AdaptadorPublicaciones.ViewHolder> {

    ArrayList<Publicacion> listadoDatos;

    OnItemClickListener onItemClickListener;

    public AdaptadorPublicaciones(ArrayList<Publicacion> listadoDatos) {
        this.listadoDatos = listadoDatos;
        this.onItemClickListener=null;
    }

    public void setListadoDatos(ArrayList<Publicacion> listadoDatos) {
        this.listadoDatos = listadoDatos;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdaptadorPublicaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view_publicaciones,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPublicaciones.ViewHolder holder, int position) {
        holder.cargarDatos(listadoDatos.get(position));
    }

    @Override
    public int getItemCount() {

        return listadoDatos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTituloPublicacion, tvDescripcionPublicacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTituloPublicacion=itemView.findViewById(R.id.tv_titulo_publicacion);
            tvDescripcionPublicacion=itemView.findViewById(R.id.tv_descripcion_publicacion);
        }

        public void cargarDatos(Publicacion publicacion) {
            tvTituloPublicacion.setText(publicacion.getTitulo()+"");
            tvDescripcionPublicacion.setText(publicacion.getDescripcion()+"");

            if (onItemClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(publicacion,getAdapterPosition());
                    }
                });
            };
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Publicacion publicacion,int posicion);
    }
}
