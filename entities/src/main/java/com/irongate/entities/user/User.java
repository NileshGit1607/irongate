package com.irongate.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irongate.entities.BusinessEntity;
import com.irongate.entities.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Getter
@Setter
@ToString
public class User extends BusinessEntity {
    private String username;
    @JsonIgnore
    private String hash;
    private Role role;
}