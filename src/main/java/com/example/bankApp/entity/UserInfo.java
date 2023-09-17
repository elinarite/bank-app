package com.example.bankApp.entity;

import com.example.bankApp.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String roles;

//    @ElementCollection(fetch= FetchType.EAGER)
//    @CollectionTable(
//            name="roles",
//            joinColumns = @JoinColumn(name="user_id")
//    )
//    @Column(name="user_role")
//    private List<String> roles;

}

