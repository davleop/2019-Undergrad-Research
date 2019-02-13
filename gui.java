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

class gui extends JFrame implements ActionListener {
    private String path = "";

    public gui() {
        this.setTitle("Stress Tester");
        this.getContentPane().setLayout(new FlowLayout());

        // set application icon
        ImageIcon img = new ImageIcon("pic.png");
        this.setIconImage(img.getImage());

        // Help menu bar (how to use)
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Help");
        mb.add(m1);
        
        JButton start = new JButton("Start"); // TODO(David): Make sure the file path is set
                                              //              correctly.
        JButton stop = new JButton("Stop");   // TODO(David): Make a pop up window for
                                              //              "Are you sure you want to stop?"
        start.addActionListener(this);
        stop.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(stop);

        add(BorderLayout.NORTH,mb);
        add(BorderLayout.CENTER,buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Start")) {
            List<String> lines = Arrays.asList(this.path);
            Path file = Paths.get("filepath.txt");
            try {
                Files.write(file, lines, Charset.forName("UTF-8"));
            } catch (IOException e) {
                System.out.println("What is file?");
            }
        }
    }

    private static void createGUI() {
        JFrame frame = new gui();

        frame.pack();
        frame.setSize(350,150);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
/*
        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Help");
        mb.add(m1);

        panel1.add(start);
        panel1.add(stop);

        // Create label for text field...
        JLabel filepath_label = new JLabel("Filepath to be tested on:");
        filepath_label.setHorizontalAlignment(SwingConstants.LEFT);

        // Create a text box to put in for the file path
        JTextField filepath = new JTextField(12);

        // New panel for easily controlling other options
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

        this.path = filepath.getText();
        start.addActionListener(this);
*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}