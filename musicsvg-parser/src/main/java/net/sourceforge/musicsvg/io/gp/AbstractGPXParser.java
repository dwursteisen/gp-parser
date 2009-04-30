/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io.gp;

import com.google.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.sourceforge.musicsvg.io.Parser;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

/**
 *
 * @author Dav
 */
public abstract class AbstractGPXParser implements Parser{
    protected static final int BITMASK_1 = 0x01;
    protected static final int BITMASK_2 = 0x02;
    protected static final int BITMASK_3 = 0x04;
    protected static final int BITMASK_4 = 0x08;
    protected static final int BITMASK_5 = 0x10;
    protected static final int BITMASK_6 = 0x20;
    protected static final int BITMASK_7 = 0x40;
    protected static final int BITMASK_8 = 0x80;
    
    protected FileInputStream is;
    protected GP4ParserListener listener;


    @Inject
    public void setListener(GP4ParserListener listener) {
        this.listener = listener;
    }

    protected byte readByte() throws IOException {
        return (byte) this.is.read();
    }

    protected int readInt() throws IOException {
        int integer = 0;
        byte[] b = {0, 0, 0, 0};

        this.is.read(b);
        integer = ((b[3] & 0xff) << 24) | ((b[BITMASK_2] & 0xff) << BITMASK_5) | ((b[BITMASK_1] & 0xff) << BITMASK_4) | (b[0] & 0xff);

        return integer;
    }

    protected boolean readBoolean() throws IOException {
        return (this.is.read() == BITMASK_1);
    }

    protected String readStringByte(int expectedLength) throws IOException {
        byte[] bytes;
        int realLength = readUnsignedByte();

        if (expectedLength != 0) {
            bytes = new byte[expectedLength];
        } else {
            bytes = new byte[realLength];
        }
        this.is.read(bytes);

        realLength = (realLength >= 0) ? realLength : expectedLength;
        return new String(bytes, 0, realLength);
    }

    protected String readStringInteger() throws IOException {
        byte[] b;
        String str;
        int length = readInt();

        b = new byte[length];
        this.is.read(b);

        str = new String(b);
        return str;
    }

    protected String readStringIntegerPlusOne() throws IOException {
        byte[] b;
        String str;
        int lengthPlusOne = readInt();
        int length = lengthPlusOne - 1;

        if (length != this.is.read()) {
            throw new IOException("Unable to read a field from the file because of a wrong size");
        }

        b = new byte[length];
        this.is.read(b);

        str = new String(b);
        return str;
    }

    protected int readUnsignedByte() throws IOException {
        return this.is.read();
    }

    public void openFile(File file) throws IOException {
         
        try {
            this.is = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        
    }

}
