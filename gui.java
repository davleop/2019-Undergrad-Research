// Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.awt.event.*;

class gui extends JFrame {
    private String path = "";

    public static boolean popup() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO QUIT THIS OPERATION?", "WARNING",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return false;
        } else if (response == JOptionPane.YES_OPTION) {
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        } else {
            return false;
        }
    }

    public gui() {
        this.setTitle("Stress Tester");
        this.getContentPane().setLayout(new FlowLayout());

        // set application icon
        ImageIcon img = new ImageIcon("pic.png");
        this.setIconImage(img.getImage());

        JButton start = new JButton("Start"); // TODO(David): Make sure the file path is set
                                              //              correctly.
        JButton stop = new JButton("Stop");   // TODO(David): Make a pop up window for
                                              //              "Are you sure you want to stop?"

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(stop);

        JLabel filepath_label = new JLabel("Filepath to be tested on:");
        filepath_label.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField filepath = new JTextField(12);

        JPanel filePanel = new JPanel();
        filePanel.add(filepath_label);
        filePanel.add(filepath);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String string = filepath.getText().toString();

                List<String> lines = Arrays.asList(string);
                Path file = Paths.get("filepath.txt");
                
                try {
                    // Disable things so that the process isn't screwed up...
                    start.setEnabled(false);
                    filepath.setEditable(false);
                    
                    // Save to file so that the Python program can know where to go...
                    Files.write(file, lines, Charset.forName("UTF-8"));
                } catch (IOException e) {
                    System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                }
            }
        });

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response) {
                    start.setEnabled(true);
                    filepath.setEditable(true);
                }
            }
        });

        add(BorderLayout.CENTER, buttonPanel);
        add(BorderLayout.SOUTH, filePanel);
    }

    private static void createGUI() {
        JFrame frame = new gui();

        frame.pack();
        frame.setSize(350,150);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}