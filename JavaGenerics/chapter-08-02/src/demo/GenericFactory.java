package demo;

public class GenericFactory<T> {
    Class cazz = null;

    public GenericFactory(Class cazz){
        this.cazz = cazz;
    }
    public T createInstance() throws IllegalAccessException, InstantiationException {
        return (T)this.cazz.newInstance();
    }
}
