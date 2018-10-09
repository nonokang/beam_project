package com.ab.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aaaa {

    private static final Pattern FILE_SCHEME_PATTERN =
            Pattern.compile("(?<scheme>[a-zA-Z][-a-zA-Z0-9+.]*):.*");

    public static void main(String[] arg){
        String spec = "ht-tp:\\Users\\ennwpae\\Desktop\\.temp-beam-2018-06-167_02-15-10-1\\4bbc567c-d90b-4567-bc4d-f99a7c7331fd";
        Matcher matcher = FILE_SCHEME_PATTERN.matcher(spec);

        if (!matcher.matches()) {
            System.out.println("this is file");
        } else {
            System.out.println("this is group scheme :"+ matcher.group("scheme").toLowerCase());
        }
    }
}
