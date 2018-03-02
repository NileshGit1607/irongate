package com.irongate.entities.sequence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Getter
@Setter
public class SequenceId {

    private long seq = 1;

    private String _type;
}