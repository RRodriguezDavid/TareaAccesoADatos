package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Gestion extends DefaultHandler {

    private String actual = "";
    private String nombre;
    private String autor;
    private double precio;
    private double totalPedido = 0.0;

    @Override
    public void startDocument() {
        System.out.println("=========== FACTURA ===========");
    }

    @Override
    public void endDocument() {
        System.out.println("================================");
        System.out.printf(" TOTAL PEDIDO: %.2f €%n", totalPedido);
        System.out.println("=========== FACTURA ===========");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        actual = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        // Cuando termina un <libro>, mostramos y sumamos al total
        if (qName.equalsIgnoreCase("libro")) {
            double precioTotal = precio * 1.04; // precio + 4%
            System.out.printf("\nLibro: %s\nAutor: %s\nPrecio: %.2f €\nPrecio con 4%%: %.2f €\n",
                    nombre, autor, precio, precioTotal);
            totalPedido += precioTotal;

            // Reiniciar datos del libro
            nombre = "";
            autor = "";
            precio = 0.0;
        }
        actual = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String texto = new String(ch, start, length).trim();
        if (!texto.isEmpty()) {
            switch (actual) {
                case "nombre":
                    nombre = texto;
                    break;
                case "autor":
                    autor = texto;
                    break;
                case "precio":
                    try {
                        precio = Double.parseDouble(texto);
                    } catch (NumberFormatException e) {
                        precio = 0.0;
                    }
                    break;
            }
        }
    }
}
