package com.example.exemplofinal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofinal.PessoaDetalheActivity;
import com.example.exemplofinal.R;
import com.example.exemplofinal.models.Pessoa;

import java.util.ArrayList;

public class PessoaAdapter extends
                RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {
    private final ArrayList<Pessoa>pessoas;

    public PessoaAdapter(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                PessoaViewHolder(
                        LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pessoa_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        Pessoa p = pessoas.get(position);
        holder.textNome.setText(p.getNome());
        holder.buttonEditar.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),
                                            PessoaDetalheActivity.class);
            intent.putExtra("id",p.getId());
            holder.itemView.getContext().startActivity(intent);
        });
        holder.buttonExcluir.setOnClickListener(v -> {
            pessoas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, pessoas.size());
        });
    }

    @Override
    public int getItemCount() {
        return pessoas != null ? pessoas.size() : 0;
    }


    public class PessoaViewHolder extends RecyclerView.ViewHolder{
        TextView textNome;
        ImageButton buttonEditar, buttonExcluir;

        public PessoaViewHolder(View itemView){
            super(itemView);
            textNome = itemView.findViewById(R.id.textView);
            buttonEditar = itemView.findViewById(R.id.imageButtonEditar);
            buttonExcluir = itemView.findViewById(R.id.imageButtonExcluir);
        }
    }
}
