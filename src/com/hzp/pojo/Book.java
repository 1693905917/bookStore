package com.hzp.pojo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author ASUS
 * @projectName book
 * @description: 图书类
 * @date 2022-01-25 20:13
 */
public class Book {
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String img_path="static/img/default.jpg";
    public Book(){

    }

    public Book(String name, String author, BigDecimal price, Integer sales, Integer stock, String img_path) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.img_path = img_path;
    }

    public Book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String img_path) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if(img_path!=null&& !"".equals(img_path)){
            this.img_path = img_path;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + img_path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(price, book.price) && Objects.equals(sales, book.sales) && Objects.equals(stock, book.stock) && Objects.equals(img_path, book.img_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, price, sales, stock, img_path);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return img_path;
    }

    public void setImgPath(String imgPath) {
        this.img_path = imgPath;
    }
}
