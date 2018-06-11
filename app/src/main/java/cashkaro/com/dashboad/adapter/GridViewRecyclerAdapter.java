package cashkaro.com.dashboad.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import cashkaro.com.dashboad.R;
import cashkaro.com.dashboad.customview.CircleView;
import cashkaro.com.dashboad.model.SchoolList;


public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.MyViewHolder> {

    public int selectedPosition = 0;
    private OnItemClickListener onItemClickListener;

    public GridViewRecyclerAdapter(Context context, List<SchoolList> list) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = (OnItemClickListener) context;
    }

    private Context context;
    private List<SchoolList> list;

    public void update(List<SchoolList> list) {
//        this.list = new ArrayList<>();
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final SchoolList tabMenu = list.get(position);

        holder.schoolname.setText(tabMenu.getName());

        holder.schoolview.setCircleColor(Color.parseColor(tabMenu.getColor()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView schoolname;
        private CircleView schoolview;// init the item view's
//        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
// get the reference of item view's
            schoolname = (TextView) itemView.findViewById(R.id.schoolname);
            schoolview = (CircleView) itemView.findViewById(R.id.schoolview);
//            linearLayout = (LinearLayout) itemView.findViewById(R.id.tabmenu);


        }
    }

    public interface OnItemClickListener {
        void position(SchoolList itemName);

        void scrollPosition(int pos);
    }
}