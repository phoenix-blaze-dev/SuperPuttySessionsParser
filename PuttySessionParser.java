import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class PuttySessionParser {

   public static void main(String[] args) {

      try {
         File inputFile = new File("Sessions.XML");
         File outFile = new File("config");
         if (outFile.createNewFile()) {
           System.out.println("File created: " + outFile.getName());
         } else {
           System.out.println("File already exists.");
         }
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("SessionData");
         System.out.println("----------------------------");

         FileWriter file = new FileWriter("config");

         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            // System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               // Show in window the element
               System.out.println("Nombre: "
                  + eElement.getAttribute("SessionName"));
               // Write to File
               file.write("HOST " + eElement.getAttribute("SessionName") + "\n");
               System.out.println("Host: "
                  + eElement.getAttribute("Host"));
               file.write("    HostName " + eElement.getAttribute("Host") + "\n");
               System.out.println("Puerto: "
                  + eElement.getAttribute("Port"));
               file.write("    Port " + eElement.getAttribute("Port") + "\n");
               System.out.println("Nombre de usuario: "
                  + eElement.getAttribute("Username"));
               file.write("    User " + eElement.getAttribute("Username") + "\n");
               System.out.println("Argumentos: "
                  + eElement.getAttribute("ExtraArgs"));
            }
         }
         file.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public static void writeToFile(String text){
    try {
      FileWriter myWriter = new FileWriter("config");
      myWriter.write(text);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
   }
}
