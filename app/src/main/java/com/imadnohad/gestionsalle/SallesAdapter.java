package com.imadnohad.gestionsalle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.imadnohad.gestionsalle.models.Salle;
import com.imadnohad.gestionsalle.service.SalleService;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SallesAdapter extends RecyclerView.Adapter<SallesAdapter.ViewHolder> {

    private List<Salle> mSalles;
    Context context;

    // Pass in the contact array into the constructor
    public SallesAdapter(List<Salle> salles, Context _context) {
        mSalles = salles;
        context = _context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.salle_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Salle salle = mSalles.get(position);

        // Set item views based on your views and data model
        TextView textViewL = holder.libeleTextView;
        textViewL.setText(salle.getLibele());
        TextView textViewC = holder.codeTextView;
        textViewC.setText(salle.getCode());

        holder.menuOptions.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(context, holder.menuOptions);
            popup.inflate(R.menu.recycler_item_menu);

            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.rvmenuEdit:
                        Intent intent = new Intent(this.context, AddActivity.class);
                        intent.putExtra("salles", (Serializable)mSalles);
                        intent.putExtra("salleId", salle.getId());
                        context.startActivity(intent);
                        break;
                    case R.id.rvmenuDelete:
                        mSalles.remove(salle);
                        notifyItemRemoved(position);
                        break;
                }
                return false;
            });

            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return mSalles.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView libeleTextView;
        public TextView codeTextView;
        public TextView menuOptions;
        CardView card = itemView.findViewById(R.id.cv_Salles);

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            libeleTextView = (TextView) itemView.findViewById(R.id.salle_libele);
            codeTextView = (TextView) itemView.findViewById(R.id.salle_code);
            menuOptions = (TextView) itemView.findViewById(R.id.textViewOptions);
        }
    }
}
