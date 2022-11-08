package co.edu.unab.invunab.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.invunab.model.Materia;
import co.edu.unab.invunab.R;

public class AdaptadorMaterias extends RecyclerView.Adapter<AdaptadorMaterias.ViewHolder> {

    ArrayList<Materia> listadoDatos;

    OnItemClickListener onItemClickListener;

    public AdaptadorMaterias(ArrayList<Materia> listadoDatos) {
        this.listadoDatos = listadoDatos;
        this.onItemClickListener=null;
    }

    public void setListadoDatos(ArrayList<Materia> listadoDatos) {
        this.listadoDatos = listadoDatos;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdaptadorMaterias.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view_materias,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMaterias.ViewHolder holder, int position) {
        holder.cargarDatos(listadoDatos.get(position));
    }

    @Override
    public int getItemCount() {

        return listadoDatos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreMateria;
        ImageView ivUrlMateria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreMateria=itemView.findViewById(R.id.tv_nombre_materia);
            ivUrlMateria=itemView.findViewById(R.id.iv_icono_materia);
        }

        public void cargarDatos(Materia materia) {
            tvNombreMateria.setText(materia.getNombre()+"");

            Picasso.get()
                    .load(materia.getUrlImagen())
                    .resize(300,300)
                    .centerCrop()
                    .into(ivUrlMateria);
            if (onItemClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(materia,getAdapterPosition());
                    }
                });
            };
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Materia materia,int posicion);
    }
}
