package net.worklogtracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="tbl_user")
public class UserEntity {
    @Id
    private String username;
    private String password;
    private String role;
}
