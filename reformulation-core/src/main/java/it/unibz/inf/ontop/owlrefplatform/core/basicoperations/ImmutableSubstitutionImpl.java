package it.unibz.inf.ontop.owlrefplatform.core.basicoperations;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unibz.inf.ontop.model.*;
import it.unibz.inf.ontop.utils.ImmutableCollectors;
import org.openrdf.query.algebra.Var;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collector;

/**
 * Wrapper above a ImmutableMap<Variable, ImmutableTerm> map.
 */
public class ImmutableSubstitutionImpl<T extends ImmutableTerm> extends AbstractImmutableSubstitutionImpl<T> {

    private final ImmutableMap<Variable, T> map;

    public ImmutableSubstitutionImpl(ImmutableMap<Variable, ? extends T> substitutionMap) {
        this.map = (ImmutableMap<Variable, T>) substitutionMap;
    }

    @Override
    public T get(Variable var) {
        return map.get(var);
    }

    @Override
    public ImmutableMap<Variable, T> getImmutableMap() {
        return map;
    }

    @Override
    public boolean isDefining(Variable variable) {
        return map.containsKey(variable);
    }

    @Override
    public ImmutableSet<Variable> getDomain() {
        return map.keySet();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public final ImmutableMap<Variable, Term> getMap() {
        return (ImmutableMap<Variable, Term>)(ImmutableMap<Variable, ?>) map;
    }

    @Override
    public ImmutableTerm applyToVariable(Variable variable) {
        if (map.containsKey(variable))
            return map.get(variable);
        return variable;
    }

    @Override
    public Var2VarSubstitution getVar2VarFragment() {
        ImmutableMap<Variable, Variable> newMap = map.entrySet().stream()
                .filter(e -> e.getValue() instanceof Variable)
                .map(e -> (Map.Entry<Variable, Variable>) new AbstractMap.SimpleEntry<>(
                        e.getKey(), (Variable) e.getValue()))
                .collect(ImmutableCollectors.toMap());

        return new Var2VarSubstitutionImpl(newMap);
    }

    @Override
    public ImmutableSubstitution<GroundTerm> getVar2GroundTermFragment() {
        ImmutableMap<Variable, GroundTerm> newMap = map.entrySet().stream()
                .filter(e -> e.getValue() instanceof GroundTerm)
                .map(e -> (Map.Entry<Variable, GroundTerm>) new AbstractMap.SimpleEntry<>(
                        e.getKey(), (GroundTerm) e.getValue()))
                .collect(ImmutableCollectors.toMap());

        return new ImmutableSubstitutionImpl<>(newMap);
    }

    @Override
    public String toString() {
        return Joiner.on(", ").withKeyValueSeparator("/").join(map);
    }

    @Override
    protected ImmutableSubstitution<T> constructNewSubstitution(ImmutableMap<Variable, T> map) {
        return new ImmutableSubstitutionImpl<>(map);
    }



}
