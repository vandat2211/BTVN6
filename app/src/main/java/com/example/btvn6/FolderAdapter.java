package com.example.btvn6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {
    private List<Folder> folderList;
    private Context context;

    public FolderAdapter(Context context) {
        this.context = context;
    }
    public void setFolders(List<Folder> list){
            this.folderList=list;
            notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        Folder folder = folderList.get(position);
        if (folder == null) {
            return;
        }

        holder.tvtitle.setText(folder.getTitle());
        holder.tvcontent.setText(folder.getContent());

    }

    @Override
    public int getItemCount() {
        if (folderList != null) {
            return folderList.size();
        } else {
            return 0;
        }
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvtitle;
        private TextView tvcontent;
            public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
        tvtitle=itemView.findViewById(R.id.tvtieude);
        tvcontent=itemView.findViewById(R.id.tvnoidung);

                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(folderList.get(getLayoutPosition()), getLayoutPosition());
        }
    }
    public interface ClickListener {
        public void onClick(Folder folder, int position);
    }

    public ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
