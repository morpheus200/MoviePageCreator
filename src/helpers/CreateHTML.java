/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import javax.swing.JFileChooser;

/**
 *
 * @author frank
 */
public class CreateHTML {
    
    private static CreateHTML instance = null;
    private LinkedList<Lookups> lookupList;
    private javax.swing.JFileChooser jFileChooser2 = new JFileChooser();
    private String htmlFolder;
    private String moviePicFolder;

    public static CreateHTML getInstance() throws IOException {
        if (instance == null) {
            instance = new CreateHTML();
        }
        return instance;
    }
    
    
    public void create(LinkedList<Lookups> lookupList) throws IOException{
        jFileChooser2.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        this.lookupList = lookupList;
        jFileChooser2.setVisible(true);
        String openfolder = null;
        if (jFileChooser2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        openfolder = jFileChooser2.getSelectedFile().getAbsolutePath();
        }
        this.htmlFolder = openfolder+ "\\movieshtml";
        this.moviePicFolder = this.htmlFolder+"\\images";
        if(!Moviename.getInstance().FolderExist(htmlFolder)){
            FolderHandling.getInstance().createFolder("movieshtml", openfolder);           
        }
        if(!Moviename.getInstance().FolderExist(this.moviePicFolder)){
            FolderHandling.getInstance().createFolder("images", htmlFolder);            
        }
        
        String doctype = "<!DOCTYPE html\n"
                + "PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n"
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en-US\" xml:lang=\"en-US\">";
        
        String header = "<head>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html;\n"
                + "charset=iso-8859-1\">\n"
                + "<script src=\"moviecat.js\" type=\"text/javascript\"></script>\n"
                + "<link type=\"text/css\" rel=\"stylesheet\" href=\"white.css\" title=\"white\">\n"
                + "<style type=text/css><!--\n"
                + "span.HOVER_ULN:hover {text-decoration: underline}\n"
                + "a.HOVER_BOLD:hover {font-weight: bold}\n"
                + "input[type=text] {font-size: small; height: 1em;}\n"
                + "input[type=checkbox] {margin: 0px; width:13px; height:13px;}\n"
                + "input[type=radio] {margin: 0px; width:13px; height:13px;}\n"
                + "span.MDIRTIME {display: none}\n"
                + "--></style>\n"
                + "</head>";
        
        String sortFilter = "<body bgcolor=\"lightgrey\">\n" +
            "<div class=\"sort-options\">Sort by:\n" +
            "<a id=\"SORT_TITLE\" href=javascript:sort_title()>Title</a> \n" +
            "<a id=\"SORT_PRODUCER\" href=javascript:sort_producer()>Producer </a> \n" +
            "<a id=\"SORT_YEAR\" href=javascript:sort_year()>Year</a> \n" +
            "</div>\n" +
            "\n" +
            "<br class=\"sort-break\">\n" +
            "<span id=FILTER_HEAD>Filter: <small>\n" +
            "<i id=STATUS>5 Movies</i> \n" +
            "&nbsp; <a href=javascript:filter_reset()>reset</a>\n" +
            "&nbsp; <a id=SHOW_FILTER1 href=javascript:show_filter(1)>show mpaa</a>\n" +
            "&nbsp; <a id=SHOW_FILTER2 href=javascript:show_filter(2)>show genre</a>\n" +
            "&nbsp; <a id=SHOW_FILTER3 href=javascript:show_filter(3)>more options</a>\n" +
            "</small></span>\n" +
            "<form id=FORM_FILTER style='display:inline' onsubmit=do_filter();return(false)>\n" +
            "<table id=FILTER_TABLE cellspacing=3 cellpadding=0 bgcolor=silver>\n" +
            "<tr valign=top>\n" +
            "\n" +
            "\n" +
            "<td id=HIDE_FILTER1>\n" +
            "<table id=TAG_TABLE cellspacing=0 cellpadding=0>\n" +
            "<tr valign=top>\n" +
            "<td>MPAA:&nbsp;<br><small>\n" +
            "<a href=javascript:tag_all()>all</a><br>\n" +
            "<!--<a href=javascript:hide_filter(1)>hide</a><br>-->\n" +
            "</small></td>\n" +
            "<td><small>\n" +
            "<table cellspacing=0 cellpadding=0>\n" +
            "<tr>\n" +
            "<td><input type=radio id=TAG_R_ALL name=TAG_R value=all checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_R','ALL')>all</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_R_NOT name=TAG_R value=not onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_R','NOT')>not</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_R_SET name=TAG_R value=set onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_R','SET')>R (3)</span>&nbsp;\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><input type=radio id=TAG_G_ALL name=TAG_G value=all checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_G','ALL')>all</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_G_NOT name=TAG_G value=not onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_G','NOT')>not</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_G_SET name=TAG_HIDEF value=set onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_G','SET')>G (2)</span>&nbsp;\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><input type=radio id=TAG_PG_ALL name=TAG_PG value=all checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_PG','ALL')>all</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_PG_NOT name=TAG_PG value=not onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_PG','NOT')>not</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_PG_SET name=TAG_PG value=set onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_PG','SET')>PG (1)</span>&nbsp;\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><input type=radio id=TAG_PG13_ALL name=TAG_PG13 value=all checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_PG13','ALL')>all</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_PG13_NOT name=TAG_PG13 value=not onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_PG13','NOT')>not</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_PG13_SET name=TAG_PG13 value=set onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_PG13','SET')>PG13 (1)</span>&nbsp;\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><input type=radio id=TAG_NC17_ALL name=TAG_NC17 value=all checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_NC17','ALL')>all</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_NC17_NOT name=TAG_NC17 value=not onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_NC17','NOT')>not</span>&nbsp;\n" +
            "<td><input type=radio id=TAG_NC17_SET name=TAG_NC17 value=set onclick=do_filter()><span class=HOVER_ULN onclick=tag_set('TAG_NC17','SET')>NC17 (1)</span>&nbsp;\n" +
            "</tr>\n" +
            "</table>\n" +
            "</small></td>\n" +
            "<td style='width:1em'></td>\n" +
            "</tr></table></td>\n" +
            "\n" +
            "\n" +
            "<td id=HIDE_FILTER2>\n" +
            "<table id=GENRE_TABLE cellspacing=0 cellpadding=0>\n" +
            "<tr valign=top>\n" +
            "<td>Genre:&nbsp;<br><small>\n" +
            "<a href=javascript:genre_all()>all</a><br>\n" +
            "<a href=javascript:genre_none()>none</a><br>\n" +
            "<!--<a href=javascript:hide_filter(2)>hide</a><br>-->\n" +
            "</small></td>\n" +
            "<td><small>\n" +
            "<input type=checkbox id=G_ACTION checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_ACTION')>Action</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_ADVENTURE checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_ADVENTURE')>Adventure</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_ANIMATION checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_ANIMATION')>Animation</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_COMEDY checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_COMEDY')>Comedy</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_DRAMA checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_DRAMA')>Drama</span>&nbsp;<br>\n" +
            "</small></td><td><small>\n" +
            "<input type=checkbox id=G_HORROR checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_HORROR')>Horror</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_MYSTERY checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_MYSTERY')>Mystery</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_SCI-FI checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_SCI-FI')>Sci-Fi</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_ADULT checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_ADULT')>Adult</span>&nbsp;<br>\n" +
            "<input type=checkbox id=G_THRILLER checked=checked onclick=do_filter()><span class=HOVER_ULN onclick=genre_one('G_THRILLER')>Thriller</span>&nbsp;<br>\n" +
            "</small></td><td><small>\n" +
            "</small></td>\n" +
            "<td style='width:1em'></td>\n" +
            "</tr></table></td>\n" +
            "\n" +
            "<td id=HIDE_FILTER3>\n" +
            "<table id=RANGE_TABLE cellspacing=0 cellpadding=0>\n" +
            "<tr><td>Year: <td>\n" +
            "<input type=text id=YMIN value=0 maxlength=5 size=2 onkeyup=numbersOnly(this) onchange=do_filter()> - \n" +
            "<input type=text id=YMAX value=2020 maxlength=5 size=2 onkeyup=numbersOnly(this) onchange=do_filter()></tr>\n" +
            "<tr><td>Runtime: <td>\n" +
            "<input type=text id=TMIN value=0 maxlength=5 size=2 onkeyup=numbersOnly(this) onchange=do_filter()> - \n" +
            "<input type=text id=TMAX value=500 maxlength=5 size=2 onkeyup=numbersOnly(this) onchange=do_filter()></tr>\n" +
            "<tr><td colspan=2><small>\n" +
            "<!--&nbsp; <a href=javascript:hide_filter(3)>hide</a>-->\n" +
            "</small></tr></table></td>\n" +
            "</tr></table>\n" +
            "</form><br>\n" +
            "<script type=\"text/javascript\">init_filter();</script>";
        
        
        String beginTable ="<table id=MTABLE cellspacing=0 cellpadding=0>";
            
        String end = "</table>\n" +
                "\n" +
                "</body></html>";
        
        int nfoCounter = 0;
        for(Lookups lookup:lookupList) {
            if (lookup.nfoExists){
                nfoCounter++;
            }
        }
        
        String[] movie = new String[nfoCounter];
        int i = 0;
        for (Lookups lookup:lookupList){
            String origPicPath = lookup.folder+"\\folder.jpg";
            String resizedPicPath = this.moviePicFolder + "\\" + lookup.resizePicName;
            if(Moviename.getInstance().FileExist(origPicPath) && !Moviename.getInstance().FileExist(resizedPicPath)) {
                ResizeImage.getInstance().resizeIMG(origPicPath, this.moviePicFolder, lookup.resizePicName);
            }
            
            if (lookup.nfoExists){
                movie[i] = "<tr><td>\n" +
                        "<table width=100% cellspacing=0 class=movietable>\n" +
                        "<tr>\n" +
                        "<td class=poster rowspan=4 width=95><img src=\"" + this.moviePicFolder + "\\" 
                        + lookup.resizePicName + "\"></td>\n" +
                        "<td class=title height=1*><b>&nbsp;\n" +
                        "<a class=MPRODUCER>"+lookup.publisher + "</a><hr/>&nbsp;\n" +
                        "<a class=MTITLE>"+lookup.movie+"</a></b>\n" +
                        " (<span class=MYEAR>" + lookup.xmlNFO.getYear() + "</span>) &nbsp; <small><i></i></small>\n" +
                        "</td></tr>\n" +
                        "<tr class=moviedesc><td height=1*>\n" +
                        "MPAA: <b class=MMPAA>"+ lookup.xmlNFO.getMpaa() + "</b> &nbsp;&nbsp; Runtime: <b class=MRUNTIME>" + lookup.xmlNFO.getRuntime() + "</b> min &nbsp;&nbsp; <i class=MGENRE>(" + lookup.xmlNFO.getGenre() + ")</i>\n" +
                        "</td></tr>\n" +
                        "<tr class=moviedesc><td class=plot><font size=-1>\n" +
                        lookup.xmlNFO.getPlot()+
                        "</td></tr>\n" +
                        "<tr class=moviemeta><td height=1*><font size=-2>\n" +
                        "Location: \n" +
                        "<a>" + lookup.publisher +"\\" + lookup.movie + "</a>\n" + 
                        "</font></td></tr>\n" +
                        "</table><br>\n" +
                        "</td></tr>";
                
                if (i <= nfoCounter){
                i++;
                }
            }
        }              
        
        writeHTML(doctype, header, sortFilter, beginTable, movie, end);
        CopyResources();

    }
    
    private void CopyResources() throws IOException{
        if(!Moviename.getInstance().FileExist(this.htmlFolder + "\\moviecat.js")){
            File file = new File(this.htmlFolder + "\\moviecat.js");
            FileOutputStream outputStream;
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/moviecat.js")) {
                outputStream = new FileOutputStream(file);
                byte by;
                while ((by = (byte) inputStream.read()) != -1){
                    outputStream.write(by);
                }
            }
            outputStream.close();
        }
        
        if(!Moviename.getInstance().FileExist(this.htmlFolder + "\\white.css")){
            File file = new File(this.htmlFolder + "\\white.css");
            FileOutputStream outputStream;
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/white.css")) {
                outputStream = new FileOutputStream(file);
                byte by;
                while ((by = (byte) inputStream.read()) != -1){
                    outputStream.write(by);
                }
            }
            outputStream.close();
        }
     
    }
    
    private void writeHTML(String docType, String header, String sortFilter, String beginTable, String[] movie, String end) throws IOException{
        
        File file = new File(this.htmlFolder + "\\movies.html");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(docType);
            bw.write(header);
            bw.write(sortFilter);
            bw.write(beginTable);
            for (String mov:movie){
                bw.write(mov);
            }
            bw.write(end);
        }
    }
}
