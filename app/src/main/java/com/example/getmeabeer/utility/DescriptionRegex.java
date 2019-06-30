package com.example.getmeabeer.utility;

import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionRegex {

    private static final String TAG = DescriptionRegex.class.toString();

    public DescriptionRegex() {
    }

    public String processDescriptString(String regexDescription) {

        String regexString = "";

        Pattern p = Pattern.compile("^([^.]+).");
        Matcher m = p.matcher(regexDescription);
        if(m.find()) {
            regexString = m.group(1);
            Log.d(TAG, "New regex string: " + regexString);
        }
        return regexString;
    }
}
