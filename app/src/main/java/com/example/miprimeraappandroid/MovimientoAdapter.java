package com.example.miprimeraappandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MovimientoAdapter extends RecyclerView.Adapter<MovimientoAdapter.ViewHolder> {

    private List<Movimiento> listaMovimientos;

    public MovimientoAdapter(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movimiento mov = listaMovimientos.get(position);
        holder.txtFecha.setText(mov.getFecha());
        holder.txtProducto.setText(mov.getProducto());
        holder.txtTipo.setText(mov.getTipo());
        holder.txtCantidad.setText(String.valueOf(mov.getCantidad()));

        // Un toque de color: si es Salida, ponemos el texto en rojo
        if(mov.getTipo().equalsIgnoreCase("Salida")) {
            holder.txtCantidad.setTextColor(android.graphics.Color.RED);
        } else {
            holder.txtCantidad.setTextColor(android.graphics.Color.parseColor("#28A745"));
        }
    }

    @Override
    public int getItemCount() {
        return listaMovimientos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFecha, txtProducto, txtTipo, txtCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtProducto = itemView.findViewById(R.id.txtProducto);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
        }
    }
}