package com.mycompany.propertymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@Component
public class CalculatorDTO {

    public Double num1;
    public Double num2;
    public Double num3;
    public Double num4;
}
