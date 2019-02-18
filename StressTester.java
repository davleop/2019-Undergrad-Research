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

class StressTester extends JFrame {
    private String path = "";
    private static String OS = System.getProperty("os.name").toLowerCase();
    private final static String file_to_run = "test.py";

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    private static String run(String start, String arg, String cmd) throws IOException {
        String str = "";
        ProcessBuilder builder = new ProcessBuilder(
            start , arg, cmd);
        builder.redirectErrorStream(false);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {break;}
            str += line;
        }
        return str;
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

    public StressTester() {
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

                        // Run Python3 please...
                        if (isWindows()) {
                            if (run("cmd.exe", "/c", "python --version").contains("Python 3"))
                                System.out.println(run("cmd.exe", "/c", "python " + file_to_run));
                            else
                                System.out.println(run("cmd.exe", "/c", "python3 " + file_to_run));
                        } else if (isUnix()) {
                            if (run("bash", "-c", "python --version").contains("Python 3"))
                                System.out.println(run("bash", "-c", "python " + file_to_run));
                            else
                                System.out.println(run("bash", "-c", "python3 " + file_to_run));
                        } else {
                            System.out.println("Not supported operating system.");
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
        JFrame frame  = new StressTester();
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