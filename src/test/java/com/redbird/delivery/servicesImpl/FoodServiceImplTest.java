package com.redbird.delivery.servicesImpl;

import com.github.javafaker.Faker;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.redbird.delivery.models.Food;
import com.redbird.delivery.repositories.FoodRepository;
import com.redbird.delivery.services.FoodService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class FoodServiceImplTest {

    private static final Faker faker = new Faker(new Locale("ru"));
    private static List<Food> fixtures = new ArrayList<>();
    private static String path = "src/main/resources/static/img/charts/";

    //
    // Отошел
    //

    @BeforeAll
    public static void generateFixtures() {
        for (int i = 0; i < 50; i++) {
            Food food = new Food();
            food.setId((long)i);
            food.setName(faker.food().dish());
            food.setType(faker.country().name());
            food.setDescription(faker.lorem().sentence());
            food.setPrice((double) faker.number().numberBetween(0, 20000));

            fixtures.add(food);
        }
    }

    @Test
    public void init() {

    }

    @AfterAll
    public static void drawer() throws PythonExecutionException, IOException {
        List<Double> prices = new ArrayList<>();
        List<Integer> countriesCharAmount = new ArrayList<>();
        List<Integer> descriptionWords = new ArrayList<>();
        fixtures.forEach(food -> {
            prices.add(food.getPrice());
            countriesCharAmount.add(food.getType().length());
            descriptionWords.add(countWords(food.getDescription()));
        });
        drawHistogram(prices, 20000, "food prices", "prices.png", "Redbird");
        drawSimple(descriptionWords, "words in description", "o", "description.png", "Redbird");
        drawSimple(countriesCharAmount, "countries char amount", "-", "charamount.png", "Redbird");
    }

    public static void drawHistogram(List<? extends Number> x1, Integer xmax, String type,String filename,String text) throws PythonExecutionException, IOException {
        Plot plt = Plot.create();
        plt.hist()
                .add(x1)
                .bins(40)
                .stacked(true)
                .color("#490e5d");
        plt.xlim(0, xmax);
        plt.title(type);
        plt.savefig(path + filename).dpi(200);
        plt.executeSilently();
        addTextWatermark(text, new File(path + filename));
    }

    public static void drawSimple(List<? extends Number> y,String type, String fmt, String filename,String text) throws PythonExecutionException, IOException {
        Plot plt = Plot.create();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            indexes.add(i);
        }
        plt.plot().add(indexes, y, fmt).label(type);
        plt.legend().loc("upper right");
        plt.title(type);
        plt.savefig(path + filename).dpi(200);
        plt.executeSilently();
        addTextWatermark(text, new File(path + filename));
    }

    public static int countWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }

    public static void addTextWatermark(String text, File sourceImageFile) {
        try {
            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            g2d.setComposite(alphaChannel);
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Consolas", Font.BOLD, 96));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
            int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
            int centerY = sourceImage.getHeight() / 2;
            g2d.drawString(text, centerX, centerY);

            ImageIO.write(sourceImage, "png", new File(sourceImageFile.getAbsolutePath()));
            g2d.dispose();

        } catch (IOException ex) {
        }
    }

}