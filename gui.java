//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
class gui {
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Stress Tester");
        ImageIcon img = new ImageIcon("pic.jpg");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Help");
        mb.add(m1);

        //Creating the panel at bottom and adding components
        JPanel panel1 = new JPanel(); // the panel is not visible in output
        JButton start = new JButton("Start"); // TODO(David): Make sure the file path is set
                                              //              correctly.

        JButton stop = new JButton("Stop");   // TODO(David): Make a pop up window for
                                              //              "Are you sure you want to stop?"
        panel1.add(start);
        panel1.add(stop);

        // Create label for text field...
        JLabel filepath_label = new JLabel("Filepath to be tested on:");
        filepath_label.setHorizontalAlignment(SwingConstants.LEFT);

        // Create a text box to put in for the file path
        JTextField filepath = new JTextField(12);

        JPanel panel2 = new JPanel();

        // Add more panels
        panel2.add(filepath_label);
        panel2.add(filepath);

        //Adding Components to the frame.
        frame.add(BorderLayout.SOUTH, panel1);
        frame.add(BorderLayout.CENTER, panel2);
        frame.add(BorderLayout.NORTH, mb);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}