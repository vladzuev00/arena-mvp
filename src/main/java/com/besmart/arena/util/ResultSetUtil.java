package com.besmart.arena.util;

import com.besmart.arena.crud.domain.*;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static java.util.UUID.fromString;

//TODO: remove unused
@UtilityClass
public final class ResultSetUtil {

    public static UUID getUUID(ResultSet resultSet, String alias)
            throws SQLException {
        return fromString(resultSet.getString(alias));
    }

    public static Venue getVenueLazily(ResultSet resultSet, String aliasId)
            throws SQLException {
        Long id = resultSet.getLong(aliasId);
        return Venue.builder().id(id).build();
    }

    public static Promoter getPromoterLazily(ResultSet resultSet, String aliasId)
            throws SQLException {
        Long id = resultSet.getLong(aliasId);
        return Promoter.builder().id(id).build();
    }

    public static Category getCategoryLazily(ResultSet resultSet, String aliasId)
            throws SQLException {
        Long id = resultSet.getLong(aliasId);
        return Category.builder().id(id).build();
    }

    public static Show getShowLazily(ResultSet resultSet, String aliasId)
            throws SQLException {
        Long id = resultSet.getLong(aliasId);
        return Show.builder().id(id).build();
    }

    //TODO: test
    public static Provider getProviderLazily(ResultSet resultSet, String aliasId)
            throws SQLException {
        Long id = resultSet.getLong(aliasId);
        return Provider.builder().id(id).build();
    }
}
