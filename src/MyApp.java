import javax.swing.JFrame;

public class MyApp {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GamePlay gameplay = new GamePlay();

        obj.setBounds(10,10,700,600);
        
        obj.setTitle("Brick_Breaker");
        obj.setResizable(false);

        obj.setLocationRelativeTo(null);

        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
    
}