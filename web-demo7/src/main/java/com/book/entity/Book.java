package com.book.entity;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.book.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-10  09:41
 * @Description: 实体类
 * @Version: 1.0
 */
public class Book {
    public static final long serialVersionUID = 1L;
    private String id;
    private String name;
    public Book(){

    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
