import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.awt.event.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
class StressTester extends JFrame {
    private String path = "";
    
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    private final static String clean_up_file   = "Python/cleanup.py";
    private final static String last_clean_file = "Python/lastClean.py";

    private JButton start                     = new JButton("Start");
    private JButton stop                      = new JButton("Stop");
    private JButton exit                      = new JButton("Exit");
    private JTextField filepath               = new JTextField(12);

    private static JProgressBar bar = new JProgressBar(0, 100);
    private static JPanel panel = new JPanel();
    
    private Thread thread1;

    private void runTest(String cmd, String arg, String type) {
        try {
            bar.setIndeterminate(false);
            go(cmd, arg, type + " Python/stress_by_write_test_rand.py"); // Write rand
            bar.setValue(15);
            bar.update(bar.getGraphics());
            
            go(cmd, arg, type + " Python/stress_by_write_test_sequ.py"); // Write seq
            bar.setValue(33);
            bar.update(bar.getGraphics());
            
            go(cmd, arg, type + " Python/stress_by_append_test_rand.py"); // Append rand
            bar.setValue(48);
            bar.update(bar.getGraphics());
            
            go(cmd, arg, type + " Python/stress_by_append_test_sequ.py"); // Append seq
            bar.setValue(66);
            bar.update(bar.getGraphics());
            
            go(cmd, arg, type + " Python/write_large_file.py"); // Large write
            bar.setValue(81);
            bar.update(bar.getGraphics());
            
            go(cmd, arg, type + " Python/read_test.py"); // Read
            bar.setValue(100);
            bar.update(bar.getGraphics());
            
            start.setEnabled(true);
            stop.setEnabled(false);
            exit.setEnabled(true);
            filepath.setEditable(true);
            bar.setValue(0);
            bar.setIndeterminate(true);

            try {
                results();
            } catch (FileNotFoundException f) {
                System.out.println("File is not found...");
            }

        } catch (Exception e) {
            System.out.println("This might take a minute...");
        }
    }

    private void runPython() {
        try {
            if (isWindows()) {
                if (go("cmd.exe", "/c", "python --version").contains("Python 3")) {
                    // python
                    runTest("cmd.exe", "/c", "python");
                } else {
                    // python3
                    runTest("cmd.exe", "/c", "python3");
                }
            } else if (isUnix()) {
                if (go("bash", "-c", "python --version").contains("Python 3")) {
                    // python
                    runTest("bash", "-c", "python");
                } else {
                    // python3
                    runTest("bash", "-c", "python3");
                }
            } else {
                System.out.println("Not supported operating system.");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while running Python 3 ...");
        }
    }

    private void stopPython() {
        try {
            if (isWindows()) {
                if (go("cmd.exe", "/c", "python --version").contains("Python 3")) {
                    try {
                        go("cmd.exe", "/c", "taskkill /IM python.exe /F && python " + clean_up_file);
                    } catch (IOException o) {
                        System.out.println("Failed to kill Python");
                    }
                } else {
                    try {
                        go("cmd.exe", "/c", "taskkill /IM python3.exe /F && python3 " + clean_up_file);
                    } catch (IOException o) {
                        System.out.println("Failed to kill Python");
                    }
                }
            } else if (isUnix()) {
                if (go("bash", "-c", "python --version").contains("Python 3")) {
                    try {
                        go("bash", "-c", "pkill python && python " + clean_up_file);
                    } catch (IOException o) {
                        System.out.println("Failed to kill Python");
                    }
                } else {
                    try {
                        go("bash", "-c", "pkill python3 && python3 " + clean_up_file);
                    } catch (IOException o) {
                        System.out.println("Failed to kill Python");
                    }
                }
            } else {
                System.out.println("Not supported operating system.");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while running Python 3 ...");
        }
    }

    private static void lastClean() {
        try {
            if (isWindows()) {
                if (go("cmd.exe", "/c", "python --version").contains("Python 3")) {
                    try {
                        go("cmd.exe", "/c", "python " + last_clean_file);
                    } catch (IOException o) {
                        System.out.println("Failed to remove readme.txt file");
                    }
                } else {
                    try {
                        go("cmd.exe", "/c", "python3 " + last_clean_file);
                    } catch (IOException o) {
                        System.out.println("Failed to remove readme.txt file");
                    }
                }
            } else if (isUnix()) {
                if (go("bash", "-c", "python --version").contains("Python 3")) {
                    try {
                        go("bash", "-c", "python " + last_clean_file);
                    } catch (IOException o) {
                        System.out.println("Failed to remove readme.txt file");
                    }
                } else {
                    try {
                        go("bash", "-c", "python3 " + last_clean_file);
                    } catch (IOException o) {
                        System.out.println("Failed to remove readme.txt file");
                    }
                }
            } else {
                System.out.println("Not supported operating system.");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while running Python 3 ...");
        }
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0 );
    }


    private static String go(String start, String arg, String cmd) throws IOException {
        String str = "";
        ProcessBuilder builder = new ProcessBuilder(
            start , arg, cmd);
        builder.redirectErrorStream(true);
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

    public static void results() throws FileNotFoundException {
        JFrame results = new JFrame("RESULTS");
        results.getContentPane().setLayout(new GridLayout(5,5));

        ImageIcon img = new ImageIcon(StressTester.class.getResource("Images/check.png"));
        results.setIconImage(img.getImage());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        results.pack();
        results.setSize(350,150);
        results.setLocation(dim.width / 2 - results.getSize().width / 2, 
            dim.height / 2 - results.getSize().height / 2);
        
        results.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        results.setResizable(false);

        String lines[] = new String[3];

        File file = new File("readme.txt");
        Scanner s = new Scanner(file);

        lines[0] = s.next();
        lines[1] = s.next();
        lines[2] = s.next();

        JButton button = new JButton("OK");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response){
                    results.dispose();
                    lastClean();
                } else {
                    // do nothing
                }
            }
        });

