package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column
    private String userno;
    
    // @Column
    // private String username;
    
    // @JsonIgnore REST APIでUserクラスをJSON出力する場合に、パスワード・フィールドを除外する
    @JsonIgnore
    @Column
    private String encodedPassword;

}