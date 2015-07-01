package org.semanticweb.ontop.model;

import org.semanticweb.ontop.model.impl.VariableImpl;
import org.semanticweb.ontop.pivotalrepr.ImmutableQueryModifiers;

/**
 * Substitution where variables are only mapped to variables
 */
public interface Var2VarSubstitution extends ImmutableSubstitution<VariableImpl> {

    @Override
    VariableImpl applyToVariable(VariableImpl variable);

    VariableOrGroundTerm applyToVariableOrGroundTerm(VariableOrGroundTerm term);

    NonGroundTerm applyToNonGroundTerm(NonGroundTerm term);

    ImmutableQueryModifiers applyToQueryModifiers(ImmutableQueryModifiers immutableQueryModifiers);
}
