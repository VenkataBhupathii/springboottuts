package com.mycompany.propertymanagement.controllers;

import com.mycompany.propertymanagement.dto.CalculatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    @Autowired
   private CalculatorDTO calculatorDTO;

    @GetMapping("/add")
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2) {
        return num1 + num2;
    }

    @GetMapping("/sub/{num1}/{num2}")
    public Double sub(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2) {
        return num1 - num2;
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO){

        Double result=null;
        result=calculatorDTO.getNum3()*calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity=new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }
}