        JLabel one = new JLabel("WRITE: " + lines[0]);
        JLabel two = new JLabel("APPEND: " + lines[1]);
        JLabel thr = new JLabel("READ: " + lines[2]);

        results.add(one);
        results.add(two);
        results.add(thr);
        results.add(button);

        results.setVisible(true);
    }

    public StressTester() {
        this.setTitle("Stress Tester");
        this.getContentPane().setLayout(new FlowLayout());

        // set application icon
        ImageIcon img = new ImageIcon(StressTester.class.getResource("Images/WT.png"));
        this.setIconImage(img.getImage());

        stop.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(exit);

        JLabel filepath_label = new JLabel("Filepath to be tested on:");
        filepath_label.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel filePanel = new JPanel();
        filePanel.add(filepath_label);
        filePanel.add(filepath);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String string = filepath.getText().toString();

                Path file = Paths.get("filepath.txt");

                if (isWindows()) {
                    if (Files.exists(Paths.get(string)) && !(string.toLowerCase().contains("c:\\")) && (string.toLowerCase().contains(":\\"))) {
                        try {
                            // Disable things so that the process isn't screwed up...
                            start.setEnabled(false);
                            exit.setEnabled(false);
                            stop.setEnabled(true);
                            filepath.setEditable(false);
                            
                            // Save to file so that the Python program can know where to go...
                            List<String> lines = Arrays.asList(string);
                            Files.write(file, lines, Charset.forName("UTF-8"));

                            Thread thread1 = new Thread () {
                                public void run() {
                                    runPython();
                                }
                            };

                            try {
                                thread1.join();
                            } catch (Exception e) {
                                throw new RuntimeException("Thread interrupt");
                            }

                            thread1.start();

                        } catch (IOException e) {
                            System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                        }
                    } else {
                        invalidPath();
                    }
                } else if (isUnix()) {
                   if (Files.exists(Paths.get(string)) && (string.contains("/media")) || string.contains("/mnt")) {
                        try {
                            try {
                            // Disable things so that the process isn't screwed up...
                            start.setEnabled(false);
                            exit.setEnabled(false);
                            stop.setEnabled(true);
                            filepath.setEditable(false);
                            
                            // Save to file so that the Python program can know where to go...
                            List<String> lines = Arrays.asList(string);
                            Files.write(file, lines, Charset.forName("UTF-8"));

                            Thread thread1 = new Thread () {
                                public void run() {
                                    runPython();
                                }
                            };

                            try {
                                thread1.join();
                            } catch (Exception e) {
                                throw new RuntimeException("Thread interrupt");
                            }

                            thread1.start();

                        } catch (IOException e) {
                            System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                        }  System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                    } else {
                        invalidPath();
                    } 
                }
            }
        });

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response) {
                    stopPython();
                    try {
                        thread1.interrupt();
                    } catch (Exception e) {
                        System.out.println("This may take a while...");
                    }
                    bar.setValue(0);
                    bar.setIndeterminate(true);
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    filepath.setEditable(true);
                    exit.setEnabled(true);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response) {
                    stopPython();
                    try {
                        thread1.interrupt();
                    } catch (Exception e) {
                        System.out.println("Please wait while I clean up...");
                    }
                    setVisible(false);
                    dispose();
                }
            }
        });

        add(BorderLayout.NORTH, buttonPanel);
        add(BorderLayout.SOUTH, filePanel);
        
        bar.setValue(0);
        bar.setIndeterminate(true);
        bar.setStringPainted(true);
        add(panel.add(bar));
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
