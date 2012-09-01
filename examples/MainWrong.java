import ljc.Monomorph;

public class MainWrong {

    public static void main(String[] args) {
        @Monomorph Cat c = new @Monomorph Cat();
        c.speak();
        c = new @Monomorph Kitten();
        c.speak();
    }

/*
        @Monomorph Cat other = newCat();
        other.speak();
        other = newKitten();
        other.speak();
    }

    static @Monomorph Cat newCat() {
        return new @Monomorph Cat();
    }

    static Cat newKitten() {
        return new Kitten();
    }
*/

}

