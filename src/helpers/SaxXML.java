/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author frank
 */
public class SaxXML {

    private static SaxXML instance = null;
    private xmlNFO xmlnfo = new xmlNFO();
    private String currentValue;

    public static SaxXML getInstance() throws IOException {
        if (instance == null) {
            instance = new SaxXML();
        }
        return instance;
    }

    public void ReadNFO(LinkedList<Lookups> movieLookups) throws SAXException, ParserConfigurationException{
        for (Lookups movie:movieLookups){
            
            try {
                    String xmlFilepath = movie.folder + "\\" + movie.movie + ".nfo";
                    if(Moviename.getInstance().FileExist(movie.folder+"\\folder.jpg")){
                        xmlnfo.setIcon(movie.folder);                       
                    }
                    
                    xmlnfo.setProducer(movie.publisher);
                    
                    if (Moviename.getInstance().FileExist(xmlFilepath)){
                        movie.nfoExists = true;
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser saxParser = factory.newSAXParser();
                        DefaultHandler handler = new DefaultHandler(){
                            @Override
                            public void endElement(String uri, String localName, String qName) throws SAXException {
                                if(qName.equals("title")){
                                    xmlnfo.setTitle(currentValue);
                                }

                                if(qName.equals("sorttitle")){
                                    xmlnfo.setSorttitle(currentValue);
                                }

                                if(qName.equals("year")){
                                    xmlnfo.setYear(currentValue);
                                }

                                if(qName.equals("plot")){
                                    xmlnfo.setPlot(currentValue);
                                }

                                if(qName.equals("genre")){
                                    xmlnfo.setGenre(currentValue);
                                    //xmlnfo.setGenre("Adult");

                                }

                                if(qName.equals("language")){
                                    xmlnfo.setLanguage(currentValue);
                                }

                                /*if(qName.equals("videocodec")){
                                    xmlnfo.setVideocodec(currentValue);
                                }

                                if(qName.equals("audiocodec")){
                                    xmlnfo.setAudiocodec(currentValue);
                                }*/
                                
                                if(qName.equals("mpaa")){
                                   /* if (currentValue.equals("")) {
                                        xmlnfo.setMpaa("R");
                                    } else {*/
                                        xmlnfo.setMpaa(currentValue);
                                   // }
                                }
                                
                                if(qName.equals("runtime")){
                                    xmlnfo.setRuntime(currentValue);
                                }
                            }
                            
                            @Override
                            public void characters(char[] ch, int start, int length) throws SAXException {
                                currentValue = new String(ch, start, length);
                                if (currentValue.equals("")){
                                    currentValue = "";
                                }
                            }
                        };
                    
                        File test = new File(xmlFilepath);
                        InputStream inputStream = new FileInputStream(test);
                        Reader reader = new InputStreamReader(inputStream, "UTF-8");
                        InputSource is  = new InputSource(reader);
                        is.setEncoding("UTF-8");
                        saxParser.parse(test, handler);
                        movie.xmlNFO = this.xmlnfo;
                        this.xmlnfo = new xmlNFO();
                 } else {
                       // xmlnfo.setProducer(movie.publisher);
                        xmlnfo.setTitle(movie.movie);
                        xmlnfo.setSorttitle(movie.movie);
                        movie.xmlNFO = this.xmlnfo;
                        this.xmlnfo = new xmlNFO();
                    }
              } catch (IOException ex) {
                Logger.getLogger(SaxXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
