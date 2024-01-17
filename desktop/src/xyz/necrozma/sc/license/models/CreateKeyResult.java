package xyz.necrozma.sc.license.models;

import com.google.gson.annotations.SerializedName;
import xyz.necrozma.sc.license.internal.BasicResult;

public class CreateKeyResult extends BasicResult {
    @SerializedName(value = "key", alternate = {"Key"})
    public String Key;
}
