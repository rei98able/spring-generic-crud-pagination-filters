package com.example.springgenericcrudpaginationfilters.model;

import com.example.springgenericcrudpaginationfilters.generic.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "example", schema = "public", catalog = "test_database")
public class ExampleEntity extends AbstractEntity {
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "dt_created")
    private Timestamp dtCreated;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @Basic
    @Column(name = "count")
    private Integer count;
    @OneToOne
    @JoinColumn(name = "nested_id")
    private ExampleNestedObjectEntity nestedObject;
    @OneToMany
    @JoinTable(
            name = "example_l_list",
            joinColumns = @JoinColumn(name = "example_id"),
            inverseJoinColumns = @JoinColumn(name = "list_id")
    )
    private Set<ListEntity> lists;

}
