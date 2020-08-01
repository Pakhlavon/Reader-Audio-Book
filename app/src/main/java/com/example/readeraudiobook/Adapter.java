package com.example.readeraudiobook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final ArrayList<Model> arraylist;
    List<Model> modelList;
    LayoutInflater layoutInflater;
    Context context;
    private RecyclerView recyclerview;
    private CopyDatabase copyDatabase;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button txtbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtbutton = itemView.findViewById(R.id.txtbutton);
        }
    }

    public Adapter(Context context, List<Model> modelList) {

        this.context = context;
        this.arraylist = new ArrayList<>();
        this.modelList = modelList;
        copyDatabase = new CopyDatabase(this.context);
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, int position) {
        Model model = modelList.get(position);

        holder.txtbutton.setText(model.name);
        final String desc = model.getDescr_uz();

        holder.txtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.txtbutton.startAnimation(AnimationUtils.loadAnimation(context,R.anim.animation));

                Intent intent =new Intent(context,SahifaActivity.class);
                intent.putExtra("desc",desc);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
