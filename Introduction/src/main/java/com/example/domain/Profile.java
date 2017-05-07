package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* エンティティとFormとBeanの関係性
 * Profile - ProfileForm - ProfileBean
 */
 
//JPAのエンティティを表す
@Entity
//テーブル名を変更(デフォルトでは、クラス名=テーブル名)
@Table(name = "Profile")
@Data
//Lombokでデフォルト・コンストラクタを自動で生成する。
@NoArgsConstructor
//デフォルト・コンストラクタ以外のコンストラクを自動で生成する。
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String userno;
    
    @Column(nullable = false)
    private String Name;
    
    @Column(nullable = false)
    private Integer Age;
    
    @Column
    private String Department;
    
    @Column
    private String Club;
    
    @Column
    private String Dispatchlocation;
    
    @Column
    private String Freetext;

}
