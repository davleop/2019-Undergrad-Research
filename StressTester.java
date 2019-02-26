import javax.swing.*;
import java.awt.*;
import java.io.*;
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

class StressTester extends JFrame implements ActionListener {
    private String path = "";
    private static String OS = System.getProperty("os.name").toLowerCase();
    private final static String file_to_run = "Python/go.py";
    private int timeLeft = 3600000;
    private JLabel label = new JLabel("");
    private Timer timer = new Timer(1000, this);
    private JButton start = new JButton("Start");
    private JButton stop  = new JButton("Stop");
    private JButton exit  = new JButton("Exit");
    private JTextField filepath = new JTextField(12);
    private Thread thread2;

    public void actionPerformed(ActionEvent e) {
        timeLeft -= 1000;
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        label.setText(df.format(timeLeft));
        if (timeLeft <= 0){
            start.setEnabled(true);
            stop.setEnabled(false);
            exit.setEnabled(true);
            filepath.setEditable(true);
            timer.stop();
            try {
                results();
            } catch (FileNotFoundException f) {
                System.out.println("File is not found...");
            }
        }
    }

    private void runPython() {
        try {
            if (isWindows()) {
                if (go("cmd.exe", "/c", "python --version").contains("Python 3")) {
                    thread2 = new Thread() {
                        public void run() {
                            try {
                                go("cmd.exe", "/c", "python " + file_to_run);
                            } catch (IOException o) {
                                System.out.println("Failed to execute Python");
                            }
                        }
                    };
                } else {
                    thread2 = new Thread() {
                        public void run() {
                            try {
                                go("cmd.exe", "/c", "python3 " + file_to_run);
                            } catch (IOException o) {
                                System.out.println("Failed to execute Python");
                            }
                        }
                    };
                }
            } else if (isUnix()) {
                if (go("bash", "-c", "python --version").contains("Python 3")) {
                    thread2 = new Thread() {
                        public void run() {
                            try {
                                go("bash", "-c", "python " + file_to_run);
                            } catch (IOException o) {
                                System.out.println("Failed to execute Python");
                            }
                        }
                    };
                } else {
                    thread2 = new Thread() {
                        public void run() {
                            try {
                                go("bash", "-c", "python3 " + file_to_run);
                            } catch (IOException o) {
                                System.out.println("Failed to execute Python");
                            }
                        }
                    };
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
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }


    private static String go(String start, String arg, String cmd) throws IOException {
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

    public static void results() throws FileNotFoundException {
        JFrame yay = new JFrame("RESULTS");
        yay.getContentPane().setLayout(new GridLayout(5,5));

        ImageIcon img = new ImageIcon("check.png");
        yay.setIconImage(img.getImage());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        yay.pack();
        yay.setSize(350,150);
        yay.setLocation(dim.width / 2 - yay.getSize().width / 2, 
            dim.height / 2 - yay.getSize().height / 2);
        
        yay.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        yay.setResizable(false);

        String lines[] = new String[5];

        File file = new File("readme.txt");
        Scanner s = new Scanner(file);

        lines[0] = s.next();
        lines[1] = s.next();
        lines[2] = s.next();
        lines[3] = s.next();
        lines[4] = s.next();

        JButton button = new JButton("OK");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean response = popup();
                if (response){
                    yay.dispose();
                } else {
                    // do nothing
                }
            }
        });

        JLabel one = new JLabel("DAY: " + lines[0]);
        JLabel two = new JLabel("WRITE: " + lines[1]);
        JLabel thr = new JLabel("APPEND: " + lines[2]);
        JLabel fou = new JLabel("LARGE WRITE: " + lines[3]);
        JLabel fiv = new JLabel("READ: " + lines[4]);

        yay.add(one);
        yay.add(two);
        yay.add(thr);
        yay.add(fou);
        yay.add(fiv);
        yay.add(button);

        yay.setVisible(true);
    }

    public StressTester() {
        this.setTitle("Stress Tester");
        this.getContentPane().setLayout(new FlowLayout());

        // set application icon
        ImageIcon img = new ImageIcon("WT.png");
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

                            Thread thread1 = new Thread() {
                                public void run() {
                                    timer.setInitialDelay(10000); // 10 Second delay to ensure threads start
                                    timer.start();
                                }
                            };

                            runPython();

                            try {
                                thread2.join();
                                thread1.join();
                            } catch (Exception ex) {
                                System.out.println("Threads failed to join...");
                            }
                            thread1.start();
                            thread2.start();

                        } catch (IOException e) {
                            System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                        }
                    } else {
                        invalidPath();
                    }
                } else if (isUnix()) {
                   if (Files.exists(Paths.get(string)) && (string.contains("/media")) || string.contains("/mnt")) {
                        try {
                            // Disable things so that the process isn't screwed up...
                            start.setEnabled(false);
                            exit.setEnabled(false);
                            stop.setEnabled(true);
                            filepath.setEditable(false);
                            
                            // Save to file so that the Python program can know where to go...
                            List<String> lines = Arrays.asList(string);
                            Files.write(file, lines, Charset.forName("UTF-8"));

                            Thread thread1 = new Thread() {
                                public void run() {
                                    timer.setInitialDelay(10000); // 10 Second delay to ensure threads start
                                    timer.start();
                                }
                            };

                            runPython();

                            try {
                                thread2.join();
                                thread1.join();
                            } catch (Exception ex) {
                                System.out.println("Threads failed to join...");
                            }
                            thread1.start();
                            thread2.start();

                        } catch (IOException e) {
                            System.out.println("INVALID WRITE --> TRY AGAIN LATER");
                        }
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
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    filepath.setEditable(true);
                    exit.setEnabled(true);
                    timer.stop();
                    timeLeft = 30000;
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

        add(BorderLayout.NORTH, buttonPanel);
        add(BorderLayout.CENTER, label);
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