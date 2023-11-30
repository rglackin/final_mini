package screens;
import other.*;

import java.awt.event.*;



abstract class Screen implements ActionListener {
    protected User u;

    public Screen() {
        this.u = new User();
    }
    public Screen(User u){
        this.u = u;
    }
    @Override 
    public void actionPerformed(ActionEvent e) {
       
        }
    abstract void initFrame();
    abstract void layoutConstraints();
    abstract void setupActionListener();
    }


