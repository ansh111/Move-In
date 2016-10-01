
package inhouse.movein.Model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUrls {

    @SerializedName("urls")
    @Expose
    private List<String> urls = new ArrayList<String>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<String> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
