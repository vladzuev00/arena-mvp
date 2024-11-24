package com.besmart.arena.util;

import com.besmart.arena.crud.domain.*;
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

    public static Venue getVenueLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Venue.builder().id(id).build();
    }

    public static Promoter getPromoterLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Promoter.builder().id(id).build();
    }

    //TODO: test
    public static Category getCategoryLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Category.builder().id(id).build();
    }

    //TODO: test
    public static Tag getTagLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Tag.builder().id(id).build();
    }

    public static Show getShowLazily(ResultSet resultSet, String columnNameId)
            throws SQLException {
        Long id = resultSet.getLong(columnNameId);
        return Show.builder().id(id).build();
    }
}
