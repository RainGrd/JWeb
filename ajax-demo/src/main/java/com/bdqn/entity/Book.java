package com.bdqn.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-17  14:55
 * @Description: TODO
 * @Version: 1.0
 */
@Alias("book")
public class Book implements Serializable {

    private static final long serialVersionUID = -3193580021342828087L;

    private Integer id;
    private String bookName;
    private String author;
    private String press;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
