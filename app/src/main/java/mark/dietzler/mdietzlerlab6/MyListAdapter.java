package mark.dietzler.mdietzlerlab6;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class MyListAdapter extends BaseExpandableListAdapter implements View.OnClickListener{
    Activity myActivity;
    ArrayList<Manufacturer> myArrayList;
    ImageView imageView;

    public MyListAdapter(){
        this.myActivity = null;
        this.myArrayList = null;
    }

    public MyListAdapter(Activity act, ArrayList<Manufacturer> manufacturers){
        this.myActivity = act;
        this.myArrayList = manufacturers;
    }

    @Override
    public int getGroupCount() {
        return myArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return myArrayList.get(groupPosition).getNumModel();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return myArrayList.get(groupPosition);
    }

    @Override
    public Model getChild(int groupPosition, int childPosition) {
        return myArrayList.get(groupPosition).getModelName(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(myActivity);
            convertView = layoutInflater.inflate(R.layout.group_view, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.textViewGroup);
        textView.setText(myArrayList.get(groupPosition).getManufacturer());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(myActivity);
            convertView = layoutInflater.inflate(R.layout.child_view, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.textViewChildView);
        textView.setText((String)myArrayList.get(groupPosition).getModelName(childPosition).getModelName());
        imageView = (ImageView)convertView.findViewById(R.id.imageView);

        imageView.setTag(R.id.group_num, groupPosition);
        imageView.setTag(R.id.posn_num, childPosition);
        imageView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        final int gposition = (int) v.getTag(R.id.group_num);
        final int cposition = (int) v.getTag(R.id.posn_num);
        Snackbar.make(v, "Are you sure?", Snackbar.LENGTH_LONG).setAction("DELETE", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myArrayList.get(gposition).deleteModel(cposition);
                notifyDataSetChanged();
            }
        }).show();


    }




}
