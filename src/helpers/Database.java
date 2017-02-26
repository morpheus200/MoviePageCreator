/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author frank
 */
public class Database {
    private static Database instance = null;
    
    private Connection con = null;    
    private Statement stmt = null;
    private LinkedList<Lookups> dbXML = new LinkedList<Lookups>();
    private LinkedList<Lookups> singleLookups = new LinkedList<Lookups>();

    
    public static Database GetInstance() throws IOException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    private boolean ConnectDB() throws SQLException{
        try {            
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:page.db");
        } catch ( ClassNotFoundException | SQLException e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
          return false;
        }
        System.out.println("Opened database successfully");
        this.stmt = con.createStatement();
        this.con.setAutoCommit(false);
        return true;  
    }
    
    private void CreateTable() throws SQLException{
        if (ConnectDB()){        
            String pornTable = "CREATE TABLE IF NOT EXISTS PORN " +
                       "(ID             INT     PRIMARY KEY     NOT NULL," +
                       " PIC            TEXT    NOT NULL," +
                       " PUBLISHER      TEXT    NOT NULL, " + 
                       " TITLE          TEXT    NOT NULL, " + 
                       " SORTTITLE      TEXT    NOT NULL, " + 
                       " GENRE          TEXT    NOT NULL, " + 
                       " MPAA           TEXT    NOT NULL, "
                     + " RUNTIME        TEXT    NOT NULL,"
                     + " YEAR           TEXT    NOT NULL,"
                     + " PLOT           TEXT    NOT NULL,"
                     + " LANGUAGE       TEXT    NOT NULL)"; 

            String movieTable = "CREATE TABLE IF NOT EXISTS MOVIE " +
                       "(ID             INT     PRIMARY KEY     NOT NULL," +
                       " PIC            TEXT    NOT NULL," +
                       " PUBLISHER      TEXT    NOT NULL, " + 
                       " TITLE          TEXT    NOT NULL, " + 
                       " SORTTITLE      TEXT    NOT NULL, " + 
                       " GENRE          TEXT    NOT NULL, " + 
                       " MPAA           TEXT    NOT NULL, "
                     + " RUNTIME        TEXT    NOT NULL,"
                     + " YEAR           TEXT    NOT NULL,"
                     + " PLOT           TEXT    NOT NULL,"
                     + " LANGUAGE       TEXT    NOT NULL)"; 


            this.stmt.executeUpdate(pornTable);
            this.con.commit();
            this.stmt.executeUpdate(movieTable);
            this.con.commit();
        }
        //DisconnectDB();
      }
    
    private void DisconnectDB() throws SQLException{
        this.stmt.close();
        this.con.close();
        this.stmt = null;
        this.con = null;
    }
    
    public void WriteFirstDB(String movieArt, LinkedList<Lookups> movieLookups) throws SQLException{
        if(ConnectDB()){
            CreateTable();
            int id = 0;
            if (movieArt.equals("porn")){
                for(Lookups lookup : movieLookups ) {
                    try {
                        String insert = "INSERT INTO PORN (ID,PIC,PUBLISHER,TITLE,"
                                + "SORTTITLE,GENRE,MPAA,RUNTIME,YEAR,PLOT,LANGUAGE) "
                                + "VALUES (" + id + ",\'" + 
                                lookup.xmlNFO.getIcon() + "\',\'" + 
                                lookup.xmlNFO.getProducer() + "\',\'" + 
                                lookup.xmlNFO.getTitle() + "\',\'" + 
                                lookup.xmlNFO.getSorttitle() + "\',\'" + 
                                lookup.xmlNFO.getGenre() + "\',\'" + 
                                lookup.xmlNFO.getMpaa() + "\',\'" + 
                                lookup.xmlNFO.getRuntime().replaceAll("\\s+","") + "\',\'" + 
                                lookup.xmlNFO.getYear().replaceAll("\\s+","") + "\',\'" + 
                                lookup.xmlNFO.getPlot().replaceAll("'", "") + "\',\'" + 
                                lookup.xmlNFO.getLanguage() + "');";
                        this.stmt.executeUpdate(insert);
                        con.commit();
                        id++;
                    }catch (SQLException e) {
                        int a = 3;
                    }
                }
            }
        }
        
        DisconnectDB();
    }
    
    
    public void UpdateDB(String movieArt, LinkedList<Lookups> lookups) throws SQLException{
        if(ConnectDB()){
            for(Lookups lookup : lookups) {
                String update = null;
                if (movieArt.equals("porn")){                    
                        update = "UPDATE PORN SET PIC = \'" + lookup.xmlNFO.getIcon() 
                            + "\', PUBLISHER = \'" + lookup.xmlNFO.getProducer()
                            + "\', TITLE = \'" + lookup.xmlNFO.getTitle().replaceAll("'", "")
                            + "\', SORTTITLE = \'" + lookup.xmlNFO.getSorttitle().replaceAll("'", "")
                            + "\', GENRE = \'" + lookup.xmlNFO.getGenre()
                            + "\', MPAA = \'" + lookup.xmlNFO.getMpaa()
                            + "\', YEAR = \'" + lookup.xmlNFO.getYear().replaceAll("\\s+","")
                            + "\', PLOT = \'" + lookup.xmlNFO.getPlot().replaceAll("'", "")
                            + "\', LANGUAGE = \'" + lookup.xmlNFO.getLanguage()
                            + "\', RUNTIME = \'" + lookup.xmlNFO.getRuntime().replaceAll("\\s+","")
                            + "\' WHERE ID = " + lookup.id + ";"; 
                }
                
                try {
                    stmt.executeUpdate(update);
                    con.commit();
                }catch (SQLException e) {
                    int a= 3;
                }
            }  
        }
        DisconnectDB();
    }
    
