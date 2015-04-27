package com.github.yoojia.fast.utils;

import java.util.Iterator;

/**
 * Joiner
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-17
 * @since   1.0
 */
public class Joiner {

    private final char mSeparatorChar;

    public Joiner(char separatorChar) {
        mSeparatorChar = separatorChar;
    }

    public static Joiner on(char separatorChar){
        return new Joiner(separatorChar);
    }

    public String join(Iterable<?> parts){
        StringBuilder buf = new StringBuilder();
        Iterator iterator = parts.iterator();
        if (iterator.hasNext()) {
            buf.append(iterator);
            while (iterator.hasNext()) {
                buf.append(mSeparatorChar);
                buf.append(iterator);
            }
        }
        return buf.toString();
    }

}
