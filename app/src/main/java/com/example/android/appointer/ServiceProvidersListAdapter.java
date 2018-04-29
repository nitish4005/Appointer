package com.example.android.appointer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.appointer.Model.ServiceProvidersModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Prasad on 22-Apr-18.
 */

public class ServiceProvidersListAdapter extends RecyclerView.Adapter<ServiceProvidersListAdapter.MoviesHolder> {
    private ServiceProvidersModel serviceproviderslist;
    private LayoutInflater inflater;
    private Context mContext;
    private String type;

    public void setServiceproviderslist(ServiceProvidersModel serviceproviderslist) {
        this.serviceproviderslist = serviceproviderslist;
        notifyDataSetChanged();
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedPosition);
    }

    public ServiceProvidersListAdapter(Context context, ServiceProvidersModel serviceproviderslist) {
        inflater = LayoutInflater.from(context);
        this.type=type;
        mContext = context;
        this.serviceproviderslist=serviceproviderslist;

    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_list_item, null);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {

        holder.providerRating.setText(serviceproviderslist.getData().get(position).getServices().get(0).getRatings()+"");
        holder.providersName.setText(serviceproviderslist.getData().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return serviceproviderslist == null ? 0 : serviceproviderslist.getData().size();
    }

    class MoviesHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView providersName;
        private CircleImageView providersDp;
        private TextView providerRating;


        public MoviesHolder(View itemView) {
            super(itemView);

            providerRating =(TextView)itemView.findViewById(R.id.rating);
            providersName =(TextView)itemView.findViewById(R.id.serviceprovidersname);
            providersDp=(CircleImageView) itemView.findViewById(R.id.serviceprovidersdp);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
