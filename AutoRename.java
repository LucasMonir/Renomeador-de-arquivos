import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class AutoRename extends JFrame {

    private String opcao = "";

    public AutoRename() {
        super("Renomeador de preguiçoso");

        setPreferredSize(new Dimension(400, 200));
        setResizable(false);
        setLayout(new GridLayout(0, 1));

        JTextField path = new JTextField();

        JButton renomear = new JButton("Renomear");

        renomear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                filaDeAlteracao(path.getText());

            }

        });

        getContentPane().add(new JLabel("Insira diretótio (padrão \\)"));
        getContentPane().add(path);
        getContentPane().add(renomear);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void filaDeAlteracao(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if ((file.isFile() && file.getName().contains("mp3")) && (file.getName().contains("-") || file.getName().contains("."))) {
                renomeador(file);
            }
        }
    }

    public void renomeador(File file) {
        System.out.println(file.getName());
        String nome = "" + file.getName().substring(2);
        File file2 = new File(nome);
        file.renameTo(file2);
    }

    public static void main(String[] args) {
        new AutoRename();
    }
}