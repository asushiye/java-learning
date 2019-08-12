package structure.proxy.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/12
 */

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}
