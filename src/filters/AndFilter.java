package filters;

import twitter4j.Status;

import java.util.List;

public class AndFilter implements Filter {
    private Filter leftChild;
    private Filter rightChild;

    public AndFilter(Filter leftChild, Filter rightChild){
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public boolean matches(Status s) { return (leftChild.matches(s) && rightChild.matches(s)); }

    @Override
    public List<String> terms() {
        List<String> terms;
        terms = leftChild.terms();
        terms.addAll(rightChild.terms());
        return terms;
    }

    @Override
    public String toString() {
        return "(" + leftChild + " and " + rightChild + ")";
    }
}
