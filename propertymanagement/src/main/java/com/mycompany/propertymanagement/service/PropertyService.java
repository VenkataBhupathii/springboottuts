package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PropertyService {
     PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getAllPropertiesForUser(Long userId);
   PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyId);
    PropertyDTO updatePrice(PropertyDTO propertyDTO,Long propertyId);
    PropertyDTO updateDescription(PropertyDTO propertyDTO,Long propertyId);
    void deleteProperty(Long propertyId);
}
