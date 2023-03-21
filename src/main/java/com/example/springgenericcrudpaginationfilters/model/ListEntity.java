package com.example.springgenericcrudpaginationfilters.model;

import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "list", schema = "public", catalog = "test_database")
public class ListEntity extends AbstractEntity {
    @Basic
    @Column(name = "name")
    private String name;
}
