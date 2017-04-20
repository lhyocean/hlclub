package com.huailai.club.huailaiclub.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;



import org.haitao.common.utils.AppLog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtils {

    public static String SDPATH = Environment.getExternalStorageDirectory()
            + "/beauty/";



    // 判断sd卡是否可用
    public static File getAppExternalStorageFile() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(Environment.getExternalStorageDirectory(), "huailaiclub");
        }
        return null;

    }

    public static String saveBitmapCatche(Bitmap bm, String picName,Context context) {
        File cacheDir =null;
        File f =null;
        try {

             cacheDir = context.getCacheDir();

             f = new File(cacheDir.getPath(), picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();


    }



    public static String saveBitmap(Bitmap bm, String picName) {
        File f =null;
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
         f = new File(SDPATH, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    public static void delFile(String fileName) {
        File file = new File(SDPATH + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir() {
        File dir = new File(SDPATH);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete();
            else if (file.isDirectory())
                deleteDir();
        }
        dir.delete();
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    /**
     * 持久化对象
     *
     * @param context
     * @param obj
     * @param fileName
     */
    public static void writeObj(Context context, Object obj, String fileName) {

        File file = new File(context.getFilesDir(), fileName);

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取持久化对象
     *
     * @param fileName
     * @return
     */
    public static Object readObj(Context context, String fileName) {
        ObjectInputStream ois = null;

        try {
            File file = new File(context.getFilesDir(), fileName);
            if (!file.exists())
                return null;
            ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }


    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            if (!file.exists()) {
                file.exists();
            }
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }


    public static String getAppPath() {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File sdCardDir = Environment.getExternalStorageDirectory();
            String fileName = sdCardDir.getAbsolutePath() + File.separator + "beauty";
            File f = new File(fileName);
            if (!f.exists()) {
                f.mkdirs();
                return f.getPath();
            } else {
                return f.getPath();
            }

        } else {
            return "";
        }
    }


    public static File getImageFile(Context context) {
        File cacheDir = context.getCacheDir();
        return new File(cacheDir.getAbsolutePath() + "/image/");
    }


    public static boolean makeDir(String path) {
        if (!hasSDCard()) {
            return false;
        } else {
            File file = new File(path);
            if (!file.exists()) {
                boolean rs = file.mkdirs();
                AppLog.e("makeDir==" + path + rs);
            }

            return true;
        }
    }

    public static boolean hasSDCard() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

}
