package pictureCopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextArea;

public class CopyFiles implements Runnable {
    private File        _srcFolder;
    private File        _destFolder;
    private JTextArea   _log;
    
    public CopyFiles(String srcPath, String destPath, JTextArea log) {
        _log = log;
        _srcFolder = new File(srcPath);
        _destFolder = new File(destPath);
        if (!_srcFolder.exists()) {
            _log.append("Դ�ļ�Ŀ¼\"" + srcPath + "\"������!");
            return;
        }
        if (!_srcFolder.isDirectory()) {
            _log.append("Դ�ļ�Ŀ¼\"" + srcPath + "\"�����ļ���!");
            return;
        }
        if (!_destFolder.exists()) {
            _destFolder.mkdir();
            _log.append("������Ŀ¼: " + destPath);
        }
    }

    @Override
    public void run() {
        copyFiles(_srcFolder, _destFolder);

    }

    private void copyFiles(File srcFolder, File destFolder) {
        File[] file = srcFolder.listFiles();

        // ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                long time = file[i].lastModified();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr = sdf.format(calendar.getTime());
                File newDestFolder = new File(destFolder, dateStr); 
                if (!newDestFolder.exists()) {
                    newDestFolder.mkdir();
                }
                File newDestFile = new File(newDestFolder, file[i].getName());
                if (newDestFile.exists()) {
                    _log.append("Existed: \"" + file[i].getPath() + "\" has already existed\r\n");
                    continue;
                }
                try {
                    copyFile(file[i], newDestFile);
                } catch (IOException e) {
                    _log.append("Failed: Copy File \"" + file[i].getPath() + "\" to \"" + newDestFile + "\" failed!\r\n");
                    _log.append(e.getMessage() + "\r\n");
                    break;
                }
                _log.append("OK: Copy File \"" + file[i].getPath() + "\" to \"" + newDestFile + "\" OK!\r\n");
            }
            if (file[i].isDirectory()) {
                // ����Ŀ¼
                copyFiles(file[i], destFolder);
            }
        }
    }
    
    private void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // �½��ļ����������������л���
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // �½��ļ���������������л���
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // ��������
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // ˢ�´˻���������
            outBuff.flush();
        } finally {
            // �ر���
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
