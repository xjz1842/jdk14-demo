package com.jdk14.demo.dynamic.jdk;

import java.io.*;

/**
 * @author xjz
 */
public class MyClassLoader extends ClassLoader{

    private File dir;

    private String proxyClassPackage;

    public String getProxyClassPackage(){
        return proxyClassPackage;
    }

    public File getDir(){
        return dir;
    }

    public MyClassLoader(String path, String proxyClassPackage) {
        this.dir = new File(path);
        this.proxyClassPackage = proxyClassPackage;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if(dir != null){
            File classFile = new File(dir,name+".class");

            if(classFile.exists()){
                try{
                 byte[] classBytes =  copyToByteArray(classFile);

                 return defineClass(proxyClassPackage+"."+name,classBytes,0,classBytes.length);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return super.findClass(name);
    }

    private byte[] copyToByteArray(File file)throws IOException{
        FileInputStream fileInputStream  = new FileInputStream(file);
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while((line=bufferedInputStream.readLine()) != null){
            stringBuilder.append(line);
        }

        bufferedInputStream.close();
        return stringBuilder.toString().getBytes();
    }
}
