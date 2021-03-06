package com.variamos.refas.core.simulationmodel;

import java.util.Map;


/**
 * Abstract  Class to group the ComparisonTranformation. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.ComparisonExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * Abstract Class to group at the ComparisonTransformation, currently only for
 * Equals. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractComparisonExpression extends
		AbstractExpression {

	public AbstractComparisonExpression() {
		super();
	}

	public AbstractComparisonExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractComparisonExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			BooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}

	public AbstractComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			NumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public abstract BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

	public abstract ComparisonExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap);

}
