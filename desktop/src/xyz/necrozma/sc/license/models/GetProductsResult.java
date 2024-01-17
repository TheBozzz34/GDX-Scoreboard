package xyz.necrozma.sc.license.models;

import com.google.gson.annotations.SerializedName;
import xyz.necrozma.sc.license.internal.BasicResult;

import java.util.List;

public class GetProductsResult extends BasicResult {

    @SerializedName(value = "products", alternate = {"Products"})
    public List<Product> Products;

}
