package com.irongate.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@ToString
public abstract class BusinessEntity {
    @Id
    private String id;
    private long created;
    @Indexed(name = "type_index")
    private String _type;

}
