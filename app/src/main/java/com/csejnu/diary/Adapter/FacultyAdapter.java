package com.csejnu.diary.Adapter;import android.content.Context;import android.content.Intent;import android.net.Uri;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.Filter;import android.widget.Filterable;import android.widget.ImageView;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.cardview.widget.CardView;import androidx.recyclerview.widget.RecyclerView;import com.csejnu.diary.Activity.DetailsActivity;import com.csejnu.diary.Database.Database;import com.csejnu.diary.Model.Faculty;import com.csejnu.diary.R;import java.util.ArrayList;import java.util.List;public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.UsersAdapterVh> implements Filterable {    int x;    List<Faculty> faculties;    List<Faculty> getUserModelListFiltered;    private Context context;    public FacultyAdapter(List<Faculty> userModelList, Context context) {        this.faculties = userModelList;        this.getUserModelListFiltered = userModelList;        this.context = context;    }    @NonNull    @Override    public FacultyAdapter.UsersAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        context = parent.getContext();        return new UsersAdapterVh(LayoutInflater.from(parent.getContext())                .inflate(R.layout.item_teacher, parent, false));    }    @Override    public void onBindViewHolder(@NonNull FacultyAdapter.UsersAdapterVh holder, int position) {        final Faculty teacher = faculties.get(position);        holder.nameTV.setText(teacher.getName());        holder.asTV.setText(teacher.getTitle());        holder.deptTv.setText(teacher.getDept());        if (teacher.getFav().equals("1")) {            x = 1;            holder.icon.setBackgroundResource(R.drawable.ic_favorite_24px__1_);        } else {            holder.icon.setBackgroundResource(R.drawable.ic_favorite_border_24px);            x = 0;        }        holder.call.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent callIntent = new Intent(Intent.ACTION_CALL);                callIntent.setData(Uri.parse("tel:" + teacher.getNumber_home()));                context.startActivity(callIntent);            }        });        holder.mail.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                String uriText =                        "mailto:" + teacher.getEmail() +                                "?subject=" + Uri.encode(" ") +                                "&body=" + Uri.encode("Sent from JnU Diary App");                Uri uri = Uri.parse(uriText);                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);                sendIntent.setData(uri);                context.startActivity(Intent.createChooser(sendIntent, "Send email"));            }        });        holder.itemView.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                context.startActivity(new Intent(context, DetailsActivity.class).putExtra("j", teacher.getUid()));            }        });        holder.favv.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                if (x == 0) {                    x = 1;                    teacher.setFav("1");                    new Database(context).update(teacher.getFav(), teacher.getUid());                    holder.icon.setBackgroundResource(R.drawable.ic_favorite_24px__1_);                } else {                    x = 0;                    teacher.setFav("0");                    new Database(context).update(teacher.getFav(), teacher.getUid());                    holder.icon.setBackgroundResource(R.drawable.ic_favorite_border_24px);                }            }        });    }    @Override    public int getItemCount() {        return faculties.size();    }    @Override    public Filter getFilter() {        Database db = new Database(context);        Filter filter = new Filter() {            @Override            protected FilterResults performFiltering(CharSequence charSequence) {                FilterResults filterResults = new FilterResults();                assert charSequence != null;                if (charSequence.length() == 0) {                    filterResults.count = getUserModelListFiltered.size();                    filterResults.values = getUserModelListFiltered;                } else {                    String searchChr = charSequence.toString().toLowerCase();                    List<Faculty> resultData = new ArrayList<>();                    for (Faculty userModel : db.getAllFacultyMembers()) {                        if (userModel.getName().toLowerCase().contains(searchChr) || userModel.getTitle().toLowerCase().contains(searchChr)) {                            resultData.add(userModel);                        }                    }                    filterResults.count = resultData.size();                    filterResults.values = resultData;                }                return filterResults;            }            @Override            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {                faculties = (List<Faculty>) filterResults.values;                notifyDataSetChanged();            }        };        return filter;    }    public static class UsersAdapterVh extends RecyclerView.ViewHolder {        TextView nameTV;        TextView asTV;        TextView deptTv;        CardView call;        CardView mail;        ImageView icon;        CardView favv;        public UsersAdapterVh(@NonNull View itemView) {            super(itemView);            nameTV = itemView.findViewById(R.id.namet);            asTV = itemView.findViewById(R.id.ast);            deptTv = itemView.findViewById(R.id.mailt);            call = itemView.findViewById(R.id.call);            mail = itemView.findViewById(R.id.mail);            favv = itemView.findViewById(R.id.favv);            icon = itemView.findViewById(R.id.regt);        }    }}