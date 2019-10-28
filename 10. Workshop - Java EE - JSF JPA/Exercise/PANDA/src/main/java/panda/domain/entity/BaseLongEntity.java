package panda.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * {@link Long} implementation of {@link BaseEntity} abstract class
 * <p>
 * Use {@link AccessType#PROPERTY} for id as best practice to avoid LazyInitializationException
 * <p>
 * Use private setter to prevent mutability
 */

@Setter(AccessLevel.PRIVATE)
@Getter
@MappedSuperclass
abstract class BaseLongEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, insertable = false, updatable = false)
    @Access(AccessType.PROPERTY)
    private Long id;
}
