package panda.domain.entity;

import panda.domain.api.Identifiable;

import java.io.Serializable;

/**
 * Base Entity class
 * Defines equals() and hashCode() methods according to best practices by Vlad Mihalcea.<br>
 * <p>
 * Implements {@link Serializable} interface for proper de/serialization of id field in child abstract classes.
 *
 * @see <a href="https://stackoverflow.com/questions/893259/should-an-abstract-class-have-a-serialversionuid/893342">
 * serialVersionUID is required on abstract classes implementing Serializable</a>
 * @see <a href="https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/">
 * The best way to implement equals, hashCode, and toString with JPA and Hibernate</a>
 */
abstract class BaseEntity<I> implements Identifiable<I>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return getId() != null && getId().equals(((Identifiable) o).getId());
    }
}
