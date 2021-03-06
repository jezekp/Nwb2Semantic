/*****************************************************************************
 * Copyright by The HDF Group.                                               *
 * Copyright by the Board of Trustees of the University of Illinois.         *
 * All rights reserved.                                                      *
 *                                                                           *
 * This file is part of the HDF Java Products distribution.                  *
 * The full copyright notice, including terms governing use, modification,   *
 * and redistribution, is contained in the files COPYING and Copyright.html. *
 * COPYING can be found at the root of the source code distribution tree.    *
 * Or, see http://hdfgroup.org/products/hdf-java/doc/Copyright.html.         *
 * If you do not have access to either file, you may request a copy from     *
 * help@hdfgroup.org.                                                        *
 ****************************************************************************/

package edu.berkeley.nwb2semantic.read;

import ncsa.hdf.object.*;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Group;
import org.openjena.atlas.lib.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by petr-jezek on 7.9.17*
 * <p>
 * jezekp@kiv.zcu.cz
 */
public class HDF5Read {

    public edu.berkeley.nwb2semantic.data.Group read(String fname) throws Exception {
        // create the file and add groups and dataset into the file


        // retrieve an instance of H5File
        FileFormat fileFormat = FileFormat.getFileFormat(FileFormat.FILE_TYPE_HDF5);

        if (fileFormat == null) {
            throw new Exception("Cannot find HDF5 FileFormat.");
        }

        // open the file with read and write access
        FileFormat testFile = fileFormat.createInstance(fname, FileFormat.WRITE);

        if (testFile == null) {
            throw new Exception("Failed to open file: " + fname);
        }

        // open the file and retrieve the file structure
        testFile.open();
        Group root = (Group) ((javax.swing.tree.DefaultMutableTreeNode) testFile.getRootNode()).getUserObject();

        edu.berkeley.nwb2semantic.data.Group out = new edu.berkeley.nwb2semantic.data.Group();
        browseFile(root, out);


        // close file resource
        testFile.close();
        return out;
    }

    protected void browseFile(Group node, edu.berkeley.nwb2semantic.data.Group outGroup) throws Exception {

        List members = node.getMemberList();

        for (Object member : members) {

            if (member instanceof Group) {
                edu.berkeley.nwb2semantic.data.Group tmp = new edu.berkeley.nwb2semantic.data.Group();
                Group group = (Group) member;
                tmp.setName(group.getName());
                outGroup.getSubgroups().add(tmp);
                tmp.setAttributes(createMetadata(group.getMetadata()));


                browseFile(group, tmp);

            }
            //todo how to deal with binary data?
            if (member instanceof Dataset) {
                Dataset dataset = (Dataset) member;
                edu.berkeley.nwb2semantic.data.Dataset tmp = new edu.berkeley.nwb2semantic.data.Dataset();
                tmp.setName(dataset.getName());
                tmp.setAttributes(createMetadata(dataset.getMetadata()));
                //             dataset.setConvertByteToString(true);
                //tmp.setData(dataset.getData());
                int datasetId = dataset.open();
                long[] dimes = dataset.getDims();
                // System.out.println(dataset.getFullName() + ": " + Arrays.toString(dataset.getDims())  + " " + Arrays.toString(dataset.getDimNames()));
                // System.out.println(dataset.getDatatype().getDatatypeClass());
                if (dimes.length == 1 && dimes[0] > 0 && dimes[0] < 20) {

                    Object o = dataset.read();
                    tmp.setData(o);

                }


//dataset.setConvertByteToString(true);
//System.out.println(dataset.read());
                dataset.close(datasetId);
                //dataset.read();
                outGroup.getDatasets().add(tmp);
                // String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);

            }
        }
    }

    private List<edu.berkeley.nwb2semantic.data.Attribute> createMetadata(List<Attribute> attributes) {
        List<edu.berkeley.nwb2semantic.data.Attribute> res = new LinkedList<edu.berkeley.nwb2semantic.data.Attribute>();
        for (Attribute item : attributes) {
            edu.berkeley.nwb2semantic.data.Attribute tmp = new edu.berkeley.nwb2semantic.data.Attribute();
            tmp.setName(item.getName());
            tmp.setValue(item.toString(","));
            res.add(tmp);
        }
        return res;

    }


}