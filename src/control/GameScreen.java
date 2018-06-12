package control;

import elements.Skull;
import elements.Lolo;
import elements.Element;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameScreen extends javax.swing.JFrame implements KeyListener {
    public int pieceState = 0;
    public int randomPiece = 1;
    
    private final Lolo PC1;
    public Lolo PC2;
    public Lolo PC3;
    public Lolo PC4;

    private final ArrayList<Element> elemArray;
    private final GameController controller = new GameController();

    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   /*teclado*/
        
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elemArray = new ArrayList<Element>();

        
        public Lolo pieceBlock_1;
        public Lolo pieceBlock_2;
        public Lolo pieceBlock_3;
        public Lolo pieceBlock_4;

        pieceBlock_1 = new Lolo("lolo.jpg");
        pieceBlock_2 = new Lolo("lolo.jpg");
        pieceBlock_3 = new Lolo("lolo.jpg");
        pieceBlock_4 = new Lolo("lolo.jpg");

        this.addElement(pieceBlock_1);
        this.addElement(pieceBlock_2);
        this.addElement(pieceBlock_3);
        this.addElement(pieceBlock_4);


        /*Cria e adiciona elementos*/
        /*PC1 = new Lolo("lolo.jpg");
        PC1.setPosition(0, 5);
        this.addElement(PC1);
        
        PC2 = new Lolo("lolo.jpg");
        PC2.setPosition(1, 5);
        this.addElement(PC2);

        PC3 = new Lolo("lolo.jpg");
        PC3.setPosition(2, 5);
        this.addElement(PC3);

        PC4 = new Lolo("lolo.jpg");
        PC4.setPosition(3, 5);
        this.addElement(PC4); */

        private int pieceType;  

        /*public void pieceManager(int position_aux_x, int position_aux_y){











           Random nRandom = new Random();
            sortedPiece = nRandom.nextInt(7);         
        
            if(sortedPiece == 0){
                pieceBlock_1.setPosition(0, 5);
                pieceBlock_2.setPosition(1, 5);
                pieceBlock_3.setPosition(2, 5);
                pieceBlock_4.setPosition(3, 5);
            }

            if(sortedPiece == 1){
                pieceBlock_1.setPosition(0, 4);
                pieceBlock_2.setPosition(0, 5);   
                pieceBlock_3.setPosition(1, 4);
                pieceBlock_4.setPosition(1, 5);
            }
            
            if(sortedPiece == 2){
                pieceBlock_1.setPosition(0, 5);
                pieceBlock_2.setPosition(1, 4);   
                pieceBlock_3.setPosition(1, 5);
                pieceBlock_4.setPosition(1, 6);
            }

            if(sortedPiece == 3){
                pieceBlock_1.setPosition(0, 4);
                pieceBlock_2.setPosition(0, 5);   
                pieceBlock_3.setPosition(1, 4);
                pieceBlock_4.setPosition(2, 4);
            }

            if(sortedPiece == 4){
                pieceBlock_1.setPosition(0, 3);
                pieceBlock_2.setPosition(0, 4);   
                pieceBlock_3.setPosition(1, 4);
                pieceBlock_4.setPosition(2, 4);
            }
            
            if(sortedPiece == 5){
                pieceBlock_1.setPosition(0, 3);
                pieceBlock_2.setPosition(0, 4);   
                pieceBlock_3.setPosition(1, 4);
                pieceBlock_4.setPosition(1, 5);
            }

            if(sortedPiece == 6){
                pieceBlock_1.setPosition(0, 4);
                pieceBlock_2.setPosition(0, 5);   
                pieceBlock_3.setPosition(1, 4);
                pieceBlock_4.setPosition(1, 3);
            }
        }                



//        Skull skull = new Skull("caveira.png");
//        skull.setPosition(9, 1);
//        this.addElement(skull);  
    } */
    
    public final void addElement(Element elem) {
        elemArray.add(elem);
    }
    
    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }
    
    @Override
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();
        
        /*Criamos um contexto grafico*/
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
        
        /* DESENHA CENARIO
           Trocar essa parte por uma estrutura mais bem organizada
           Utilizando a classe Stage
        */
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.IMG_PATH + "fundoTetris.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray);
        this.setTitle("-> Cell: " + PC1.getStringPosition());
        
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY_SCREEN_UPDATE);
    }

    public void pieceSpinner(){
        if(randomPiece == 1){
            if(pieceState == 0){
                PC2.moveUp();
                PC2.moveRight();
                PC3.moveUp();
                PC3.moveRight();
                PC3.moveUp();
                PC3.moveRight();
                PC4.moveUp();
                PC4.moveRight();
                PC4.moveUp();
                PC4.moveRight();
                PC4.moveUp();
                PC4.moveRight();

                pieceState = 1;
            }else if(pieceState == 1){
                PC2.moveDown();
                PC2.moveLeft();
                PC3.moveDown();
                PC3.moveLeft();
                PC3.moveDown();
                PC3.moveLeft();
                PC4.moveDown();
                PC4.moveLeft();
                PC4.moveDown();
                PC4.moveLeft();
                PC4.moveDown();
                PC4.moveLeft();

                pieceState = 0;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {      
            pieceSpinner();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            PC1.moveDown();
            PC2.moveDown();
            PC3.moveDown();
            PC4.moveDown(); 
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            PC1.moveLeft();
            PC2.moveLeft();
            PC3.moveLeft();
            PC4.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            PC1.moveRight();
            PC2.moveRight();
            PC3.moveRight();
            PC4.moveRight();
        }

        
        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
