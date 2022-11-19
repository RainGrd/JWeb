package com.bdqn.entity;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-09  09:01
 * @Description: TODO
 * @Version: 1.0
 */
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
