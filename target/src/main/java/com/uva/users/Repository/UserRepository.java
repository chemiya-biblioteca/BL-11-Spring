package com.uva.users.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.uva.users.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query("SELECT v FROM User v WHERE v.name = ?1 ")
    List<User> findByName(String name);

    @Query(value="SELECT * FROM User v,Car c WHERE v.edad > ?1 and v.id=c.uid and c.model= ?2 ", nativeQuery = true)
    List<User> findByEdadModelo(int edad,String modelo);


   
   

    



}
