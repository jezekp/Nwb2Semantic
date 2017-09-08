package edu.berkeley.nwb2semantic.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by petr-jezek on 8.9.17*
 * <p>
 * jezekp@kiv.zcu.cz
 */
public class Group {

    private List<Group> subgroups = new LinkedList<Group>();
    private List<Attribute> attributes = new LinkedList<Attribute>();
    private List<Dataset> datasets = new LinkedList<Dataset>();
    private String name;

    public List<Group> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<Group> subgroups) {
        this.subgroups = subgroups;
    }


    public List<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

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
}
