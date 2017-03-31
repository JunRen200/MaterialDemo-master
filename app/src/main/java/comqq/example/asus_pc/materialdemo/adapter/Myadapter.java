package comqq.example.asus_pc.materialdemo.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comqq.example.asus_pc.materialdemo.R;

/**
 * Created by asus-pc on 2017/2/28.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private ArrayList<String> list;
    private ArrayList<Integer> heightlist;
    private OnItemClicklistener monItemclicklistener;

    public Myadapter(ArrayList<String> list, ArrayList<Integer> heightlist) {
        this.heightlist = heightlist;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public interface OnItemClicklistener {
        void onItemclick(String name, int position);

        void onItemLongclick(int position);
    }

    public void setItemonclicklistener(OnItemClicklistener OnItemClicklistener) {
        this.monItemclicklistener = OnItemClicklistener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txt.setMinHeight(100 + (int) (Math.random() * 600));
        holder.txt.setText("" + list.get(position));
        if (monItemclicklistener != null) {
            holder.txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monItemclicklistener.onItemclick(list.get(position), position);
                }
            });
            holder.txt.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeData(position);
                    return false;
                }
            });

        }
    }

    public void addData(int position) {
        list.add(position, "哈哈");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }
    }
}
