package io.learning.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
