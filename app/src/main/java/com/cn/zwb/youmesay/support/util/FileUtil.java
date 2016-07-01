package com.cn.zwb.youmesay.support.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zhangweibo on 2015/11/23.
 */
public class FileUtil {


    public static final String ROOT_PATH= Environment.getExternalStorageDirectory().getPath()+"/material/";

    public static final String IMAGE_PATH=ROOT_PATH+"image/";

    public static final String LRC_PATH=ROOT_PATH+"lrc/";


    public static final String TEMP_IMAGE_PATH=IMAGE_PATH+"tempImage.jpg";


    public static final String CRASH_PATH=ROOT_PATH+"crash/";

    /**
     * sd卡是否可用
     * @return
     */

    public static boolean isSDCardAvailable(){

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd card 可用
            return true;
        }
        return false;

    }


    public static File getTempImageFile(){
        if (isSDCardAvailable()){

            File file=new File(IMAGE_PATH);
            if (!file.exists())
            {
                file.mkdirs();
            }

            file=new File(TEMP_IMAGE_PATH);
            if (!file.exists())
            {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        }
        return null;
    }


    public static boolean saveImageFile(String name){

        if (isSDCardAvailable()){

            saveFile(IMAGE_PATH, name);
            return true;
        }
        return  false;

    }



    public static void saveFile(String filePath, String name){

        File file =new File(filePath,name);
        if (file.exists())
        {
            file.mkdirs();
        }

    }

    /**
     *写入异常文件
     * @param name
     * @param b
     * @return
     */

    public static boolean wirteCrashToFile(String name, byte[]b){

        if (isSDCardAvailable())
        {
            writeMsgToFile(CRASH_PATH, name, b);
            return true;
        }
        return false;
    }


    /**
     * 文件是否存在
     * @return
     */
    public static boolean isFileExit(String rootPath,String path){
        File f=new File(rootPath+path);
        return f.exists()?true:false;
    }
    /**
     * 文件是否存在
     * @return
     */
    public static boolean isFileExit(String path){
        File f=new File(path);
        return (f.exists() && f.isDirectory())?true:false;
    }



    /**
     * 获取文件内容
     * @param path
     * @return
     */
    public static  String getFileContent(String rootPath,String path){
        StringBuffer sb= new StringBuffer("");

        try {
            // read file content from file

            FileReader reader = new FileReader(new File(rootPath,path));
            BufferedReader br = new BufferedReader(reader);

            String str = null;
            while((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
            br.close();
            reader.close();

        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return sb.toString();


}










    public static void   writeMsgToFile(String filePath, String name, byte[] b){

        File dir = new File(CRASH_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath + name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
