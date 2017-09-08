package edu.berkeley.nwb2semantic;

import edu.berkeley.nwb2semantic.read.HDF5Read;
import ncsa.hdf.object.Group;
import tools.JenaBeanExtensionTool;
import tools.Syntax;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petr-jezek on 7.9.17*
 * <p>
 * jezekp@kiv.zcu.cz
 */
public class Main {

    public static void main(String[] args) {

        //System.loadLibrary("jhdf5");
        String file = "/home/petr-jezek/Data/nwb_datasets/nwbMatlab_DG/ANM184389_20130207.nwb";


        HDF5Read h5DatasetRead = new HDF5Read();
        try {
            edu.berkeley.nwb2semantic.data.Group root = h5DatasetRead.read(file);

            List<Object> resList = new ArrayList<Object>();
            resList.add(root);

            JenaBeanExtensionTool jbe = new JenaBeanExtensionTool();

			/* load the ontology header from a packageName */
            //jbe.loadStatements(new FileInputStream("ontologyHeader.rdf.xml"), Syntax.RDF_XML_ABBREV);
            /* load and transform the OOM */


            jbe.loadOOM(resList, false);

			/* get the ontology document in RDF/XML */

            PrintStream out = new PrintStream(System.out);
            jbe.writeOntologyDocument(out, Syntax.RDF_XML);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
