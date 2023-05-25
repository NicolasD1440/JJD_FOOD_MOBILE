package com.example.jjd_food_mobile.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jjd_food_mobile.R;
import com.example.jjd_food_mobile.modelo.comida;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.NumberFormat;


public class comidaAdapter extends FirestoreRecyclerAdapter<comida, comidaAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public comidaAdapter(@NonNull FirestoreRecyclerOptions<comida> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull comida comida) {
        NumberFormat nf = NumberFormat.getInstance();
       holder.precio.setText("Precio: " + nf.format(Integer.parseInt(comida.getPrecio()))+ " COP");
       holder.impuesto.setText("Impuesto: "+comida.getImpuesto()+" COP");
       holder.total.setText("Total: "+nf.format(Integer.parseInt(comida.getTotal()))+" COP");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comida, parent, false);
        return  new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView precio, impuesto, total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            precio = itemView.findViewById(R.id.PrecioAda);
            impuesto = itemView.findViewById(R.id.impuestoAda);
            total = itemView.findViewById(R.id.totalAda);
        }
    }
}
