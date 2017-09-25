package edu.berkeley.nwb2semantic;


import edu.berkeley.nwb2semantic.read.HDF5Read;
import ncsa.hdf.object.Group;
import tools.JenaBeanExtensionTool;
import tools.Syntax;

import java.io.FileOutputStream;
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

        if(args.length > 0) {
            //System.loadLibrary("jhdf5");
            String file = args[0];


            HDF5Read read = new HDF5Read();
            try {
                edu.berkeley.nwb2semantic.data.Group root = read.read(file);

                List<Object> resList = new ArrayList<Object>();
                resList.add(root);

                JenaBeanExtensionTool jbe = new JenaBeanExtensionTool();


                jbe.loadOOM(resList, false);

			/* get the ontology document in RDF/XML */
             //   PrintStream out = new PrintStream(new FileOutputStream("/tmp/" + new java.io.File(file).getName() + ".xml.rdf"));
                PrintStream out = new PrintStream(System.out);
                jbe.writeOntologyDocument(out, Syntax.RDF_XML);
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("parameter: nwb file");
        }

    }
}
