import ljc.Monomorph;

public class MainWrong {

    public static void main(String[] args) {
        @Monomorph Cat c = new Cat();
        // Cat c = new Cat();
        c.speak();
        c = new Kitten();
        c.speak();
    }

}

