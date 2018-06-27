package control;

import elements.Skull;
import elements.Lolo;
//import elements.Lolo;
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
import java.util.Random;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameScreen extends javax.swing.JFrame implements KeyListener {
    public int pieces_flag;
    public int other_pieces_aux_counter = 0;
    public int other_pieces_index_counter = 0;
    public Lolo[] other_pieces = new Lolo[800];

    public Lolo piece_component_2;
    public Lolo piece_component_3;
    public Lolo piece_component_4;
    public Lolo piece_component_1;

    private final ArrayList<Element> elemArray;
    private final GameController controller = new GameController();

    public int sortedPiece;

    public int piece_1_state = 0;
    public int piece_2_state = 0;
    public int piece_3_state = 0;
    public int piece_4_state = 0;
    public int piece_5_state = 0;
    public int piece_6_state = 0;
    public int piece_7_state = 0;

    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   /*teclado*/
        
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     Consts.NUM_CELLS_LEFT * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elemArray = new ArrayList<Element>();    

        pieces_flag = 0;

        piece_component_1 = new Lolo("lolo.png");
        piece_component_2 = new Lolo("lolo.png");
        piece_component_3 = new Lolo("lolo.png");
        piece_component_4 = new Lolo("lolo.png");

        // Skull skull = new Skull("excl.png");
        // skull.setPosition(9, 1);
        // this.addElement(skull);  
    }

    public void pieceManager(){
         if(other_pieces_aux_counter > 0){
            String[] position_aux1 = piece_component_1.getStringPosition().split(", ");
            String[] position_aux2 = piece_component_2.getStringPosition().split(", ");
            String[] position_aux3 = piece_component_3.getStringPosition().split(", ");
            String[] position_aux4 = piece_component_4.getStringPosition().split(", ");

            String[] position_x_aux1 = position_aux1[0].split("\\(");
            String[] position_x_aux2 = position_aux2[0].split("\\(");
            String[] position_x_aux3 = position_aux3[0].split("\\(");
            String[] position_x_aux4 = position_aux4[0].split("\\(");

            int position_x_1 = Integer.parseInt(position_x_aux1[1]);
            int position_x_2 = Integer.parseInt(position_x_aux2[1]);
            int position_x_3 = Integer.parseInt(position_x_aux3[1]);
            int position_x_4 = Integer.parseInt(position_x_aux4[1]);

            String[] position_y_aux1 = position_aux1[1].split("\\)");
            String[] position_y_aux2 = position_aux2[1].split("\\)");
            String[] position_y_aux3 = position_aux3[1].split("\\)");
            String[] position_y_aux4 = position_aux4[1].split("\\)");

            int position_y_1 = Integer.parseInt(position_y_aux1[0]);
            int position_y_2 = Integer.parseInt(position_y_aux2[0]);
            int position_y_3 = Integer.parseInt(position_y_aux3[0]);
            int position_y_4 = Integer.parseInt(position_y_aux4[0]);

            other_pieces[other_pieces_index_counter] = new Lolo("lolo.png");
            other_pieces[other_pieces_index_counter + 1] = new Lolo("lolo.png");
            other_pieces[other_pieces_index_counter + 2] = new Lolo("lolo.png");
            other_pieces[other_pieces_index_counter + 3] = new Lolo("lolo.png");


            other_pieces[other_pieces_index_counter].setPosition(position_x_1, position_y_1);
            other_pieces[other_pieces_index_counter + 1].setPosition(position_x_2, position_y_2);
            other_pieces[other_pieces_index_counter + 2].setPosition(position_x_3, position_y_3);
            other_pieces[other_pieces_index_counter + 3].setPosition(position_x_4, position_y_4);

            this.addElement(other_pieces[other_pieces_index_counter]);
            this.addElement(other_pieces[other_pieces_index_counter + 1]);
            this.addElement(other_pieces[other_pieces_index_counter + 2]);
            this.addElement(other_pieces[other_pieces_index_counter + 3]);

            other_pieces_index_counter = other_pieces_index_counter + 4;
        }else{
            other_pieces_aux_counter = other_pieces_aux_counter + 1;
        }

        Random randomGen = new Random();
        sortedPiece = randomGen.nextInt(7);

        this.removeElement(piece_component_1);
        this.removeElement(piece_component_2);
        this.removeElement(piece_component_3);
        this.removeElement(piece_component_4);

        if(sortedPiece == 0){ // THIS CODE WILL BE NEEDED ONLY ONCE, IM JUST WRITING FOR EACH TO PIECE TO REMEMBER THE POSITIONS LATER
            piece_component_1.setPosition(0, 4);
            piece_component_2.setPosition(0, 5);
            piece_component_3.setPosition(0, 6);
            piece_component_4.setPosition(0, 7);
        } else if(sortedPiece == 1){
            piece_component_1.setPosition(0, 4);
            piece_component_2.setPosition(0, 5);
            piece_component_3.setPosition(1, 4);
            piece_component_4.setPosition(1, 5);
        } else if(sortedPiece == 2){
            piece_component_1.setPosition(0, 5);
            piece_component_2.setPosition(1, 4);
            piece_component_3.setPosition(1, 5);
            piece_component_4.setPosition(1, 6);
        } else if(sortedPiece == 3){
            piece_component_1.setPosition(0, 4);
            piece_component_2.setPosition(0, 5);
            piece_component_3.setPosition(1, 4);
            piece_component_4.setPosition(2, 4);
        } else if(sortedPiece == 4){
            piece_component_1.setPosition(0, 3);
            piece_component_2.setPosition(0, 4);
            piece_component_3.setPosition(1, 4);
            piece_component_4.setPosition(2, 4);
        } else if(sortedPiece == 5){
            piece_component_1.setPosition(0, 3);
            piece_component_2.setPosition(0, 4);
            piece_component_3.setPosition(1, 4);
            piece_component_4.setPosition(1, 5);
        } else if(sortedPiece == 6){
            piece_component_1.setPosition(0, 4);
            piece_component_2.setPosition(0, 5);
            piece_component_3.setPosition(1, 4);
            piece_component_4.setPosition(1, 3);
        }

        this.addElement(piece_component_1);
        this.addElement(piece_component_2);
        this.addElement(piece_component_3);
        this.addElement(piece_component_4);
    }
    
    public int pieces_contact_checker(){
        String[] position_aux1 = piece_component_1.getStringPosition().split(", ");
        String[] position_aux2 = piece_component_2.getStringPosition().split(", ");
        String[] position_aux3 = piece_component_3.getStringPosition().split(", ");
        String[] position_aux4 = piece_component_4.getStringPosition().split(", ");

        String[] position_x_aux1 = position_aux1[0].split("\\(");
        String[] position_x_aux2 = position_aux2[0].split("\\(");
        String[] position_x_aux3 = position_aux3[0].split("\\(");
        String[] position_x_aux4 = position_aux4[0].split("\\(");

        String[] position_y_aux1 = position_aux1[1].split("\\)");
        String[] position_y_aux2 = position_aux2[1].split("\\)");
        String[] position_y_aux3 = position_aux3[1].split("\\)");
        String[] position_y_aux4 = position_aux4[1].split("\\)");

        int position_x_1 = Integer.parseInt(position_x_aux1[1]);
        int position_x_2 = Integer.parseInt(position_x_aux2[1]);
        int position_x_3 = Integer.parseInt(position_x_aux3[1]);
        int position_x_4 = Integer.parseInt(position_x_aux4[1]);

        int position_y_1 = Integer.parseInt(position_y_aux1[0]);
        int position_y_2 = Integer.parseInt(position_y_aux2[0]);
        int position_y_3 = Integer.parseInt(position_y_aux3[0]);
        int position_y_4 = Integer.parseInt(position_y_aux4[0]);

        for(int i = 0; i < other_pieces.length; i++){
            if(other_pieces[i] == null){
                return 0;
            }
            System.out.println(other_pieces[i].getStringPosition());
            String[] OP_AUX1 = other_pieces[i].getStringPosition().split(", ");
            
            String[] OP_x_aux = OP_AUX1[0].split("\\(");
            String[] OP_y_aux = OP_AUX1[1].split("\\)");

            int OP_X_FINAL = Integer.parseInt(OP_x_aux[1]);
            int OP_Y_FINAL = Integer.parseInt(OP_y_aux[0]);

            if(((position_x_1 + 1) == OP_X_FINAL) && (position_y_1 == OP_Y_FINAL)){
               return 1;
            }else if(((position_x_2 + 1) == OP_X_FINAL) && (position_y_2 == OP_Y_FINAL)){
                return 1;
            }else if(((position_x_3 + 1) == OP_X_FINAL) && (position_y_3 == OP_Y_FINAL)){
                return 1;
            }else if(((position_x_4 + 1) == OP_X_FINAL) && (position_y_4 == OP_Y_FINAL)){
                return 1;
            }
        }

        return 0;
    }

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
        for (int i = 0; i < Consts.NUM_CELLS_LEFT; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.IMG_PATH + "grey_piece2.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray);
        this.setTitle("-> Cell: " + piece_component_1.getStringPosition());
        
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

    public int pieceIsAtMaximumBottom(){ // separated in two ifs to not exceed line in text editor
        if((piece_component_1.getStringPosition().contains("17")) || (piece_component_2.getStringPosition().contains("17"))){

            return 1; // this is to leave the function incase it enters on this if, to not enter in the second if...
        }

        if((piece_component_3.getStringPosition().contains("17")) || (piece_component_4.getStringPosition().contains("17"))){

            return 1;
        }

        return 0;
    }

    public void piece_faller(){
        TimerTask piece_fall_task = new TimerTask() {
            
            @Override
            public void run() {
                if(pieces_flag == 0){
                    pieceManager();

                    pieces_flag = 1;
                }else{
                    if(pieceIsAtMaximumBottom() == 1){
                        pieceManager();
                    }else{
                        int should_move_down = pieces_contact_checker();

                        if(should_move_down == 0){
                            piece_component_1.moveDown(); 
                            piece_component_2.moveDown();
                            piece_component_3.moveDown();
                            piece_component_4.moveDown();
                        }else{
                            pieceManager();
                        }
                    }
                }
            }
        };

        Timer timer_piece_fall = new Timer();
        timer_piece_fall.schedule(piece_fall_task, 0L, 500);
    }
    // TREATMENT FOR WALLS COLISION ------------------------------------------------------------------------------------------------------------
    public int isPossibleToMoveRight(){ // probably gonna have one to each piece type too, for the spin process
        if((piece_component_1.getStringPosition().contains(", 9)")) || (piece_component_2.getStringPosition().contains(", 9)"))){
            return 1;
        }

        if((piece_component_3.getStringPosition().contains(", 9)")) || (piece_component_4.getStringPosition().contains(", 9)"))){
            return 1;
        }

        return 0;
    }// this is the general function for all pieces, for the wall edges
    public int isPossibleToMoveLeft(){ // probably gonna have one to each piece type too, for the spin process
        if((piece_component_1.getStringPosition().contains(", 0)")) || (piece_component_2.getStringPosition().contains(", 0)"))){
            return 1;
        }

        if((piece_component_3.getStringPosition().contains(", 0)")) || (piece_component_4.getStringPosition().contains(", 0)"))){
            return 1;
        }
        
        return 0;
    }// this is the general function for all pieces, for the wall edges
// TREATMENT FOR WALLS COLISION ------------------------------------------------------------------------------------------------------------

// TREATMENT FOR MOVEUP WALL ------------------------------------------------------------------------------------------------------------
    public void moveUpWall(Lolo piece1, Lolo piece2, Lolo piece3, Lolo piece4){
        if(isPossibleToMoveLeft() == 1){
            piece1.moveRight();
            piece2.moveRight();
            piece3.moveRight();
            piece4.moveRight();

            piece1.moveRight();
            piece2.moveRight();
            piece3.moveRight();
            piece4.moveRight();
        }

        if(isPossibleToMoveRight() == 1){
            piece1.moveLeft();
            piece2.moveLeft();
            piece3.moveLeft();
            piece4.moveLeft();

            piece1.moveLeft();
            piece2.moveLeft();
            piece3.moveLeft();
            piece4.moveLeft();
        }
    }
// TREATMENT FOR MOVEUP WALL ------------------------------------------------------------------------------------------------------------
    public void moveDownAndLeft(Lolo piece){ // for piece 1, to not write lots of code
        piece.moveLeft();
        piece.moveDown();
    }

    public void moveUpAndRight(Lolo piece){ // for piece 1, to not write lots of code
        piece.moveUp();
        piece.moveRight();
    }

    public void moveDownAndRight(Lolo piece){ // for piece 1, to not write lots of code
        piece.moveDown();
        piece.moveRight();
    }

     public void moveUpAndLeft(Lolo piece){ // for piece 1, to not write lots of code
        piece.moveUp();
        piece.moveLeft();
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(pieceIsAtMaximumBottom() == 1){
                pieceManager();
            }
            
            int should_move_down = pieces_contact_checker();

            if(should_move_down == 0){
                piece_component_1.moveDown(); 
                piece_component_2.moveDown();
                piece_component_3.moveDown();
                piece_component_4.moveDown();
            }else{
                pieceManager();
            }
            
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(isPossibleToMoveLeft() == 0){
                piece_component_1.moveLeft();
                piece_component_2.moveLeft();
                piece_component_3.moveLeft();
                piece_component_4.moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(isPossibleToMoveRight() == 0){

                piece_component_1.moveRight();
                piece_component_2.moveRight();
                piece_component_3.moveRight();
                piece_component_4.moveRight();
            }
        // PIECES SPIN ------------------------------------------------------------------------------------------------------------
        } else if (e.getKeyCode() == KeyEvent.VK_UP){
            moveUpWall(piece_component_1, piece_component_2, piece_component_3, piece_component_4);
            if(sortedPiece == 0){ // one condition for each piece type, since the pieces spin are different to each other
                if(piece_1_state == 0){
                    moveDownAndLeft(piece_component_2);

                    moveDownAndLeft(piece_component_3);
                    moveDownAndLeft(piece_component_3);

                    moveDownAndLeft(piece_component_4);
                    moveDownAndLeft(piece_component_4);
                    moveDownAndLeft(piece_component_4);
                    piece_1_state = 1;
                }else{
                    moveUpAndRight(piece_component_2);

                    moveUpAndRight(piece_component_3);
                    moveUpAndRight(piece_component_3);

                    moveUpAndRight(piece_component_4);
                    moveUpAndRight(piece_component_4);
                    moveUpAndRight(piece_component_4);

                    piece_1_state = 0;
                }
            }

            if(sortedPiece == 2){ // one condition for each piece type, since the pieces spin are different to each other
                if(piece_3_state == 0){
                    moveDownAndRight(piece_component_1);
                    moveUpAndRight(piece_component_2);
                    moveDownAndLeft(piece_component_4);

                    piece_3_state = 1;
                }else if(piece_3_state == 1){
                    moveDownAndLeft(piece_component_1);
                    moveDownAndRight(piece_component_2);
                    moveUpAndLeft(piece_component_4);

                    piece_3_state = 2;
                } else if(piece_3_state == 2){
                    moveUpAndLeft(piece_component_1);
                    moveDownAndLeft(piece_component_2);
                    moveUpAndRight(piece_component_4);

                    piece_3_state = 3;
                }else if(piece_3_state == 3){
                    moveUpAndRight(piece_component_1);
                    moveUpAndLeft(piece_component_2);
                    moveDownAndRight(piece_component_4);

                    piece_3_state = 0;
                }
            }

            if(sortedPiece == 3){ // one condition for each piece type, since the pieces spin are different to each other
                if(piece_4_state == 0){
                    moveDownAndLeft(piece_component_2);
                    moveUpAndLeft(piece_component_3);
                    moveUpAndLeft(piece_component_4);
                    moveUpAndLeft(piece_component_4);

                    piece_4_state = 1;
                }else if(piece_4_state == 1){
                    piece_component_1.moveDown();
                    piece_component_2.moveLeft();
                    piece_component_3.moveRight();
                    piece_component_4.moveRight();
                    moveUpAndRight(piece_component_4);

                    piece_4_state = 2;
                } else if(piece_4_state == 2){
                    piece_component_1.moveLeft();
                    piece_component_2.moveUp();
                    piece_component_3.moveDown();
                    piece_component_4.moveDown();

                    moveDownAndRight(piece_component_4);

                    piece_4_state = 3;
                }else if(piece_4_state == 3){
                    piece_component_1.moveUp();
                    piece_component_2.moveRight();
                    piece_component_3.moveLeft();
                    piece_component_4.moveLeft();

                    moveDownAndLeft(piece_component_4);

                    piece_4_state = 0;
                }
            }

            if(sortedPiece == 4){ // one condition for each piece type, since the pieces spin are different to each other
                if(piece_5_state == 0){
                    piece_component_1.moveRight();
                    piece_component_2.moveDown();
                    piece_component_3.moveLeft();
                    piece_component_4.moveLeft();

                    moveUpAndLeft(piece_component_4);

                    piece_5_state = 1;
                }else if(piece_5_state == 1){
                    moveDownAndRight(piece_component_1);
                    moveUpAndRight(piece_component_3);
                    moveUpAndRight(piece_component_4);
                    moveUpAndRight(piece_component_4);

                    piece_5_state = 2;
                } else if(piece_5_state == 2){
                    piece_component_1.moveLeft();
                    piece_component_2.moveUp();
                    piece_component_3.moveRight();
                    piece_component_4.moveRight();

                    moveDownAndRight(piece_component_4);

                    piece_5_state = 3;
                }else if(piece_5_state == 3){
                    piece_component_1.moveUp();
                    piece_component_2.moveRight();
                    piece_component_3.moveDown();
                    piece_component_4.moveDown();

                    moveDownAndLeft(piece_component_4);

                    piece_5_state = 0;
                }
            }

            if(sortedPiece == 5){ // one condition for each piece type, since the pieces spin are different to each other
                if(piece_6_state == 0){
                    piece_component_1.moveRight();
                    piece_component_2.moveDown();
                    piece_component_3.moveLeft();
                    piece_component_4.moveLeft();

                    moveDownAndLeft(piece_component_4);

                    piece_6_state = 1;
                }else if(piece_6_state == 1){
                    piece_component_1.moveLeft();
                    piece_component_2.moveUp();
                    piece_component_3.moveRight();
                    piece_component_4.moveRight();

                    moveUpAndRight(piece_component_4);
                   
                    piece_6_state = 0;
                } 
            }

            if(sortedPiece == 6){ // one condition for each piece type, since the pieces spin are different to each other
                if(piece_7_state == 0){
                    moveDownAndLeft(piece_component_2);
                    moveUpAndLeft(piece_component_3);

                    piece_component_4.moveUp();
                    piece_component_4.moveUp();

                    piece_7_state = 1;
                }else if(piece_7_state == 1){
                    moveUpAndRight(piece_component_2);
                    moveDownAndRight(piece_component_3);

                    piece_component_4.moveDown();
                    piece_component_4.moveDown();
                   
                    piece_7_state = 0;
                } 
            }
        } 
        // PIECES SPIN ------------------------------------------------------------------------------------------------------------
        
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
