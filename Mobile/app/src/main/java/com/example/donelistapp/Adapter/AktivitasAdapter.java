package com.example.donelistapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donelistapp.Models.AktivitasDataItem;

import com.example.donelistapp.R;

import java.text.BreakIterator;
import java.util.List;

public class AktivitasAdapter extends RecyclerView.Adapter<AktivitasAdapter.HolderData>{

    private List<AktivitasDataItem> dataAktvitas;
    private Context mContext;

    public AktivitasAdapter(Context mContext, List<AktivitasDataItem> dataAktvitas) {
        this.mContext = mContext;
        this.dataAktvitas = dataAktvitas;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_aktivitas_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        AktivitasDataItem aData = dataAktvitas.get(position);

        holder.tvAktivitas.setText(String.valueOf(aData.getActivityName()));
    }

    @Override
    public int getItemCount() {
        return dataAktvitas.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvAktivitas;
        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvAktivitas = itemView.findViewById(R.id.tv_aktivitas);
        }
    }


}
