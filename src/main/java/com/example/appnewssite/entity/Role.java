package com.example.appnewssite.entity;

import com.example.appnewssite.entity.enums.Permissions;
import com.example.appnewssite.entity.template.AbstractEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Role extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permissions> permissions;

    @Column(length = 500)
    private String description;

}
