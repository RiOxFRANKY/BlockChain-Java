package NoobChain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Block<T> {
    private final T data;
    private final String prevHash;
    private final String currHash;
    private Long nonce;

    public Block(T data , String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.nonce = 0L;
        this.currHash = calculateHash();
    }

    private String calculateHash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            while (true) { 
                String text = this.prevHash + "|" + this.nonce + "|" + (this.data != null ? this.data.toString() : "null");
                byte[] hashBytes = md.digest(text.getBytes());

                String hash = IntStream.range(0, hashBytes.length)
                                    .mapToObj(i -> String.format("%02x", hashBytes[i]))
                                    .collect(Collectors.joining());
                
                if(hash.startsWith("0000")) return hash;

                this.nonce++;
                
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHash(){
        return this.currHash;
    }

    public Boolean checkBlock(Block<T> prev){
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = prev.getHash() + "|" + this.nonce + "|" + (this.data != null ? this.data.toString() : "null");
            byte[] hashBytes = md.digest(text.getBytes());

            String hash = IntStream.range(0, hashBytes.length)
                                .mapToObj(i -> String.format("%02x", hashBytes[i]))
                                .collect(Collectors.joining());

            return this.getHash().equals(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return (this.data != null ? this.data.toString() : "null") + "|" + nonce;
    }

    
}
