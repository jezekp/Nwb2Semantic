package edu.berkeley.nwb2semantic;

import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.h5.H5File;

/**
 * Created by petr-jezek on 7.9.17*
 * <p>
 * jezekp@kiv.zcu.cz
 */
public class Main {

    public static void main(String[] args) {

        //System.loadLibrary("jhdf5");
        String file = "/home/petr-jezek/Data/nwb_datasets/nwbMatlab_DG/ANM184389_20130207.nwb";


        H5DatasetRead h5DatasetRead = new H5DatasetRead();
        try {
            h5DatasetRead.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
