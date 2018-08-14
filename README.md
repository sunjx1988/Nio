# Nio

- 1.Java NIO 教程 
- 2.Java NIO概述 
- 3.Java NIO Channel
- 4.Java NIO Buffer
- 5.Java NIO Scatter / Gather
- 6.Java NIO 通道之间的数据传输
- 7.Java NIO Selector
- 8.Java NIO FileChannel
- 9.Java NIO SocketChannel
- 10.Java NIO ServerSocketChannel
- 11.Java NIO 非阻塞式服务器
- 12.Java NIO DataGramChannel
- 13.Java NIO Pipe
- 14.Java NIO 与IO
- 15.Java NIO Path 
- 16.Java NIO Files 
- 17.Java NIO AsynchronousFileChannel 

>NIO 的三大组件 Selector \ Channel \ Buffer

- Channel
    - 
    - 既可以从Channel读数据也可以从中写数据
    - 可以异步的读写
    - 通道通过Buffer读写数据
    
    FileChannel 从文件中读写数据。
    
    DatagramChannel 能通过UDP读写网络中的数据。
    
    SocketChannel 能通过TCP读写网络中的数据。
    
    ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
    
- Buffer
    - 
    - 写入数据到Buffer
    - 调用flip()方法 重置position
    - 从Buffer中读取数据
    - 调用clear()方法或者compact()方法，clear清空buffer中的数据, compact清除已读的数据
    - capacity,position和limit ,capacity是Buffer的容量,position记录位置类似index,limit记录最大可读写数量
    - Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。
    - mark()与reset()方法,通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position。

- Scatter / Gather
    -
    - Java NIO开始支持scatter/gather，scatter/gather用于描述从Channel（译者注：Channel在中文经常翻译为通道）中读取或者写入到Channel的操作。
      分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
      聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。
      
      scatter / gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。
      
      ```
      ByteBuffer header = ByteBuffer.allocate(128);
      ByteBuffer body   = ByteBuffer.allocate(1024);
      ByteBuffer[] bufferArray = { header, body };
      channel.read(bufferArray);
      ```
- Selector
    -
    -    
