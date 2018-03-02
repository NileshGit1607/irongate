package com.irongate.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Train {
    String number;
    String name;
    String source;
    String destination;
    List<Station> stations;
}
