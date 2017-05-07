package com.example.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class ProfileForm {
	
	private Integer id;
    
	@NotBlank
	@Size(min = 1, max = 127)
    private String Name;
	
    private String userno;
    
    @NotNull
    private Integer Age;
    
    @NotNull
    private String Department;
    
    private String Club;
    
    private String Dispatchlocation;

    private String Freetext;

}
