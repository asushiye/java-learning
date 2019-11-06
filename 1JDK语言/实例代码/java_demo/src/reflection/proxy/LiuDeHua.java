package reflection.proxy;

public class LiuDeHua implements Person {
    @Override
    public String sing(String name) {
        System.out.println("请刘德华出场唱"+name+"歌");
        return "歌已唱，谢谢大家！";
    }

    @Override
    public String dance(String name) {
        System.out.println("请刘德华出场跳"+name+" 舞");
        return "舞蹈跳完，谢谢大家！";
    }
}
