package com.uva.users.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uva.users.Excepcion.UserExcepcion;
import com.uva.users.Repository.CarRepository;
import com.uva.users.Repository.UserRepository;
import com.uva.users.model.Car;
import com.uva.users.model.User;

import org.json.JSONObject;



/*
 * 
 * estructura:
 * 
 * se utiliza response entity
 * 
 * -post de usuario con httpheaders
 * -post de coche con httpeaders
 * -get de usuario
 * -update de usuario con pathvariable
 * -delete de usuario con pathvariable
 * -get de usuario por id
 * -consulta preparada de usuario
 * -onsulta preparada de las dos tablas
 * 
 * 
 * 
 * 
 * 
 */





@RestController
@RequestMapping("ejemploUsuarios")
public class ejemploRest {
    private final UserRepository repository;
    private final CarRepository repositoryCar;

    ejemploRest(UserRepository repository,CarRepository repositoryCar) {
        this.repository = repository;
        this.repositoryCar = repositoryCar;
    }



    //post de usuario con httpheaders-------------------------------------------
    /*
     * {

 "name":"chema",
  "email":"chemiya",
  "edad":23
 
}
     * 
     * 
     * 
     * 
     */
    @PostMapping(value="/usuario",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newUser(@RequestBody User newUser) {
        try {
            repository.save(newUser);
            HttpHeaders headers = new HttpHeaders();
            headers.add("cabecera", "cabecera cuando se guarda bien");
            return new ResponseEntity<>("guardado con exito",headers,HttpStatus.OK);
        } catch (Exception e) {
            
            throw new UserExcepcion("Error al crear el nuevo registro.");
        }
    }






    //post de coche con httpeaders-----------------------------------------
    /*
     * {
  "model":"cla",
  "year":"2001",
  "horsepower":"22",
  "uid":"1"
  
}
     * 
     * 
     * 
     * 
     * 
     */
    @PostMapping(value="/coche",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newCoche(@RequestBody String newCoche) {
        JSONObject cocheJson=new JSONObject(newCoche);
        Car guardarCoche=new Car();
        
        guardarCoche.setModel(cocheJson.getString("model"));
        guardarCoche.setHorsepower(Integer.parseInt(((String)cocheJson.get("horsepower"))));
        
        
        guardarCoche.setHorsepower(Integer.parseInt(((String)cocheJson.getString("horsepower"))));
        guardarCoche.setYear(Integer.parseInt(((String)cocheJson.getString("year"))));
        
        int idUsuario=Integer.parseInt(((String)cocheJson.getString("uid")));
        User usuarioAsignado=repository.findById(idUsuario).orElseThrow(()->new UserExcepcion("no encontrado"));
       
       
          guardarCoche.setUser(usuarioAsignado);
        try {
            repositoryCar.save(guardarCoche);
            HttpHeaders headers = new HttpHeaders();
            headers.add("cabecera", "cabecera cuando se guarda bien");
            
            return new ResponseEntity<>("guardado con exito",headers,HttpStatus.OK);
        } catch (Exception e) {
            
            throw new UserExcepcion("Error al crear el nuevo registro.");
        }
    }





    //get de usuario------------------------------
    @GetMapping(value="/usuario")
    public List<User> getUsuers() {
        return (List<User>)repository.findAll();
    }




    //update de usuario con pathvariable----------------------------
/*http://localhost:8080/ejemploUsuarios/usuario/3


 * {
  "id":3,
  "name":"chema4",
  "email":"chemiya11",
  "edad":12
}
 * 
 * 
 * 
 * 
 * 
 * 
 */

    @PutMapping("/usuario/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userUpdateData){
	
		Optional<User> findUser = repository.findById(id);
		
		if(findUser.isPresent()){
			
			User userToUpdate = findUser.get();			
			
			userToUpdate.copyDataFromUser(userUpdateData);			
			
			User userSaved = repository.save(userToUpdate);			
		    return ResponseEntity.ok(userSaved);
		}
		else {
			throw new UserExcepcion("Not found User by id: " + id);
		}
	}








    //delete de usuario con pathvariable-----------------------------------
    @DeleteMapping("/usuario/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
	
		Optional<User> findUser = repository.findById(id);
	
		if(findUser.isPresent()){	
			
			repository.delete(findUser.get());			
		    return ResponseEntity.ok(true);
		}
		else {
			throw new UserExcepcion("Not found User by id: " + id);
		}
	}








    //get de usuario por id----------------------------------
    @GetMapping("/usuario/{id}")
	public ResponseEntity<User> getUserPorId(@PathVariable int id) {
 
		Optional<User> user = repository.findById(id);
		
		if(user.isPresent()){
           
		    return ResponseEntity.ok(user.get());
		}
		//Si no, lanzamos un error
		else{
		    throw new UserExcepcion("Not found User by id: " + id);
		}
	}











    //consulta preparada de usuario----------------------------------------------
    //http://localhost:8080/ejemploUsuarios/usuario?name=chema
    @GetMapping(value="/usuarioPorNombre")
    public List<User> getUsersName(@RequestParam String name) {
        return (List<User>)repository.findByName(name);
        
    }









    //consulta preparada de las dos tablas-------------------------
    //http://localhost:8080/ejemploUsuarios/edadModelo?edad=21&modelo=cla
    @GetMapping(value="/edadModelo")
    public List<User> getUsersCar(@RequestParam String edad, @RequestParam String modelo ) {
        return (List<User>)repository.findByEdadModelo(Integer.parseInt(edad),modelo);
        
        
    }



  

   

}
