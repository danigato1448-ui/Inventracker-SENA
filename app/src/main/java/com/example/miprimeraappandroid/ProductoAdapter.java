package com.example.miprimeraappandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.miprimeraappandroid.R;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> listaProductos;

    public ProductoAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Aquí inflamos el diseño que creamos para cada fila de producto
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_web, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        // 1. Nombre del producto (nombre_producto en SQL)
        holder.tvNombre.setText(producto.getNombre());

        // 2. Referencia
        holder.tvReferencia.setText("Ref: " + producto.getReferencia());

        // 3. Categoría (id_categoria en SQL)
        holder.tvCategoria.setText("Cat: " + producto.getCategoria());

        // 4. Stock Actual (stock_actual en SQL)
        // Usamos String.valueOf para evitar que la app se cierre por error de tipo de dato
        holder.tvStock.setText(String.valueOf(producto.getStock()) + " unidades");

        // 5. Precio de Venta (precio_venta en SQL)
        holder.tvPrecio.setText("$" + String.valueOf(producto.getPrecio()));

        // 6. Logo (Asegúrate de que sea 'logo' en minúscula)
        holder.ivLogo.setImageResource(R.drawable.logo);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvReferencia, tvCategoria, tvStock, tvPrecio;
        ImageView ivLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Vinculamos los IDs que pusiste en item_producto_web.xml
            tvNombre = itemView.findViewById(R.id.tvProdNombre);
            tvReferencia = itemView.findViewById(R.id.tvProdRef);
            tvCategoria = itemView.findViewById(R.id.tvProdCategoria);
            tvStock = itemView.findViewById(R.id.tvProdStock);
            tvPrecio = itemView.findViewById(R.id.tvProdPrecio);
            ivLogo = itemView.findViewById(R.id.ivLogoProducto);
        }
    }
}