package mark.dietzler.mdietzlerlab6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "param1";


    // TODO: Rename and change types of parameters
    private ArrayList<Manufacturer> mManufacturerList;



    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     -     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(ArrayList<Manufacturer> manufacturer) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST, manufacturer);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mManufacturerList = (ArrayList<Manufacturer>) getArguments().get(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final MainActivity mainActivity = (MainActivity)getActivity();
        View rootView = inflater.inflate(R.layout.fragment_list, container, false).getRootView();
        ExpandableListView expandableListView = rootView.findViewById(R.id.expandableListView);
        MyListAdapter myListAdapter = new MyListAdapter(this.getActivity(), mManufacturerList);
        expandableListView.setAdapter(myListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mainActivity.changePage(mManufacturerList.get(groupPosition).getModelName(childPosition));
                return false;
            }
        });
        return rootView;
    }

}
