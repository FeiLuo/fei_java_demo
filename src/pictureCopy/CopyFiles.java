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
            _log.append("源文件目录\"" + srcPath + "\"不存在!");
            return;
        }
        if (!_srcFolder.isDirectory()) {
            _log.append("源文件目录\"" + srcPath + "\"不是文件夹!");
            return;
        }
        if (!_destFolder.exists()) {
            _destFolder.mkdir();
            _log.append("创建根目录: " + destPath);
        }
    }

    @Override
    public void run() {
        copyFiles(_srcFolder, _destFolder);

    }

    private void copyFiles(File srcFolder, File destFolder) {
        File[] file = srcFolder.listFiles();

        // 获取源文件夹当前下的文件或目录
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
                // 复制目录
                copyFiles(file[i], destFolder);
            }
        }
    }
    
    private void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
