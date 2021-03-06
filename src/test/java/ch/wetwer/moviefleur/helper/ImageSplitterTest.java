package ch.wetwer.moviefleur.helper;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImageSplitterTest {

    @Test
    public void testSplit() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final List<BufferedImage> result = ImageSplitter.split(frameDefault);

        // Verify the results
        assertEquals(frameDefault.getWidth() * 2, result.get(0).getWidth() + result.get(1).getWidth());
    }

    @Test(expected = IOException.class)
    public void testSplit_ThrowsIOException() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("test/test.png"));

        // Run the test
        ImageSplitter.split(frameDefault);
    }

    @Test
    public void testResize() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 300;
        final int height = 300;

        // Run the test
        final BufferedImage result = ImageSplitter.resize(image, width, height);

        // Verify the results
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test
    public void testCrop() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 300;
        final int height = 300;

        // Run the test
        final BufferedImage result = ImageSplitter.crop(image, width, height);

        // Verify the results
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test(expected = RasterFormatException.class)
    public void testCrop_ThrowsRasterFormatException() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 2000;
        final int height = 2000;

        // Run the test
        ImageSplitter.crop(image, width, height);
    }

    @Test
    public void testCropOffset() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 300;
        final int height = 300;
        final int offsetX = 20;
        final int offsetY = 20;

        // Run the test
        final BufferedImage result = ImageSplitter.crop(image, offsetX, offsetY, width, height);

        // Verify the results
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test(expected = RasterFormatException.class)
    public void testCropOffset_ThrowsRasterFormatException() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 2000;
        final int height = 2000;
        final int offsetX = 20;
        final int offsetY = 20;

        // Run the test
        ImageSplitter.crop(image, offsetX, offsetY, width, height);
    }

}
