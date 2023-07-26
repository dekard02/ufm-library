package com.ufm.library.constant;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StorageContants {
    private static final String DEFAULT_AVATAR = "default-avatar.png";

    public static final String PUBLIC_FOLDER = "public/";

    public static final String STUDENT_IMAGES_FOLDER = PUBLIC_FOLDER + "images/student/";

    public static final String STUDENT_DEFAULT_IMAGE = STUDENT_IMAGES_FOLDER + DEFAULT_AVATAR;

    public static final String LIBRARIAN_IMAGES_FOLDER = PUBLIC_FOLDER + "images/librarian/";

    public static final String LIBRARIAN_DEFAULT_IMAGE = LIBRARIAN_IMAGES_FOLDER + DEFAULT_AVATAR;

    public static final String BOOK_IMAGES_FOLDER = PUBLIC_FOLDER + "images/book/";

    public static final String BOOK_DEFAULT_IMAGE = BOOK_IMAGES_FOLDER + "book-placeholder.png";

    public static List<String> getAllFolders() {
        var fields = StorageContants.class.getDeclaredFields();
        return Stream.of(fields)
                .filter((field -> {
                    var nameContainFolder = field.getName().contains("FOLDER");
                    var isStatic = Modifier.isStatic(field.getModifiers());
                    var isStringType = field.getType() == String.class;
                    return nameContainFolder && isStatic && isStringType;
                }))
                .map(field -> {
                    try {
                        return field.get(null).toString();
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
