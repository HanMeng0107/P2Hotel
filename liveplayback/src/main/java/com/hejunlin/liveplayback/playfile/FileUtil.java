package com.hejunlin.liveplayback.playfile;

import java.io.File;

import android.os.Environment;
import android.util.Log;

/**
 * ���ݺ�׺�������ļ�����
 *
 * @author zyq
 */
public class FileUtil {
    private static String type = "*/*";
    public static String ip = "172.16.152.15";
    public static String deviceDMRUDN = "0";
    public static String deviceDMSUDN = "0";
    public static int port = 2222;


    public static String getFileType(String uri) {
        if (uri == null) {
            return type;
        }

        if (uri.endsWith(".mp3")) {
            return "audio/mpeg";
        }

        if (uri.endsWith(".mp4")) {
            return "video/mp4";
        }

        return type;
    }

    public static String getDeviceDMRUDN() {
        return deviceDMRUDN;
    }

    public static String getDeviceDMSUDN() {
        return deviceDMSUDN;
    }

    /**
     * @param path �ļ���
     * @return �����ɹ�����true������false��
     */
    public static boolean mkdir(String name) {
        boolean bool = false;

        boolean state = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (state) {
            File f = Environment.getExternalStorageDirectory();
            String path = f.getPath();
            String dir = path + "/" + name + "/";
            File file = new File(dir);
            if (!file.exists()) {
                bool = file.mkdir();
            } else {
                Log.i("", "-----------" + dir + "�Ѵ���----------------");
            }

        } else {
            Log.e("", "-----------------�ⲿ�洢��������----------------");
        }


        return bool;
    }


}
