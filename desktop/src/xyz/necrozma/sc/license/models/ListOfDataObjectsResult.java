package xyz.necrozma.sc.license.models;

import com.google.gson.annotations.SerializedName;
import xyz.necrozma.sc.license.internal.BasicResult;

import java.util.List;

public class ListOfDataObjectsResult extends BasicResult {
    @SerializedName(value = "dataObjects", alternate = {"DataObjects"})
    public List<DataObject> DataObjects;
}
