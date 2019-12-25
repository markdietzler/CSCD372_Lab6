package mark.dietzler.mdietzlerlab6;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    //ExpandableListView expandableListView;
    SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
    //MyListAdapter myListAdapter = new MyListAdapter(this,manufacturers);
    Model currentModel;
    ArrayList<Model> modelArrayList = new ArrayList<Model>();
    ViewPager viewPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager) ;

        if(savedInstanceState != null) {
            currentModel = (Model) savedInstanceState.getSerializable("currentModel");
        }

        boolean booly = parseFile("muscle_cars.txt");
        if(!booly) {
            Toast.makeText(this, "Parse file FAIED", Toast.LENGTH_SHORT).show();
        } else {
            viewPager.setAdapter(sectionPagerAdapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("currentModel", currentModel);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        state.putSerializable("currentModel", currentModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if(id == R.id.action_about) {
            Toast.makeText(this, "Lab 6, Winter 2019, Mark Dietzler", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean parseFile(String fileName) {
        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream;
        String[] stringArray;
        Manufacturer manufacturer = null;
        try {
            inputStream = getAssets().open(fileName);
        }catch (Exception E){
            return false;
        }
        Scanner scanner = new Scanner(inputStream);
        try {
            while(scanner.hasNextLine()) {
                String manufactureLine = scanner.nextLine();
                do {
                    String modelLine = scanner.nextLine();
                    if(!modelLine.equalsIgnoreCase("END")) {
                        stringArray = modelLine.split(",");
                        int picid = getResources().getIdentifier(stringArray[0].toLowerCase().replaceAll(" ", ""), "drawable", getPackageName());
                        Model model = new Model(stringArray[0], stringArray[1], stringArray[2], picid);

                        modelArrayList.add(model);
                    }
                    else break;
                } while(scanner.hasNextLine());
                manufacturer = new Manufacturer(manufactureLine, new ArrayList<Model>());
                for (int i = 0; i < modelArrayList.size();i++) {
                    manufacturer.addModel(modelArrayList.get(i));
                }
                manufacturers.add(manufacturer);
                modelArrayList.clear();
            }
        } catch (Exception E) {
            Toast.makeText(this, "PROBLEM", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter{

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if(i == 0) {
                return ListFragment.newInstance(manufacturers);
            }
            else return new DetailFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

    public void changePage(Model model){
        currentModel = model;
        sectionPagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem() == 1){
            viewPager.setCurrentItem(0);
        }
        else super.onBackPressed();
    }

    public Model getSelectedModel(){
        return currentModel;
    }
}

