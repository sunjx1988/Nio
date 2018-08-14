package jx;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: sunjx
 * @Date: 2018/8/14 0014 16:11
 * @Description:
 */
public class SelectorMain {
    public static void main(String[] args) {
        try {
            // 1.创建Selector
            Selector selector = Selector.open();

            // 2.生成channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            // 3.绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));

            // 4.将channel设置为非阻塞, FileChannel 不能设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            // 5.监听
            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                if(socketChannel != null){
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    ByteBuffer writeBuffer = ByteBuffer.allocate(128);

                    //读
                    int readSize = socketChannel.read(buffer);

                    while (readSize > 0){

                        buffer.flip();
                        while (buffer.hasRemaining()){
                            System.out.println((char) buffer.get());
                        }
                        socketChannel.write(writeBuffer);
                        buffer.clear();
                        writeBuffer.clear();
                        readSize = socketChannel.read(buffer);
                    }

                    //写
                    buffer.clear();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
