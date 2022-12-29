package com.uva.users.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

//un usuario tiene muchos coches


@Entity
@Table(name = "User")
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String email;
    
    @Column
    private int edad;

    @OneToMany(mappedBy = "user")
    List<Car>cars;


    User(int id,String name,String email,int edad){
        this.email=email;
        this.name=name;
        this.id=id;
        this.edad=edad;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    public void copyDataFromUser(User source) {
		this.name = source.getName();
		this.email = source.getEmail();
		this.edad=source.getEdad();
        this.id=source.getId();
        
	}

    User(){

    }

    


}
