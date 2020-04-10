package designpatterns;

// 饿汉模式(线程安全，枚举方式)
public enum Singleton {
    //定义一个枚举的元素，它就是 Singleton 的一个实例

    INSTANCE;

    public void doSomeThing() {
        System.out.println("枚举方法实现单例");
    }

}
