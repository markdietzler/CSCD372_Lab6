package mark.dietzler.mdietzlerlab6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment{

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity)getActivity();
        View rootV = inflater.inflate(R.layout.fragment_detail,container,false).getRootView();
        Model model = activity.getSelectedModel();
        if(model != null) {
            TextView textViewModel = rootV.findViewById(R.id.textViewModel);
            TextView textViewYears = rootV.findViewById(R.id.textViewYears);
            TextView textViewEngine = rootV.findViewById(R.id.textViewEngine);
            ImageView imageViewCar = rootV.findViewById(R.id.imageViewCar);

            textViewModel.setText(model.getModelName());
            textViewYears.setText(model.getModelYears());
            textViewEngine.setText(model.getModelEngines());
            imageViewCar.setImageResource(model.getModelID());
        }

        return rootV;
    }
}
