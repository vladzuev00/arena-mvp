package com.besmart.arena.crud.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class ShowTest {

    @Test
    public void categoryExternalIdsShouldBeGot() {
        int firstGivenExternalId = 2331;
        int secondGivenExternalId = 2332;
        int thirdGivenExternalId = 2333;
        Show givenShow = Show.builder()
                .categories(
                        List.of(
                                Category.builder().externalId(firstGivenExternalId).build(),
                                Category.builder().externalId(secondGivenExternalId).build(),
                                Category.builder().externalId(thirdGivenExternalId).build()
                        )
                )
                .build();

        int[] actual = givenShow.getCategoryExternalIds();
        int[] expected = {firstGivenExternalId, secondGivenExternalId, thirdGivenExternalId};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void tagNamesShouldBeGot() {
        String firstGivenName = "first-name";
        String secondGivenName = "second-name";
        String thirdGivenName = "third-name";
        Show givenShow = Show.builder()
                .tags(
                        List.of(
                                Tag.builder().name(firstGivenName).build(),
                                Tag.builder().name(secondGivenName).build(),
                                Tag.builder().name(thirdGivenName).build()
                        )
                )
                .build();

        String[] actual = givenShow.getTagNames();
        String[] expected = {firstGivenName, secondGivenName, thirdGivenName};
        assertArrayEquals(expected, actual);
    }
}
