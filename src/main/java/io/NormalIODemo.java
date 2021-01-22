/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/3/16 21:34
 */
package io;

import java.io.*;


public class NormalIODemo {
    public static final String originalFilePath = "E:\\Programming Folder\\inter.txt";
    public static void main(String[] args) throws IOException {
        testNormalIO();

    }

    /**
     * 普通读写文件方式。
     * @throws IOException
     */
    public static void testNormalIO() throws IOException {

        File fileForRead = new File(originalFilePath);
        if (!fileForRead.exists()) {
            System.out.println("File for reading not exists");
            return;
        }
        InputStream inputStream = new FileInputStream(fileForRead);
        String filePathForWrite = "E:\\Programming Folder\\inter2.docx";
        File fileForWrite = new File(filePathForWrite);
        OutputStream outputStream = new FileOutputStream(fileForWrite);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = inputStream.read(buffer)) != -1) {
            //  count 是每次从inputStream中读取到buffer数组中的字节数
            // 将buffer数组下标为0 到count-1的字节写入到outputStream，避免最后一次读取时,buffer数组中有脏数据
            outputStream.write(buffer, 0, count);
        }

        System.out.println("Finish to write ...");
        outputStream.close();
        inputStream.close();
    }
}
