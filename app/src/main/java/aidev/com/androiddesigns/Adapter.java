package aidev.com.androiddesigns;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Initialiser> listitem;
    private Context context;
    HashMap map;


    public Adapter(Context context, List<Initialiser> listitem) {
        this.listitem = listitem;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView data1;
        private ImageView img1;
        private ImageView back;


        public ViewHolder(View itemView) {
            super(itemView);
            data1 = (TextView) itemView.findViewById(R.id.data1);
            img1 = (ImageView) itemView.findViewById(R.id.image1);
            back = (ImageView) itemView.findViewById(R.id.back);
            map = new HashMap();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        Initialiser homeInitialiser = listitem.get(position);
        final String data = ""+homeInitialiser.getData1();
        final String img = ""+homeInitialiser.getImage();

        Glide.with(context).load(img).into(holder.img1);

        holder.data1.setText(data);

        holder.data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBack(holder,data);
            }
        });

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBack(holder,data);
            }
        });

    }

    private void getBack(ViewHolder holder, String data) {

        if(map.containsKey(data)){
            map.remove(data);
            holder.back.setVisibility(View.INVISIBLE);
        }
        else{
            map.put(data,0);
            holder.back.setVisibility(View.VISIBLE);
        }

    }

    public HashMap getNamesMap(){
        return map;
    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }
}