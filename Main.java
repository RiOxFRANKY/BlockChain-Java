import NoobChain.*;

public class Main {
    public static void main(String[] args) {
        LinkedNoobChain<String> chain = new LinkedNoobChain<>();

        for (int i = 0; i < 10; i++) {
            chain.addBlock("xd");
            System.out.println(chain.getCurrentHash());
            System.out.println(chain.get(i+1));
        }

        System.out.println(chain.verifyChain());

        chain.change(5, "krinje");

        

        System.out.println(chain.verifyChain());

        chain.change(5, "xd");

        

        System.out.println(chain.verifyChain());
    }
}
