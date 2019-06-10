package com.azor.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class CodeGenerator{
    /**
     * Generate a random alpha-numeric string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    /**
     * Generate a random numeric string.
     */
    public String nextCode(){
        char[] symbols = digits.toCharArray();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];

        return new String(buf);
    }

    public static CodeGenerator getInstance() {
        return instance;
    }

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    private static final String alphanum = upper + lower + digits;

    private static final CodeGenerator instance;

    static {
        instance = new CodeGenerator();
    }

    private final Random random;

    private final char[] symbols;

    private final char[] buf;

    public CodeGenerator(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public CodeGenerator(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public CodeGenerator(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public CodeGenerator() {
        this(8);
    }
}
