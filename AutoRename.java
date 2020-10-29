import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutoRename extends JFrame {
    private static JTextArea log = new JTextArea(5,20);
    private static JFileChooser chooseDirectory = new JFileChooser();

    public AutoRename() {
        super("Renomeador de preguiçoso");

        setPreferredSize(new Dimension(500, 600));
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        
        p1.setLayout(new BorderLayout());
        p2.setLayout(new BorderLayout());
        
        setupFileChooser();

        log.setEditable(false);

        JButton renomear = new JButton("Renomear");

        renomear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                filaDeAlteracao(getPath());
                
            }

        });

        p1.add(new JLabel("Insira diretótio (double backslash)"), BorderLayout.NORTH);
        p1.add(chooseDirectory, BorderLayout.CENTER);
        p2.add(log, BorderLayout.NORTH);
        p2.add(renomear, BorderLayout.SOUTH);

        getContentPane().add(p1);
        getContentPane().add(p2);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void setupFileChooser(){
        chooseDirectory.setControlButtonsAreShown(false);
        chooseDirectory.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter audio = new FileNameExtensionFilter("Faixas de áudio", "mp3", "flac", "wma","wav", "aac" ); 
        chooseDirectory.addChoosableFileFilter(audio);
    }

    public String getPath(){
        return chooseDirectory.getCurrentDirectory().getAbsolutePath();
    }

    public void filaDeAlteracao(String path) {
        File folder = new File(path);
        File[] listOfFiles  = new File(folder.listFiles());

        if(!(listOfFiles.length == 0)){
            for (File file : listOfFiles) {
                if ((file.isFile() && file.getName().contains("mp3")) && (file.getName().contains("-") || file.getName().contains("."))) {
                    renomeador(file);
                }
            }
        }else{
            logUpdate("Não há arquivos.");
        }
    }

    public static void logUpdate(String update){
        log.setText(log.getText() + "\n" + update);
    }

    public void renomeador(File file) {
        logUpdate(file.getName());
        String nome = "" + file.getName().substring(3);
        File file2 = new File(getPath() + "\\" + nome);
        file.renameTo(file2);
    }

    public static void main(String[] args) {
        new AutoRename();
    }
}