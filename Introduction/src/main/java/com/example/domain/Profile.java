package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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
    @Size(min = 1, max = 30)
    private String Name;
    
    @Column(nullable = false)
    @Max(150)
	@Min(0)
    private Integer Age;
    
    @Column(nullable = false)
    private String Department;
    
    @Column
    private String Club;
    
    @Column(nullable = false)
    private String Dispatchlocation;
    
    @Column
    private String Freetext;

}
