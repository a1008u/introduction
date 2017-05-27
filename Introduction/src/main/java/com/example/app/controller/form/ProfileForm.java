package com.example.app.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class ProfileForm {
	
	private Integer id;
    
	@NotBlank
	@Size(min = 1, max = 30)
    private String Name;
	
	@NotBlank
    private String userno;
    
    @Max(150)
	@Min(0)
    private Integer Age;
    
	@NotBlank
    private String Department;
    
    private String Club;
    
    @NotBlank
    private String Dispatchlocation;

    private String Freetext;

}
