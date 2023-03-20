package com.example.springgenericcrudpaginationfilters.generic.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Abstract Entity that will be used in other abstractions.
 */

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

    // switch GenerationType if you have not common postgresql sequences
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
