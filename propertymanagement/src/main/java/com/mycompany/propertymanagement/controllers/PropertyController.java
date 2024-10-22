package com.mycompany.propertymanagement.controllers;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PropertyController {
    @Autowired
PropertyService propertyService;
    @PostMapping("/properties")
public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyService.saveProperty(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.CREATED);
    return responseEntity;
}

@GetMapping("/getall")
public ResponseEntity<List<PropertyDTO>> getAllProperties(){
       List<PropertyDTO> propertyList= propertyService.getAllProperties();
       ResponseEntity<List<PropertyDTO>> responseEntity=new ResponseEntity<>(propertyList,HttpStatus.CREATED);
       return responseEntity;
}

    @GetMapping("/getall/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long userId){
        List<PropertyDTO> propertyList= propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity=new ResponseEntity<>(propertyList,HttpStatus.CREATED);
        return responseEntity;
    }

@PutMapping("/properties/{propertyId}")
public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
      propertyDTO= propertyService.updateProperty(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity=new ResponseEntity<>(propertyDTO, HttpStatus.OK);

        return responseEntity;
}

@PatchMapping("/properties/p/{propertyId}")
public ResponseEntity<PropertyDTO> updatePrice(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId) {
        propertyDTO=propertyService.updatePrice(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity=new ResponseEntity<>(propertyDTO, HttpStatus.OK);

        return responseEntity;
}
    @PatchMapping("/properties/d/{propertyId}")
    public ResponseEntity<PropertyDTO> updateDescription(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId) {
        propertyDTO=propertyService.updateDescription(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity=new ResponseEntity<>(propertyDTO, HttpStatus.OK);

        return responseEntity;

    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return responseEntity;
    }

}
