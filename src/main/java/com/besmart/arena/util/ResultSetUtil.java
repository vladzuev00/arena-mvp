package com.besmart.arena.util;

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
}
