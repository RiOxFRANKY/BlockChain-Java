package NoobChain;

import java.util.LinkedList;

public class LinkedNoobChain<T> extends AbstractNoobChain<T> {
    LinkedList<Block<T>> chain ;

    public LinkedNoobChain() {
        chain = new LinkedList<>();
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

    public String get(int i){
        return chain.get(i).toString();
    }

    public void change(int i , T data){
        chain.set(i + 1, new Block<>(data , chain.get(i).getHash()));
    }
}
