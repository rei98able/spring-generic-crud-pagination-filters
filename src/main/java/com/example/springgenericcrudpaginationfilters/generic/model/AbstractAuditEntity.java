package com.example.springgenericcrudpaginationfilters.generic.model;//package com.example.pabp_business_logic.generic.crud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Abstract Entity that is using Spring Data Jpa Auditing
 */

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractAuditEntity extends AbstractEntity implements Serializable {
    @CreatedBy
    private Long createdBy;
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;
    @LastModifiedDate
    @Column(name = "updated_date")
    private Timestamp updatedDate;
}

