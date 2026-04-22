package NoobChain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractNoobChain<T> implements NoobChain<T> {
    protected final Block<T> genesisBlock;

    public AbstractNoobChain() {
        String hash = createGenesis();
        this.genesisBlock = new Block<>(null , hash);
    }

    private String createGenesis(){
        String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();

        int n = rand.nextInt(1000);
        int k = data.length();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < n; i++) temp.append(data.charAt(rand.nextInt(k)));

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(temp.toString().getBytes());

            String genesisHash = IntStream.range(0, hashBytes.length)
                                        .mapToObj(i -> String.format("%02x", hashBytes[i]))
                                        .collect(Collectors.joining());
            
            return genesisHash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    
}
