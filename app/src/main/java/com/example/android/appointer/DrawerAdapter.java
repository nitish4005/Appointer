package com.example.android.appointer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.appointer.Common.DrawerRow;
import com.example.android.appointer.Service.ServiceFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by Prasad on 19-Apr-18.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {
    private LayoutInflater inflator;
    private List<DrawerRow> rowData = Collections.emptyList();
    private DrawerLayout drawerLayout;
    private Context context;

    public DrawerAdapter(DrawerLayout drawerLayout, Context context, List<DrawerRow> drawerData) {
        inflator = LayoutInflater.from(context);
        rowData = drawerData;
        this.drawerLayout = drawerLayout;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        DrawerRow current = rowData.get(position);
        viewHolder.title.setText(current.title);
        viewHolder.icon.setImageResource(current.iconId);
    }


    @Override
    public int getItemCount() {
        return rowData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            //  int categoryId = Integer.parseInt("" + v.getTag());


            //getAdapterPosition();
          /* final Intent intent;
            intent =  new Intent(context, FBLoginActivity.class);
            context.startActivity(intent);*/

            if (getAdapterPosition()==0)
            {

            }
            else if (getAdapterPosition() == 1) {

            } else if (getAdapterPosition() == 2) {


            } else if (getAdapterPosition() == 3) {

            }
            else if(getAdapterPosition() == 4)
            {

            }

            drawerLayout.closeDrawers();
            // EventBus.getDefault().post(new ContentRefreshEvent(""+categoryId));
        }
    }


}
