package mark.dietzler.mdietzlerlab6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Manufacturer implements Serializable {

    String manufacturer;
    ArrayList<Model> models;

    Manufacturer(){

    }

    Manufacturer(String manufacturer){
        this.manufacturer = manufacturer;
        this.models = new ArrayList<Model>();
    }

    Manufacturer(String manufacturer, ArrayList<Model> models){
        this.manufacturer = manufacturer;
        this.models = models;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ArrayList<Model> getModels() {
        return models;
    }
    public Model getModelName(int position){
        return models.get(position);
    }

    public void deleteModel(int position){
        if(position >= 0 && position<models.size()){
            models.remove(position);
        }
    }

    public void deleteModel(String name){
        models.remove(name);
    }

    public int getNumModel(){
        return models.size();
    }

    public void addModel(Model model){
        models.add(model);
    }

    public void addColModel(Collection models){
        models.addAll(models);
    }
}