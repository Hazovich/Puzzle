
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {

   private boolean isEmptyField;


   //Конструкторы
    public Button() {
        super();
        initInterface();
    }

    public Button(Image image) {
        super(new ImageIcon(image));
    }


    //Работа мыши с интерфейсом
    private void initInterface() {

        isEmptyField = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.yellow));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
    }

    //Геттер и Сеттер
    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField(boolean emptyField) {
        isEmptyField = emptyField;
    }
}

