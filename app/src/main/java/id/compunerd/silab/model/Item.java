package id.compunerd.silab.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("success")
    @Expose
    private List<ResultItem> success = null;

    public List<ResultItem> getSuccess() {
        return success;
    }

    public void setSuccess(List<ResultItem> success) {
        this.success = success;
    }

}