package mark.dietzler.mdietzlerlab6;

import java.io.Serializable;

public class Model implements Serializable {

    String modelName, modelYears, modelEngines;
    int modelID;

    public Model (String name, String years, String engines, int id){
        modelName = name;
        modelYears = years;
        modelEngines = engines;
        modelID = id;
    }

    public int getModelID() {
        return modelID;
    }

    public String getModelEngines() {
        return modelEngines;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelYears() {
        return modelYears;
    }

    public void setModelEngines(String modelEngines) {
        this.modelEngines = modelEngines;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelYears(String modelYears) {
        this.modelYears = modelYears;
    }
}
