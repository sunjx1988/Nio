package jx;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther: sunjx
 * @Date: 2018/8/14 0014 14:17
 * @Description:NIO 文件读写操作
 */
public class FileNioMain {

    public static void main(String[] args) {
        try {
            Path filePath = Paths.get("E:\\nio.txt");
            RandomAccessFile file = new RandomAccessFile(filePath.toFile(),"rw");
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }

            // 1.获取channel
            FileChannel channel = file.getChannel();

            // 2.创建buffer并开辟出能存储128个byte的内存空间
            ByteBuffer buffer = ByteBuffer.allocate(128);

            // 3.读取
            int readCount = channel.read(buffer);

            while (readCount != -1){
                // 4.将position初始化置为0
                buffer.flip();

                while (buffer.hasRemaining()){
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                readCount = channel.read(buffer);
            }

            file.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
