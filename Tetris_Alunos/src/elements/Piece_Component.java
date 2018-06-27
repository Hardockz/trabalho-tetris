package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Piece_Component extends Element  implements Serializable{
    
    public Piece_Component(String imageName) {
        super(imageName);
        this.isTransposable = false;
    }
    
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

    public void backToLastPosition(){
        this.pos.comeBack();
    }
}
