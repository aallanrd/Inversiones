/*
 * Copyright (C) 2015 curso
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *

// Esta es la clase mas fake de todas 
// Haha no tengo idea de como manejar bien los XML 
// Espero que me ayuden :P


package util;

/**
 *
 * @author curso
 */
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Pretty-prints xml, supplied as a string.
 * <p/>
 * eg.
 * <code>
 * String formattedXml = new XmlFormatter().format("<tag><nested>hello</nested></tag>");
 * </code>
 */
public class XmlFormatter {

    public XmlFormatter() {
    }

    public String format(String unformattedXml) {
        try {
            final Document document = parseXmlFile(unformattedXml);

            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);

            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   
    /* 
    * Dependiendo del tipo de Archivo que se quiera formatear al archivo 
    * Se llama a esta funcion y dentro del String se añaden los atributos que se requieran :)
    */
    public static void main(String[] args) {
        
        String id = "00013AC"; 
        String unformattedXml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><QueryMessage\n" +
                        "        xmlns=\"http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message\"\n" +
                        "        xmlns:query=\"http://www.SDMX.org/resources/SDMXML/schemas/v2_0/query\">\n" +
                        "    <Clientes>\n" +
                        "        <clientes:Interesados>\n" +
                        "   \t\t\t\t\t         <clientes:ID>ECB\n\n\n\n</clientes:ID>\n" +
                        "        </clientes:Interesados>\n" +
                
                        "        <clientes:Activos>\n" +
                        "   \t\t\t\t\t         <clientes:ID>"+id+"\n\n\n\n</clientes:ID>\n" +
                        "        </clientes:Activos>\n" +
                        "    </Clientes>\n\n\n\n\n" +
                        "</QueryMessage>";

        System.out.println(new XmlFormatter().format(unformattedXml));
    }

}
