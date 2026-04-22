package NoobChain;

public interface NoobChain<T> {
    void addBlock(T data);

    String getCurrentHash();

    Boolean verifyChain();
}
