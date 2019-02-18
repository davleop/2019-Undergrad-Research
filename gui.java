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

    private static void runWin_P3() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "python3 test.py");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {break;}
            System.out.println(line);
        }
    }

    private static void runLinux_P3() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
            "bash", "-c", "python3 test.py");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {break;}
            System.out.println(line);
        }
    }

    private static void runWin_P() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "python test.py");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {break;}
            System.out.println(line);
        }
    }

    private static void runLinux_P() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
            "bash", "-c", "python test.py");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {break;}
            System.out.println(line);
        }
    }

    private static boolean popup() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO DO THIS OPERATION?"
            , "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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

    private static void invalidPath() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        JOptionPane.showMessageDialog(null, "Invalid filepath. Try again...", "Oops",
         JOptionPane.ERROR_MESSAGE);
    }

    public gui() {
        this.setTitle("Stress Tester");
        this.getContentPane().setLayout(new FlowLayout());

        // set application icon
        ImageIcon img = new ImageIcon("pic.png");
        this.setIconImage(img.getImage());

        JButton start = new JButton("Start");
        JButton stop  = new JButton("Stop");
        JButton exit  = new JButton("Exit");

        stop.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(exit);

        JLabel filepath_label = new JLabel("Filepath to be tested on:");
        filepath_label.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField filepath = new JTextField(12);

        JPanel filePanel = new JPanel();
        filePanel.add(filepath_label);
        filePanel.add(filepath);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String string = filepath.getText().toString();

                Path file = Paths.get("filepath.txt");

                if (Files.exists(Paths.get(string))) {
                    try {
                        // Disable things so that the process isn't screwed up...
                        start.setEnabled(false);
                        exit.setEnabled(false);
                        stop.setEnabled(true);
                        filepath.setEditable(false);
                        
                        // Save to file so that the Python program can know where to go...
                        List<String> lines = Arrays.asList(string);
                        Files.write(file, lines, Charset.forName("UTF-8"));

                        try {
                            runWin_P3();
                        } catch (Exception e) {
                            try {
                                runWin_P();
                            } catch (Exception x) {
                                try {
                                    runLinux_P3();
                                } catch (Exception c) {
                                    try {
                                        runLinux_P();
                                    } catch (Exception p) {
                                        System.out.println("Something went wrong...");
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                    }
                } else {
                    invalidPath();
                }
            }
        });

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response) {
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    filepath.setEditable(true);
                    exit.setEnabled(true);
                    // TODO(David): add kill process and clean up data...
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response) {
                    // TODO(David): add kill process and clean up data...
                    setVisible(false);
                    dispose();
                }
            }
        });

        add(BorderLayout.CENTER, buttonPanel);
        add(BorderLayout.SOUTH, filePanel);
    }

    private static void createGUI() {
        JFrame frame  = new gui();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.pack();
        frame.setSize(350,150);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, 
            dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}