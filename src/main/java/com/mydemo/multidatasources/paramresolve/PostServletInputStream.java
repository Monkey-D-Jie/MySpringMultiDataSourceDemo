package com.mydemo.multidatasources.paramresolve;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 替换本身的输入流对象
 */
public class PostServletInputStream  extends ServletInputStream {
    private InputStream inputStream;
    //解析json之后的文本
    private String body ;

    public PostServletInputStream(String body) throws IOException {
        this.body=body;
        inputStream = null;
    }


    private InputStream acquireInputStream() throws IOException {
        if(inputStream == null) {
            //通过解析之后传入的文本生成inputStream以便后面controller调用
            inputStream = new ByteArrayInputStream(body.getBytes("UTF-8"));
        }
        return inputStream;
    }


    @Override
    public void close() throws IOException {
        try {
            if(inputStream != null) {
                inputStream.close();
            }
        }
        catch(IOException e) {
            throw e;
        }
        finally {
            inputStream = null;
        }
    }


    @Override
    public int read() throws IOException {
        return acquireInputStream().read();
    }


    @Override
    public boolean markSupported() {
        return false;
    }


    @Override
    public synchronized void mark(int i) {
        throw new UnsupportedOperationException("mark not supported");
    }


    @Override
    public synchronized void reset() throws IOException {
        throw new IOException(new UnsupportedOperationException("reset not supported"));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }
}
