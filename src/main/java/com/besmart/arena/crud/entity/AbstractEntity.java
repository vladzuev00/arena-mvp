package com.besmart.arena.crud.entity;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.hash;

public abstract class AbstractEntity<ID> implements Serializable {

    public abstract void setId(ID id);

    public abstract ID getId();

    @Override
    @SuppressWarnings({"unchecked", "EqualsWhichDoesntCheckParameterClass"})
    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (Hibernate.getClass(this) != Hibernate.getClass(otherObject)) {
            return false;
        }
        AbstractEntity<ID> other = (AbstractEntity<ID>) otherObject;
        return Objects.equals(getId(), other.getId());
    }

    @Override
    public final int hashCode() {
        return hash(getId());
    }
}
