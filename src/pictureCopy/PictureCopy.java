/**
 * 
 */
package pictureCopy;

import java.awt.EventQueue;
import javax.swing.JFrame;

public final class PictureCopy {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PictureCopyFrame frame = new PictureCopyFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
