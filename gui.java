//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
class gui {
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Easy One Click Stress Test");
        ImageIcon img = new ImageIcon("pic.jpg");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Help");
        mb.add(m1);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton start = new JButton("Start"); // TODO(David): Make sure the file path is set
                                              //              correctly.

        JButton stop = new JButton("Stop");   // TODO(David): Make a pop up window for
                                              //              "Are you sure you want to stop?"
        panel.add(start);
        panel.add(stop);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}