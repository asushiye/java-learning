package structure.proxy.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/12
 */

public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println("begin transaction");
        if (realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
        System.out.println("end transaction");
    }
}
