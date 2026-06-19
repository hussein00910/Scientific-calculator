package com.example.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.VH> {

    private final List<HistoryManager.Entry> items;

    HistoryAdapter(List<HistoryManager.Entry> items) { this.items = items; }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        HistoryManager.Entry e = items.get(position);
        holder.expr.setText(e.expression);
        holder.result.setText(e.result);
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView expr, result;
        VH(View v) {
            super(v);
            expr   = v.findViewById(R.id.tvHistoryExpression);
            result = v.findViewById(R.id.tvHistoryResult);
        }
    }
}
