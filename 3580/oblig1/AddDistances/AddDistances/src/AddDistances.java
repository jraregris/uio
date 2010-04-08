
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDistances {

  public static void main(String[] args) {
    Model model = ModelFactory.createDefaultModel();
    if (args.length < 2) {
      Logger.getLogger(AddDistances.class.getName()).log(Level.SEVERE, "You need tp provide an input and an output file as arguments.");
      System.exit(1);
    }
    try {
      model.read(new FileInputStream(args[0]), null, "TTL");
      System.out.println("Model read!");
    } catch (FileNotFoundException ex) {
      Logger.getLogger(AddDistances.class.getName()).log(Level.SEVERE, null, ex);
    }

    String placesNS = "http://inf3580.ifi.uio.no/places#";
    String geoNS = "http://www.w3.org/2003/01/geo/wgs84_pos#";
    String rdfNS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    String xsdNS = "http://www.w3.org/2001/XMLSchema#";



    Property name = model.getProperty(placesNS + "name");
    Property position = model.getProperty(placesNS + "position");
    Property endpoint = model.getProperty(placesNS + "endpoint");
    Property distance = model.getProperty(placesNS + "distance");
    Property connection = model.getProperty(placesNS + "Connection");

    Property lat = model.getProperty(geoNS + "lat");
    Property lon = model.getProperty(geoNS + "long");

    Property type = model.getProperty(rdfNS + "type");

    RDFDatatype xsddouble = XSDDatatype.XSDdouble;

    for (Iterator<Resource> from = model.listResourcesWithProperty(position); from.hasNext();) {
      Resource f = from.next();
      for (Iterator<Resource> to = model.listResourcesWithProperty(position); to.hasNext();) {
        Resource t = to.next();

        Double dist = Distances.distanceDEG(
                f.getProperty(position).getProperty(lat).getDouble(),
                f.getProperty(position).getProperty(lon).getDouble(),
                t.getProperty(position).getProperty(lat).getDouble(),
                t.getProperty(position).getProperty(lon).getDouble());

        if (dist != 0) { // Not adding distance to itself

          Resource c = model.createResource(f.getLocalName() + " to " + t.getLocalName());
          c.addProperty(type, connection);
          c.addProperty(endpoint, f);
          c.addProperty(endpoint, t);
          c.addProperty(distance, dist.toString(), XSDDatatype.XSDdouble);

        }
      }
    }

    try {
      model.write(new FileOutputStream(args[1]), "TTL");
      System.out.println("Model written!");
    } catch (IOException ex) {
      Logger.getLogger(AddDistances.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public double placeToLat(Model m, Resource r) {
    return r.getProperty(m.getProperty("http://inf3580.ifi.uio.no/places#position")).getProperty(m.getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#lat")).getDouble();
  }
}
