package com.zwli.generic;

public class FavoritesTest {
    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorites(String.class, "Java");
        f.putFavorites(Integer.class, 0xcafebabe);
        f.putFavorites(Class.class, Favorites.class);

        String favoritesString = f.getFavorites(String.class);
        int favoritesInteger = f.getFavorites(Integer.class);
        Class<?> favoritesClass = f.getFavorites(Class.class);
        System.out.printf("%s %x %s%n", favoritesString, favoritesInteger, favoritesClass.getName());
    }
}
