package enumStudy;

import annotationStudy.*;
public class EnumStudy {

    public static void main(String[] args){
        Level level = Level.HIGH;
        for (Level slevel : Level.values()) {
            System.out.println(slevel);
        }


        String levelValue= level.getLevelValue();
        System.out.println(levelValue);

        //AnnotationStudy 标记为 @Deprecate
        AnnotationStudy annotationStudy= new AnnotationStudy();
    }
}
