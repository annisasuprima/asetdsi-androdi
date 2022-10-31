package com.example.asetdsi.adapter;

    import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

    import androidx.recyclerview.widget.RecyclerView;

    import com.example.asetdsi.CobaActivity;
    import com.example.asetdsi.R;
    import com.example.asetdsi.model.StoreItems;

    import java.util.ArrayList;


    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

        private ArrayList<StoreItems> items;
        private int itemLayout;
        private final Context mContext;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        private View.OnLongClickListener onLongClickListener;

        public ItemAdapter(ArrayList<StoreItems> items, int itemLayout, Context context) {
            this.items = items;
            this.itemLayout = itemLayout;
            this.mContext = context;
            onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    StoreItems item = (StoreItems) buttonView.getTag();
                    if(isChecked){
                        item.setStatus("Checked");
                    }else {
                        item.setStatus("Unchecked");
                    }
                }
            };
            onLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    CobaActivity activity = (CobaActivity) mContext;
                    v.setOnCreateContextMenuListener(activity);
                    activity.openMenu(v);
                    return false;
                }
            };
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            StoreItems item = items.get(position);
            holder.tvSerialNumber.setText(item.getSerialNumber());
            holder.tvItems.setText(item.getItems());
            holder.tvBrand.setText(item.getBrand());
            holder.tvPrice.setText(item.getPrice());

            if(item.getStatus().equals("Checked")){
                holder.checkBox.setChecked(true);
            }else {
                holder.checkBox.setChecked(false);
            }
            holder.checkBox.setTag(item);
            holder.checkBox.setOnCheckedChangeListener(onCheckedChangeListener);

            holder.itemView.setTag(item);
            holder.itemView.setOnLongClickListener(onLongClickListener);
        }

        @Override
        public int getItemCount() {
            if(items == null) {
                return 0;
            }
            return items.size();
        }

        public void addItem(StoreItems item){
            items.add(item);
            notifyItemInserted(getItemCount() - 1);
        }

        public void updateItem(StoreItems item){
            int pos = items.indexOf(item);
            notifyItemChanged(pos);
        }

        public void deleteItem(StoreItems item){
            int pos = items.indexOf(item);
            items.remove(pos);
            notifyItemRemoved(pos);
        }

        public ArrayList<StoreItems> getItems(){
            if(items == null){
                return new ArrayList<>();
            }
            return items;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public CheckBox checkBox;
            public TextView tvSerialNumber;
            public TextView tvItems;
            public TextView tvBrand;
            public TextView tvPrice;

            public ViewHolder(View itemView) {
                super(itemView);
                checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
                tvSerialNumber = (TextView) itemView.findViewById(R.id.tvSerialNumber);
                tvItems = (TextView) itemView.findViewById(R.id.tvItems);
                tvBrand = (TextView) itemView.findViewById(R.id.tvBrand);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            }
        }
    }

