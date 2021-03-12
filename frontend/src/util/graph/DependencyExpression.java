package util.graph;

import java.util.Collection;

public interface DependencyExpression<T> {

    boolean isTrue();

    Collection<T> getDependencies();

    void AND(DependencyExpression<T> dependencyExpression);
    void OR (DependencyExpression<T> dependencyExpression);

    boolean getNOT(boolean NOT);
    void setNOT(boolean NOT);
}
