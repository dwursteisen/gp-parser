/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io.gp;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dav
 */
public class ParserHelper {
    protected static final int BITMASK_1 = 0x01;
    protected static final int BITMASK_2 = 0x02;
    protected static final int BITMASK_3 = 0x04;
    protected static final int BITMASK_4 = 0x08;
    protected static final int BITMASK_5 = 0x10;
    protected static final int BITMASK_6 = 0x20;
    protected static final int BITMASK_7 = 0x40;
    protected static final int BITMASK_8 = 0x80;

    protected byte readByte(InputStream is) throws IOException {
        return (byte) is.read();
    }

    protected int readInt(InputStream is) throws IOException {
        int integer = 0;
        byte[] b = {0, 0, 0, 0};

        is.read(b);
        integer = ((b[3] & 0xff) << 24) | ((b[BITMASK_2] & 0xff) << BITMASK_5) | ((b[BITMASK_1] & 0xff) << BITMASK_4) | (b[0] & 0xff);

        return integer;
    }

    protected boolean readBoolean(InputStream is) throws IOException {
        return (is.read() == BITMASK_1);
    }

    protected String readStringByte(InputStream is, int expectedLength) throws IOException {
        byte[] bytes;
        int realLength = readUnsignedByte(is);

        if (expectedLength != 0) {
            bytes = new byte[expectedLength];
        } else {
            bytes = new byte[realLength];
        }
        is.read(bytes);

        realLength = (realLength >= 0) ? realLength : expectedLength;
        return new String(bytes, 0, realLength);
    }

    protected String readStringInteger(InputStream is) throws IOException {
        byte[] b;
        String str;
        int length = readInt(is);

        b = new byte[length];
        is.read(b);

        str = new String(b);
        return str;
    }

    protected String readStringIntegerPlusOne(InputStream is) throws IOException {
        byte[] b;
        String str;
        int lengthPlusOne = readInt(is);
        int length = lengthPlusOne - 1;

        if (length != is.read()) {
            throw new IOException("Unable to read a field from the file because of a wrong size");
        }

        b = new byte[length];
        is.read(b);

        str = new String(b);
        return str;
    }

    protected int readUnsignedByte(InputStream is) throws IOException {
        return is.read();
    }

}
