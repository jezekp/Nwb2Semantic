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

package edu.berkeley.nwb2semantic;

import ncsa.hdf.object.*;
import ncsa.hdf.object.h5.H5File;
import ncsa.hdf.object.h5.H5Group;

import java.util.List;

/**
 * <p>
 * Title: HDF Object Package (Java) Example
 * </p>
 * <p>
 * Description: this example shows how to read/write HDF datasets using the
 * "HDF Object Package (Java)". The example creates an integer dataset, and read
 * and write data values:
 *
 * <pre>
 *     "/" (root)
 *             2D 32-bit integer 20x10
 * </pre>
 *
 * </p>
 *
 * @author Peter X. Cao
 * @version 2.4
 */
public class H5DatasetRead {

    public void read(String fname) throws Exception {
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

browseFile(root);


        // close file resource
        testFile.close();
    }

    public void browseFile(Group item) throws Exception {

        List dataset =  item.getMemberList();

        for(Object i : dataset) {

            if(i instanceof H5Group) {
                Group group = (Group) i;
                List metadata = group.getMetadata();
                for(Object m : metadata) {
                    System.out.println("metadata: " + m);
                }

                browseFile(group);

            }

            System.out.print( "/"  + item);
        }
        System.out.println();
    }


}