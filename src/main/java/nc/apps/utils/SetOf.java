package nc.apps.utils;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class SetOf {
    public static <T> Set<T> from(T... params){
        return new HashSet<T>(Arrays.stream(params).collect(Collectors.toList()));
    }
}
