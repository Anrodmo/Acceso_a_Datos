/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad_ut1_maven_jakarta;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anrod
 */

@XmlRootElement (name = "catalog")
//@XmlAccessorType (XmlAccessType.FIELD) opcional
public class Catalog {
    
    @XmlElement(name = "book")
    private List<Book> catalogoDeLibros=null;
          
   
    public List<Book> getCatalog(){
        return catalogoDeLibros;
    }
    
    public void setCatalogoDeLibros(List<Book> catalogoDeLibros) {
        this.catalogoDeLibros = catalogoDeLibros;
    }
    
    
    
    
//    @XmlElement
//    String book;
//    @XmlAttribute
//    String id;
//    @XmlElement
//    String title;
//    @XmlElement
//    String author;
//    @XmlElement
//    String genre;
//    @XmlElement
//    String price;
//    @XmlElement
//    String publish_date;
//    @XmlElement
//    String description;
//
//    public Catalog(String book, String id, String title, String author, String genre, String price, String publish_date, String description) {
//        super();
//        this.book = book;
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.genre = genre;
//        this.price = price;
//        this.publish_date = publish_date;
//        this.description = description;
//    }
//    
//    public Catalog(){
//        super();
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//    
//        public String getBook() {
//        return book;
//    }
//
//    public void setBook(String book) {
//        this.book = book;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getPublish_date() {
//        return publish_date;
//    }
//
//    public void setPublish_date(String publish_date) {
//        this.publish_date = publish_date;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    

    

    

    
    
    
    
    
    
    
}
