import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements ActionListener {

    static ServerSocket skt;
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    Server() {

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 350, 60);
        add(p1);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 17, 30, 30);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        setLayout(null);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 5, 50, 50);
        p1.add(l2);

        JLabel l3 = new JLabel("Bhuvan");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        //l3.setForeground(Color.white);
        l3.setForeground(Color.cyan);
        l3.setBounds(100, 10, 100, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        //l3.setForeground(Color.white);
        l4.setForeground(Color.cyan);
        l4.setBounds(100, 30, 100, 18);
        p1.add(l4);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(280, 17, 30, 30);
        p1.add(l5);

        t1 = new JTextField();
        t1.setBounds(5, 555, 240, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        b1 = new JButton("Send");
        b1.setBounds(245, 555, 100, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        a1 = new JTextArea();
        a1.setBounds(4, 60, 350, 490);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        a1.setEditable(false);

        add(a1);

        getContentPane().setBackground(Color.white);
        setSize(350, 600);
        setLocation(400, 100);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = t1.getText();
            a1.setText(a1.getText() + "\n\t\t" + "  " + out);
            dout.writeUTF(out);
            t1.setText("");
        }catch (Exception e){

        }
    }

    public static void main(String args[]) {
        new Server().setVisible(true);
        String msginput = "";
        try {

            skt = new ServerSocket(6001);

            while(true) {
                s = skt.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                while (true) {
                    msginput = din.readUTF();
                    a1.setText(a1.getText() + "\n" + msginput);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
