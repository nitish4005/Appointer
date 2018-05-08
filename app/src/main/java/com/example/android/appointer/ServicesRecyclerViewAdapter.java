package com.example.android.appointer;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.appointer.Model.ServiceProvidersModel;
import com.example.android.appointer.Service.ServiceFactory;

/**
 * Created by Prasad on 22-Apr-18.
 */

public class ServicesRecyclerViewAdapter extends RecyclerView.Adapter<ServicesRecyclerViewAdapter.CustomViewHolder> {
    private Context mContext;
    private String[] serviceNames;
    private String[] route = {"plumbers", "electrician", "painter", "grocery_shops", "developers", "tutors"};
    private ServiceProvidersModel serviceproviderslist;

    public ServicesRecyclerViewAdapter(Context context, String[] serviceNames) {
        this.mContext = context;
        this.serviceNames = serviceNames;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_display_card, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int position) {
        customViewHolder.serviceName.setText(serviceNames[position]);
        customViewHolder.serviceProvidersRecyclerView.setNestedScrollingEnabled(false);
        customViewHolder.seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        final ServiceProvidersListAdapter adapter = new ServiceProvidersListAdapter(mContext, serviceproviderslist);
        new AsyncTask<Void,Void,ServiceProvidersModel>() {

            @Override
            protected ServiceProvidersModel doInBackground(Void... voids) {
                serviceproviderslist = ServiceFactory.getNetworkCalls_service().getServiceProviders(route[position]);
                return serviceproviderslist;
            }

            @Override
            protected void onPostExecute(ServiceProvidersModel serviceProvidersModel) {
                Log.i("nk","inside postexecue123");
                if(serviceProvidersModel!=null) {
                    adapter.setServiceproviderslist(serviceProvidersModel);
                     Log.i("nk","inside postexecue");
                }
            }
        }.execute();

        customViewHolder.serviceProvidersRecyclerView.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return serviceNames == null ? 0 : serviceNames.length;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView serviceProvidersRecyclerView;
        private TextView serviceName;
        private Button seeAll;

        public CustomViewHolder(View view) {
            super(view);
            serviceProvidersRecyclerView = (RecyclerView) view.findViewById(R.id.serviceProvidersRecyclerView);
            serviceProvidersRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            serviceName = (TextView) view.findViewById(R.id.servicename);
            seeAll = (Button) view.findViewById(R.id.seeAll);


        }
    }

   }
