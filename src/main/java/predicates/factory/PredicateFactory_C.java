package predicates.factory;

import predicates.domain.Predicate;
import predicates.domain.Tuple;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 03.05.12
 * Time: 0:14
 */
public class PredicateFactory_C extends PredicateFactory implements Iterable<Predicate>, Iterator<Predicate> {
    Set<Predicate> predicateSet;
    Iterator<Predicate> current;
    Integer capacity;

    public PredicateFactory_C(Integer dim, Integer capacity) {
        this.dim = dim;
        this.capacity = capacity;
        this.predicateSet = new HashSet<Predicate>();

        Predicate refl = PredicateFactory_Reflecsive.getTau(dim, capacity);
        
        for (Tuple c: new Tuple(2,dim)) {
            if (c.isStart() || c.isFull())
                continue;

            Predicate prefinal = new Predicate(refl);
            Set<Tuple> notIncluded = new HashSet<Tuple>();

            for(Tuple tuple2: new Tuple(dim,capacity)){
                boolean isInC = false;
                for (int ii:tuple2.getValues())
                    isInC |= c.getValues().get(ii)==1;
                if (isInC)
                    prefinal.addVector(tuple2.getValues());
                else
                    notIncluded.add(tuple2);
            }

            List<Tuple> candidates = new ArrayList<Tuple>(notIncluded);
            for (Tuple cand: new Tuple(2, candidates.size())){
                if (cand.isFull())
                    continue;
                Predicate fin = new Predicate(prefinal);
                for (int pos=0;pos<cand.getValues().size(); pos++)
                    if (pos==1)
                        fin.addVector(candidates.get(pos).getValues());
                predicateSet.add(fin);
            }
        }
        current = predicateSet.iterator();
    }


    @Override
    public Iterator<Predicate> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return current.hasNext();
    }

    @Override
    public Predicate next() {
        return current.next();
    }

    @Override
    public void remove() {}
}
