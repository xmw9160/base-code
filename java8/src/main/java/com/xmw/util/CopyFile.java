package com.xmw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import com.google.common.io.Files;

/**
 * 文件拷贝几种不同io实现方式
 *
 * @author mingwei.xia
 * @date 2018/7/24 10:45
 * @since V1.0
 */
@SuppressWarnings("unused")
public class CopyFile {

    public static void copyFileByStream(File source, File dest) throws
            IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public static void copyFileByChannel(File source, File dest) throws
            IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(dest).getChannel()) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }

    public static void fileCopy(File source, File dest) throws IOException {
        Files.copy(source, dest);
    }
}
