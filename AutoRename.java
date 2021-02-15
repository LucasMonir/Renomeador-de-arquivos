import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class AutoRename extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JTextArea log = new JTextArea(8, 20);
    private static JFileChooser chooseDirectory = new JFileChooser();

    private AutoRename() {
        super("Renomeador de músicas");

        setPreferredSize(new Dimension(500, 600));
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.setLayout(new BorderLayout());
        p2.setLayout(new BorderLayout());
        p3.setLayout(new GridLayout(1, 2));

        p1.setBackground(new Color(0, 69, 123));
        p2.setBackground(new Color(0, 69, 123));
        p3.setBackground(new Color(0, 69, 123));

        chooseDirectory.setForeground(new Color(218, 225, 233));
        setupFileChooser();

        log.setEditable(false);
        log.setBackground(new Color(218, 225, 233));

        JScrollPane scrollPane = new JScrollPane(log);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton adicionar = new JButton("Adicionar Enumeração");
        JButton remover = new JButton("Remover Enumeração");

        adicionar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                filaDeAlteracao(getPath(), true);

            }

        });

        remover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                filaDeAlteracao(getPath(), false);

            }

        });

        p1.add(chooseDirectory, BorderLayout.CENTER);
        p2.add(scrollPane, BorderLayout.NORTH);
        p3.add(adicionar);
        p3.add(remover);
        p2.add(p3, BorderLayout.SOUTH);

        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static void setupFileChooser() {
        chooseDirectory.setControlButtonsAreShown(false);
        FileNameExtensionFilter audio = new FileNameExtensionFilter("Faixas de áudio", "mp3", "flac", "wma", "wav",
                "aac");
        chooseDirectory.addChoosableFileFilter(audio);
    }

    private String getPath() {
        return chooseDirectory.getCurrentDirectory().getAbsolutePath();
    }

    private void filaDeAlteracao(String path, boolean adicionar) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        int iterator = 1;

        for (File file : listOfFiles) {
            if (!adicionar) {
                if ((file.isFile() && file.getName().contains("mp3"))
                        && (file.getName().contains("-") || file.getName().contains("."))) {
                    remover(file);
                }
            } else {
                if (file.isFile()) {
                    adicionar(file, iterator);
                    iterator++;
                }
            }
        }
    }

    private static void logUpdate(String update) {
        log.setText(log.getText() + "\n" + update);
    }

    private void remover(File file) {
        logUpdate(file.getName());
        String nome = "" + file.getName().substring(4);
        File file2 = new File(getPath() + "\\" + nome);
        file.renameTo(file2);
    }

    private void adicionar(File file, int iterator) {
        logUpdate(file.getName());
        String nome = iterator + " - " + file.getName();
        File file2 = new File(getPath() + "\\" + nome);
        file.renameTo(file2);
    }

    public static void main(String[] args) {
        new AutoRename();
    }
}
