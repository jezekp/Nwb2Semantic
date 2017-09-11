package edu.berkeley.nwb2semantic.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by petr-jezek on 8.9.17*
 * <p>
 * jezekp@kiv.zcu.cz
 */
public class Dataset {

    private List<Attribute> attributes = new LinkedList<Attribute>();
    private String name;
    private Object data;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
