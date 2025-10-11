package SAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            Gestion handler = new Gestion();
            parser.parse(new File("documentopedidos.xml"), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
