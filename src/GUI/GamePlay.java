package GUI;
import Class.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.Timer;  
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePlay extends javax.swing.JFrame {

    public GamePlay() {
        initComponents();
        start();
    }
    
    Config con=new Config();
    GamePlan game=new GamePlan();
    Player player=new Player();
    
    int numList[];
    int timer;
    int chance;
    int score=0;
    int scoreLimit;
    
    int playerId;
    int level;
    
    //Assign Buttons
    public void assignButton(int numList[], int Button_count){
        Container buttonLayout;
        this.setDefaultCloseOperation(GamePlay.EXIT_ON_CLOSE);

        buttonLayout = jPanel1;
        buttonLayout.setLayout(new GridLayout(3, 2));

        List<JButton> buttonList = new ArrayList<JButton>();
            for(int i = 0; i < Button_count; i++) {
                String value = Integer.toString(numList[i]);
                JButton button = new JButton(value);
                button.setPreferredSize(new Dimension(80, 40));
                button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonclick(button);
                }
                });
                buttonList.add(button);
                buttonLayout.add(button);
            }
    }

    //start game
    public void start(){
        level=player.getPlayerLevel();
        playerId=player.getPlayerId();
        game.gamePlan(level);
        t.start();
        numList=game.getNumList();
        assignButton(numList, game.getBtncount());
        timer=game.getTimer();
        chance=game.getChance();
        scoreLimit=game.getScoreLimit();
        jLabel1.setText("Level "+level);
        
        if(chance==3){
             jLabel3.setVisible(false);
        }else if(chance==2){
             jLabel3.setVisible(false);
             jLabel4.setVisible(false);
        } 
    }
    
    //Timer
    Timer t=new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(timer>0){
                timer--;
                jLabel2.setText(""+timer);
                
            }
            if(timer==0){
                t.stop();
                if(score>=scoreLimit){
                    JOptionPane.showMessageDialog(null, "Congratulations! You have successfully passes the level ", "Level Passed", JOptionPane.INFORMATION_MESSAGE);
                    nextGame();
                }else{
                    JOptionPane.showMessageDialog(null, "Sorry! Times UP!", "Level Failed", JOptionPane.INFORMATION_MESSAGE);
                    newGame();
                }
            }
        }
    });
    
    //remove chances
    public boolean removeChance(){
        if(chance==4){
            jLabel3.setVisible(false);
            chance--;
        }else if(chance==3){
            jLabel4.setVisible(false);
            chance--;
        }else if(chance==2){
            jLabel5.setVisible(false);
            chance--;
        }else if(chance==1){
            jLabel6.setVisible(false);
            chance--;
        }else if(chance==0){
            return false;
        }
        return true;
    }
    
    public void scoreSet(){
        score=game.setScore(score);
        jLabel8.setText(Integer.toString(score)+"/"+scoreLimit);
    }
     
   
     public void buttonclick(JButton jButton){
         
        boolean result=game.clicked(Integer. parseInt(jButton.getText()));
        if(result==true){
            jButton.setEnabled(false);
            scoreSet();
        }else{
            boolean chance=removeChance();
            if (chance==false){
                t.stop();
                if(score>=scoreLimit){
                    JOptionPane.showMessageDialog(null, "Congratulations! You have successfully passes the level ", "Level Passed", JOptionPane.INFORMATION_MESSAGE);
                    nextGame();
                }else{
                    JOptionPane.showMessageDialog(null, "Sorry! You don't have any hearts left!", "Level Failed", JOptionPane.INFORMATION_MESSAGE);
                    newGame();
                }
            }
        }
        boolean AllClicked=game.isAllClicked();
        if(AllClicked==true)
        {
            t.stop();
            JOptionPane.showMessageDialog(null, "Congratulations! You have successfully passes the level ", "Level Passed", JOptionPane.INFORMATION_MESSAGE);
            nextGame();
        }   
    }
     
     public void newGame(){
         this.setVisible(false);
         GamePlay newgame=new GamePlay();
         newgame.setVisible(true);
     }
     
     public void nextGame(){
        con.insertData("INSERT INTO `game_score` (Player_Id,Level,Score,Time) VALUES("+playerId+","+level+","+score+","+timer+");");
        String player_Email=player.getPlayerEmail();
        String player_Username=player.getplayerUsername();
        try {
            SendMail.sendMail(player_Email,player_Username,level,score);
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         int point=0;
         ResultSet rs=con.getData("SELECT Best_Score_Point FROM `best_score` WHERE Best_Score_Level="+level+";");
         try {
            while(rs.next())
            {
                point=Integer.parseInt(rs.getString("Best_Score_Point"));
            }
         } catch (SQLException ex) {
            Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         if(score>=point){
            con.insertData("UPDATE `best_score` SET Best_Score_Player_Id="+playerId+",Best_Score_Point="+score+" WHERE Best_Score_Level="+level+";"); 
         }
         
         level=level+1;
         player.setPlayerLevel(level);
         con.insertData("UPDATE `player` SET Player_Level="+level+" where Player_Id="+playerId+";");
         this.setVisible(false);
         GamePlay newgame=new GamePlay();
         newgame.setVisible(true);
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jPanel2.setPreferredSize(new java.awt.Dimension(850, 300));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Find The Least");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 760));
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 670, 80, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 300));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(230, 200, 816, 300);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Timer");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Level");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_heart_30px.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_heart_30px.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_heart_30px.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_heart_30px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(507, 507, 507)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 1280, 100);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Score : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(102, 102, 102))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(1040, 650, 240, 70);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gameplay1 (1).jpg"))); // NOI18N
        jLabel9.setText("jLabel9");
        jLabel9.setMaximumSize(new java.awt.Dimension(1280, 720));
        jLabel9.setMinimumSize(new java.awt.Dimension(1280, 720));
        jLabel9.setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 1280, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        t.stop();
        setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamePlay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
