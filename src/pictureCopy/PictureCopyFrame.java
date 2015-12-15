/**
 * 
 */
package pictureCopy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


/**
 * @author FLuo
 *
 */
public class PictureCopyFrame extends JFrame {
    
    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 550;
    public static final int STEPS = 1000;
    public static final int DELAY = 10;
    
    private JLabel _srcLabel = new JLabel("源文件夹：");
    private JTextField _txtSrcPath = new JTextField(40);
    private JButton _srcPathButton = new JButton("   浏览     ");
    private JLabel _destLabel = new JLabel("目的文件夹：");
    private JTextField _txtDestPath = new JTextField(40);
    private JButton _destPathButton = new JButton("   浏览     ");
    private JTextArea _txtLog  = new JTextArea(20, 20);
    private JButton _copyButton  = new JButton("开始拷贝");

    public PictureCopyFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("拷贝照片");
        
        _txtSrcPath.setText("D:\\pCopyTest\\1");
        _txtDestPath.setText("D:\\pCopyTest\\2");
        
        FormLayout layout = new FormLayout(
                "4dlu,left:p:grow,2dlu,left:p:grow,2dlu,left:p:grow, 2dlu",   // columns
                "8dlu,p,2dlu,p:grow,2dlu,p:grow,2dlu,p:grow,2dlu"); // rows

        _txtSrcPath.setEditable(false);
        _txtDestPath.setEditable(false);
        _txtLog.setEditable(false);
        
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        builder.add(_srcLabel, cc.xy(2, 2));
        builder.add(_txtSrcPath, cc.xy(4, 2));
        builder.add(_srcPathButton, cc.xy(6, 2));
        
        builder.add(_destLabel, cc.xy(2, 4));
        builder.add(_txtDestPath, cc.xy(4, 4));
        builder.add(_destPathButton, cc.xy(6, 4));
        
        builder.add(_copyButton, cc.xy(6, 6));
        
        JScrollPane scrollPane = new JScrollPane(_txtLog);
        builder.add(scrollPane, cc.xyw(2, 8, 5));
        add(builder.getPanel());
        
        _srcPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolder(_txtSrcPath);
            }
        });
        _destPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolder(_txtDestPath);
            }
        });
        _copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                copyFiles();
            }
        });
        

    }
    
    private void selectFolder(JTextField field) {
        JFileChooser folderPath = new JFileChooser();
        folderPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = folderPath.showOpenDialog(field);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = folderPath.getSelectedFile().getPath();
            field.setText(path);
        }
    }
    
    private void copyFiles() {
        String source = _txtSrcPath.getText();
        String dest = _txtDestPath.getText();
        CopyFiles copyFiles = new CopyFiles(source, dest, _txtLog);
        Thread thread=new Thread(copyFiles);
        thread.start();
    }
}