   /* public void writeDB(String movieArt) throws SQLException{
        if(ConnectDB()){
            
        }
    }*/
      
    public boolean ReadDBSingle(String movieArt, LinkedList<Lookups> lookups) throws SQLException {
        //this.singleLookups = new LinkedList<Lookups>[lookups.size()];    
        if (ConnectDB() && movieArt.equals("porn")) {
            try {
            this.stmt.executeQuery("SELECT * FROM " + movieArt);
            } catch (SQLException e) {
                DisconnectDB();
                return false;
            } 
            
            ResultSet rs = null;
            for (Lookups lookup : lookups) {
                try {
                    boolean changed = false;
                    rs = this.stmt.executeQuery("SELECT ID FROM PORN WHERE TITLE = \'" 
                            + lookup.movie + "\' AND PUBLISHER = \'" + lookup.publisher + "\'");
                   // this.singleLookups.remove(lookup);
                    //Wenn der Film vorhanden ist wird die ID aus der DB übernommen
                    while(rs.next()) {
                        lookup.id = rs.getInt("id");
                        changed = true;
                    }
                    //Wenn die ID nicht geändert wurde
                    if (!changed) {
                        int counter = 0;
                        rs = this.stmt.executeQuery("SELECT ID FROM PORN");
                        while (rs.next()) {
                            if (rs.getInt("id") >= counter) {
                                counter = rs.getInt("id");
                            }
                        }

                        lookup.id = counter + 1;
                        String insert = "INSERT INTO PORN (ID,PIC,PUBLISHER,TITLE,"
                            + "SORTTITLE,GENRE,MPAA,RUNTIME,YEAR,PLOT,LANGUAGE) "
                            + "VALUES (" + lookup.id + ",\'" + 
                            lookup.xmlNFO.getIcon() + "\',\'" + 
                            lookup.xmlNFO.getProducer() + "\',\'" + 
                            lookup.xmlNFO.getTitle().replaceAll("'", "") + "\',\'" + 
                            lookup.xmlNFO.getSorttitle().replaceAll("'", "") + "\',\'" + 
                            lookup.xmlNFO.getGenre() + "\',\'" + 
                            lookup.xmlNFO.getMpaa() + "\',\'" + 
                            lookup.xmlNFO.getRuntime().replaceAll("\\s+","") + "\',\'" + 
                            lookup.xmlNFO.getYear().replaceAll("\\s+","") + "\',\'" + 
                            lookup.xmlNFO.getPlot().replaceAll("'", "") + "\',\'" + 
                            lookup.xmlNFO.getLanguage() + "');";
                        stmt.executeUpdate(insert);
                        con.commit();
                    }
                    
                    this.singleLookups.add(lookup);                 
                }catch (SQLException e){
                    DisconnectDB();
                    return false;  
                }
            }
        }
        DisconnectDB();
        return true;
    }
    
    public LinkedList<Lookups> GetDBListSingle(){
        return this.singleLookups;
    }
    
    public boolean ReadDB(String movieArt) throws SQLException{
        if (ConnectDB()){        
            if(movieArt.endsWith("porn")) {
                try {
                    this.stmt.executeQuery("SELECT * FROM " + movieArt);
                    } catch (SQLException e) {
                         return false;
                    }                
                try (ResultSet rs = this.stmt.executeQuery( "SELECT * FROM PORN;" )) {
                    while ( rs.next() ) {
                        LinkedList<Lookups> dbLookups = new LinkedList<>();
                        Lookups lookup = new Lookups();
                        xmlNFO xmlNfo = new xmlNFO();
                        //xmlNfo.setID(rs.getInt("id"));
                        lookup.id = rs.getInt("id");
                        lookup.publisher = rs.getString("publisher");
                        xmlNfo.setProducer(rs.getString("publisher"));
                        lookup.resizePicName = this.cleanResizePicName(rs.getString("publisher") + "_" + rs.getString("title") + ".jpg");
                        xmlNfo.setGenre(rs.getString("genre"));
                        //xmlNfo.setGenre("Adult");
                        xmlNfo.setIconDB(rs.getString("pic"));
                        xmlNfo.setLanguage(rs.getString("language"));
                        xmlNfo.setMpaa(rs.getString("mpaa"));
                        //xmlNfo.setMpaa("R");
                        xmlNfo.setPlot(rs.getString("plot"));
                        xmlNfo.setRuntime(rs.getString("runtime"));
                        xmlNfo.setSorttitle(rs.getString("sorttitle"));
                        lookup.movie = rs.getString("title");
                        xmlNfo.setTitle(rs.getString("title"));
                        xmlNfo.setYear(rs.getString("year"));
                        lookup.xmlNFO = xmlNfo;
                        dbXML.add(lookup);
                    } 
                }            
            }
        }
        
        DisconnectDB();
        return dbXML.size() != 0;
    }
    
    public LinkedList<Lookups> GetDBList(){
        return this.dbXML;
    }
    
    private String cleanResizePicName(String resizePicName){
     String buffer;
     if (resizePicName.contains("#")){
        buffer = resizePicName.replaceAll("#", "");
     } else {
         buffer = resizePicName;
     }
     
     return buffer;
   }
}
