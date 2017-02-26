/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author frank
 */
public class XMLCreator {

    private static XMLCreator instance = null;

    public static XMLCreator getInstance() throws IOException {
        if (instance == null) {
            instance = new XMLCreator();
        }
        return instance;
    }

    public void CreateNFOAdult(String folderName, String movieName, xmlNFO xmlnfo){
        try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                DOMImplementation impl = docBuilder.getDOMImplementation();

                // movie elements
                //Document doc = docBuilder.newDocument();
                Document doc = impl.createDocument(null,null,null);
                Element rootElement = doc.createElement("movie");
                doc.appendChild(rootElement);

                // title elements
                Element title = doc.createElement("title");
                title.appendChild(doc.createTextNode(xmlnfo.getTitle()));
                rootElement.appendChild(title);
                
                // producer elements
                Element producer = doc.createElement("producer");
                producer.appendChild(doc.createTextNode(xmlnfo.getProducer()));
                rootElement.appendChild(producer);

                // sorttitle elements
                Element sorttitle = doc.createElement("sorttitle");
                sorttitle.appendChild(doc.createTextNode(xmlnfo.getSorttitle()));
                rootElement.appendChild(sorttitle);

                // rating elements
              /*  Element rating = doc.createElement("rating");
                rating.appendChild(doc.createTextNode(""));
                rootElement.appendChild(rating);*/

                // year elements
                Element year = doc.createElement("year");
                year.appendChild(doc.createTextNode(xmlnfo.getYear().replaceAll("\\s+","")));
                rootElement.appendChild(year);

                // plot elements
                Element plot = doc.createElement("plot");
                plot.appendChild(doc.createTextNode(xmlnfo.getPlot()));
                rootElement.appendChild(plot);


                // runtime elements
                Element runtime = doc.createElement("runtime");
                runtime.appendChild(doc.createTextNode(xmlnfo.getRuntime().replaceAll("\\s+","")));
                rootElement.appendChild(runtime);

                // mpaa elements
                Element mpaa = doc.createElement("mpaa");
                mpaa.appendChild(doc.createTextNode(xmlnfo.getMpaa()));
                rootElement.appendChild(mpaa);

                // language elements
                Element language = doc.createElement("language");
                language.appendChild(doc.createTextNode(xmlnfo.getLanguage()));
                rootElement.appendChild(language);

                // id elements
                /*Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(movieID));
                rootElement.appendChild(id);*/

                // genre elements
                Element genre = doc.createElement("genre");
                genre.appendChild(doc.createTextNode(xmlnfo.getGenre()));
                rootElement.appendChild(genre);
                
                //fileInfo
              /*  Element fileInfo = doc.createElement("fileinfo");
                rootElement.appendChild(fileInfo);
                
                Element streamdetails = doc.createElement("streamdetails");
                fileInfo.appendChild(streamdetails);
               
                Element video = doc.createElement("video");
                streamdetails.appendChild(video);
                Element videocodec = doc.createElement("codec");
                videocodec.appendChild(doc.createTextNode(xmlnfo.getVideocodec()));
                video.appendChild(videocodec);

                Element audio = doc.createElement("audio");
                streamdetails.appendChild(audio);
                Element audiocodec = doc.createElement("codec");
                audiocodec.appendChild(doc.createTextNode(xmlnfo.getAudiocodec()));
                audio.appendChild(audiocodec);*/

                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                String nfoFile = folderName + File.separator + movieName + ".nfo";

                StringWriter sw = new StringWriter();
                StreamResult result = new StreamResult(sw);
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
    
                BufferedWriter out;

                out = new BufferedWriter(new FileWriter(nfoFile));
                out.write(sw.toString());
                out.close();


          } catch (IOException ex) {
            Logger.getLogger(XMLCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException | TransformerException pce) {
          }
    }
}
