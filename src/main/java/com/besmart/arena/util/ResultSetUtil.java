package com.besmart.arena.util;

import com.besmart.arena.crud.dto.Category;
import com.besmart.arena.crud.dto.Venue;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static java.util.UUID.fromString;

@UtilityClass
public final class ResultSetUtil {

    public static UUID getUUID(ResultSet resultSet, String columnName)
            throws SQLException {
        return fromString(resultSet.getString(columnName));
    }

    public static Category getCategoryLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Category.builder().id(id).build();
    }

    public static Venue getVenueLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Venue.builder().id(id).build();
    }
}
