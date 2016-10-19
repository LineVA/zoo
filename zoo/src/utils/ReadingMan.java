package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author doyenm
 */
public class ReadingMan {
 public static String load(File f) throws IOException {
        FileChannel channel = new FileInputStream(f).getChannel();
        try {
            ByteBuffer b = ByteBuffer.allocate((int) channel.size());
            channel.read(b);
            return new String(b.array());
        } finally {        
            channel.close();
        }
    }
}
