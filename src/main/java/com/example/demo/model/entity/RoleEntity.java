package com.example.demo.model.entity;

import com.example.demo.model.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "roles", schema = "main")
public class RoleEntity {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roles_seq")
    @SequenceGenerator(
            name = "roles_seq",
            sequenceName = "main.roles_role_id_seq", allocationSize = 1)
    private Long roleId;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "user_id")
    private Long userId;

}
