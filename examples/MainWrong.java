import ljc.Monomorph;

public class MainWrong {

    public static void main(String[] args) {
        //@Monomorph Cat c = "wrong type";
        @Monomorph Cat c = new Cat();
        // Cat c = new Cat();
        c.speak();
        c = new Kitten();
        c.speak();
    }

/*
    static @Monomorph Cat newCat() {
        return new Cat();
    }

    static Cat newKitten() {
        return new Kitten();
    }
*/

}

