package com.besmart.arena.crud.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class ShowTest {

    @Test
    public void categoryNamesShouldBeGot() {
        String firstGivenName = "first-name";
        String secondGivenName = "second-name";
        String thirdGivenName = "third-name";
        Show givenShow = Show.builder()
                .categories(
                        List.of(
                                Category.builder().name(firstGivenName).build(),
                                Category.builder().name(secondGivenName).build(),
                                Category.builder().name(thirdGivenName).build()
                        )
                )
                .build();

        String[] actual = givenShow.getCategoryNames();
        String[] expected = {firstGivenName, secondGivenName, thirdGivenName};
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
