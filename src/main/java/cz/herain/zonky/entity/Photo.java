package cz.herain.zonky.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Vít on 16. 10. 2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo implements Serializable {

    private static final long serialVersionUID = 1234984946498496L;

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
