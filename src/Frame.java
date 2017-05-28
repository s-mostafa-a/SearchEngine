import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

/**
 * Created by ahmadi on 12/12/16.
 */
public class Frame extends JFrame {
    JButton Build, Reset, Help, Exit, Brows;
    JTextField path, command;
    JTextArea info;
    JLabel enterCom;
    JRadioButton BST, TST, Trie;
    JPanel center, down, downer, downerer, up;
    Reader reader;
    public Frame() {
        super("frame");
        frameInitialize();
    }
    public void frameInitialize(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setVisible(true);
        setDefaultCloseOperation(ICONIFIED);
        setResizable(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //up
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 2.0;
        gbc.fill = GridBagConstraints.BOTH;
        up = new JPanel();
        up.setBackground(new Color(162,	181, 205));
        add(up, gbc);
        //center
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 6.0;
        gbc.fill = GridBagConstraints.BOTH;
        center = new JPanel();
        add(center, gbc);
        center.setBackground(new Color(162,	181, 205));
        //jp
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel jp = new JPanel();
        jp.setBackground(new Color(162,	181, 205));
        add(jp, gbc);
        //down
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        down = new JPanel();
        down.setBackground(new Color(162,	181, 205));
        add(down, gbc);
        //downer
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        downer = new JPanel();
        add(downer, gbc);
        downer.setBackground(new Color(162,	181, 205));
        //downerer
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        downerer = new JPanel();
        downerer.setBackground(new Color(162,	181, 205));
        add(downerer, gbc);
        //////////////////////////////////////////////////////init
        path = new JTextField();
        path.setColumns(41);
        info = new JTextArea();
        info.setColumns(41);
        info.setRows(13);
        info.setEditable(false);
        final JScrollPane infoS = new JScrollPane(info);
        center.setBorder(new EtchedBorder());
        Brows = new JButton("Go to this folder and read all");
        command = new JTextField(41);
        enterCom = new JLabel("Enter search type: ");
        BST = new JRadioButton("BST");
        BST.setSelected(true);
        TST = new JRadioButton("TST");
        Trie = new JRadioButton("Trie");
        Build = new JButton("Do This Command");
        Reset = new JButton("Reset");
        Help = new JButton("Help");
        Exit = new JButton("Exit");
        ButtonGroup group = new ButtonGroup();
        group.add(BST);
        group.add(TST);
        group.add(Trie);
        JPanel p = new JPanel();
        p.setBackground(new Color(162, 181, 205));
        up.add(p, new BorderLayout().NORTH);
        up.add(path, new BorderLayout().CENTER);
        up.add(Brows, new BorderLayout().SOUTH);
        center.add(infoS);
        down.add(enterCom, new BorderLayout().CENTER);
        down.add(BST, new BorderLayout().SOUTH);
        down.add(TST, new BorderLayout().SOUTH);
        down.add(Trie, new BorderLayout().SOUTH);
        downer.add(command, new BorderLayout().SOUTH);
        downerer.add(Build);
        downerer.add(Reset);
        downerer.add(Help);
        downerer.add(Exit);
        //action listeners
        Brows.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                reader = new Reader(path.getText(), info);
            }
        });
        Build.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                String treeType = "";
                if (BST.isSelected())
                    treeType = "bst";
                if (TST.isSelected())
                    treeType = "tst";
                if (Trie.isSelected())
                    treeType = "trie";
                String cmd = command.getText();
                info.setText(info.getText() + "\n");
                if(cmd.compareTo("list -w") == 0){
                    long time = System.currentTimeMillis();
                    reader.showWords(treeType);
                    String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                    info.setText(info.getText() + t);
                }
                else if(cmd.compareTo("list -f") == 0){
                    long time = System.currentTimeMillis();

                    File folder = new File(path.getText());
                    File[] listOfFiles = folder.listFiles();
                    for (int i = 0; i < listOfFiles.length; i++)
                        if (listOfFiles[i].isFile()) {
                            info.setText(info.getText() + "\n" + listOfFiles[i].getName());
                        }

                    String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                    info.setText(info.getText() + t);
                }
                else if (cmd.compareTo("list -l") == 0){
                    long time = System.currentTimeMillis();
                    reader.showFiles(treeType);
                    String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                    info.setText(info.getText() + t);
                }
                else {
                    Scanner scan = new Scanner(cmd);
                    String input = scan.next();
                    if(input.compareTo("delete")==0){
                        long time = System.currentTimeMillis();
                        reader.removeFile(scan.next());
                        String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                        info.setText(info.getText() + t);
                    }
                    else if(input.compareTo("add") == 0){
                        long time = System.currentTimeMillis();
                        reader.addNewFile(scan.next());
                        String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                        info.setText(info.getText() + t);
                    }
                    else if(input.compareTo("update") == 0){
                        String sss = scan.next();
                        long time = System.currentTimeMillis();
                        reader.removeFile(sss);
                        reader.addNewFile(sss);
                        String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                        info.setText(info.getText() + t);
                    }
                    else if(input.compareTo("search") == 0){
                        long time = System.currentTimeMillis();
                        String sss = scan.next();
                        if (sss.compareTo("-w") == 0)
                            reader.search(scan.next(), treeType);
                        else if (sss.compareTo("-treeType") == 0){
                            while(scan.hasNext()){
                                reader.search(scan.next(), treeType);
                            }
                        }
                        String t = String.format("\n" + (double)(System.currentTimeMillis() - time) / 1000.0 + " s lasted");
                        info.setText(info.getText() + t);
                    }

                }

            }
        });
        Reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                reader = new Reader();
                path.setText("");
                info.setText("");
                command.setText("");
            }
        });
        Exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //updates
        up.updateUI();
        center.updateUI();
        down.updateUI();
        downer.updateUI();
        downerer.updateUI();
    }
    public String getInfo(){
        return info.getText();
    }
    public void setInfo(String str){
        info.setText(str);
    }
    public String getPath(){
        return path.getText();
    }
    public String getCom(){
        return command.getText();
    }
    public void setCom(String str){
        command.setText(str);
    }

}
