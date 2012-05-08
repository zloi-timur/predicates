package predicates.factory;

import predicates.domain.Predicate;
import predicates.domain.Tuple;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 08.05.12
 * Time: 18:46
 */
public class PredicateFactory_Reflecsive extends PredicateFactory{
    
    public static Predicate getTau(Integer dim, Integer capacity){
        Predicate refl = new Predicate(dim, capacity);
        for (Tuple tuple: new Tuple(dim, capacity))
            if (isRefl(tuple, dim))
                refl.addVector(tuple.getValues());
        return refl;
    }

    public static boolean isRefl(Tuple tuple, Integer dim) {
        boolean bb[] = new boolean[dim];
        for (Integer i: tuple.getValues()){
            if (!bb[i])
                bb[i] = true;
            else
                return true;
        }
        return false;
    }
}
