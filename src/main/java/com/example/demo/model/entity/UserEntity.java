package com.example.demo.model.entity;

import com.example.demo.model.Role;
import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "users", schema = "main")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq")
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "main.users_user_id_seq", allocationSize = 1)

    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "is_active")
    @Enumerated(value = EnumType.STRING)
    private Status isActive;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
