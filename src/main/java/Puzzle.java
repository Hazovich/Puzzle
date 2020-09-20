import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle extends JFrame {

    //Определение полей и их типы
    private BufferedImage image, resize;
    private int width, height;
    private ArrayList<Point> pics;
    private ArrayList<Button> buttons;

    //Пустой блок
    private Button emptyButton;

    //Сетка изображения
    private JPanel panel;

    //Ячейка (блоки) изображения
    private Image imgBlock;

    //Константа абсолютной ширины любого изображения
    private final int ABSOLUTE_WIDTH = 400;

    public Puzzle() throws IOException {
        initInterface();
    }

    //Создания интерфейса
    public void initInterface() throws IOException {

        //Определение "маркера" блокам изображения
        pics = new ArrayList<Point>();
        pics.add(new Point(0, 0));
        pics.add(new Point(0, 1));
        pics.add(new Point(0, 2));
        pics.add(new Point(1, 0));
        pics.add(new Point(1, 1));
        pics.add(new Point(1, 2));
        pics.add(new Point(2, 0));
        pics.add(new Point(2, 1));
        pics.add(new Point(2, 2));
        pics.add(new Point(3, 0));
        pics.add(new Point(3, 1));
        pics.add(new Point(3, 2));


        //Создание кнопки
        buttons = new ArrayList<Button>();

        //Создание панели
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        panel.setLayout(new GridLayout(4, 3));

        //Инициализация изображения и его размеров
        image = load();

        //Новая высота изображения
        int h = newHeight(image.getWidth(), image.getHeight());

        //Изображение с новым размером
        resize = resize(image, ABSOLUTE_WIDTH, h, BufferedImage.TYPE_INT_ARGB);

        //Новые размеры изображения
        width = resize.getWidth();
        height = resize.getHeight();

        //Размещение сетки по центру
        add(panel, BorderLayout.CENTER);

        //Обрезка изображения
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                imgBlock = createImage(new FilteredImageSource(resize.getSource(),
                        new CropImageFilter(col * width / 3, row * height / 4, width / 3, height / 4)));

                Button button = new Button(imgBlock);
                button.putClientProperty("position", new Point(row, col));

                if (row == 3 && col == 2) {
                    emptyButton = new Button();
                    emptyButton.setBorderPainted(false);
                    emptyButton.setContentAreaFilled(false);
                    emptyButton.setEmptyField(true);
                }else {
                    buttons.add(button);
                }
            }
        }
    }

    //Переопределение размеров изображения
    private BufferedImage resize(BufferedImage img, int w, int h, int type) {
        BufferedImage image = new BufferedImage(w, h, type);
        Graphics2D grisha = image.createGraphics();
        grisha.drawImage(img, 0, 0, w, h, null);
        grisha.dispose();
        return image;
    }

    //Загрузка изображения
    private BufferedImage load() throws IOException {
        BufferedImage img = ImageIO.read(new File("roses.jpg"));
        return img;
    }

    //Определение новой высоты
    private int newHeight(int w, int h) {
        double ratio = ABSOLUTE_WIDTH / (double) w;
        int height = h * (int) ratio;
        return height;
    }
}
