package com.example.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Lombokでデフォルト・コンストラクタを自動で生成する。
@NoArgsConstructor
//デフォルト・コンストラクタ以外のコンストラクを自動で生成する。
@AllArgsConstructor
@Component
public class ProfileBean {
    
    private String userno;
   
    private String Name;

    private Integer Age;
    
    private String Department;
    
    private String Club;

    private String Dispatchlocation;
    
    private String Freetext;

}
