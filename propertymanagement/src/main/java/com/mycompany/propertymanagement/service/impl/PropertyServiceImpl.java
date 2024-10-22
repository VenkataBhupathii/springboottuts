package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optUe = userRepository.findById(propertyDTO.getUserId());
        if (optUe.isPresent()) {
            PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optUe.get());
            pe = propertyRepository.save(pe);

            propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        }
        else{
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("USER_ID_NOT_EXIST");
            errorModel.setMessage("User does not exist");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
            return propertyDTO;

    }

    @Override
    public List<PropertyDTO> getAllProperties() {
            List<PropertyEntity> listofProps=(List<PropertyEntity>)propertyRepository.findAll();
            List<PropertyDTO> propList=new ArrayList<PropertyDTO>();
            for(PropertyEntity pe :listofProps){
                PropertyDTO dto=propertyConverter.convertEntitytoDTO(pe);
                propList.add(dto);
            }
        return propList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listofProps=(List<PropertyEntity>)propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propList=new ArrayList<PropertyDTO>();
        for(PropertyEntity pe :listofProps){
            PropertyDTO dto=propertyConverter.convertEntitytoDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn= propertyRepository.findById(propertyId);
       PropertyDTO dto=null;
        if(optEn.isPresent()){
            PropertyEntity pe= optEn.get();

            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());

            pe = propertyRepository.save(pe);
            dto=propertyConverter.convertEntitytoDTO(pe);


        }
        return dto;
    }

    @Override
    public PropertyDTO updatePrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> OptEn= propertyRepository.findById(propertyId);

        PropertyDTO dto=null;

        if(OptEn.isPresent()){
            PropertyEntity pe= OptEn.get();

            pe.setPrice(propertyDTO.getPrice());
            pe=propertyRepository.save(pe);
dto= propertyConverter.convertEntitytoDTO(pe);

        }
        return dto;
    }

    @Override
    public PropertyDTO updateDescription(PropertyDTO propertyDTO,Long propertyId) {

        Optional<PropertyEntity> optEn=propertyRepository.findById(propertyId);

        PropertyDTO dto=null;

        if(optEn.isPresent()){
            PropertyEntity pe= optEn.get();

            pe.setDescription(propertyDTO.getDescription());

            pe=propertyRepository.save(pe);
           dto= propertyConverter.convertEntitytoDTO(pe);

        }
return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
