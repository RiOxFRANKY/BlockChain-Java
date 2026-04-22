package NoobChain;

import java.util.ArrayList;

public class ArrayNoobChain<T> extends AbstractNoobChain<T> {
    private final ArrayList<Block<T>> chain;

    public ArrayNoobChain() {
        chain = new ArrayList<>();
        chain.add(genesisBlock);
    }

    @Override
    public void addBlock(T data){
        Block<T> temp = new Block<>(data, chain.get(chain.size() - 1 ).getHash());
        chain.add(temp);
    }

    @Override
    public String getCurrentHash(){
        return chain.get(chain.size() - 1).getHash();
    }

    @Override
    public Boolean verifyChain(){
        for(int i = 1 ; i < chain.size() ; i++){
            if(!chain.get(i).checkBlock(chain.get(i - 1))) return false;
        }

        return true;
    }

    
}
