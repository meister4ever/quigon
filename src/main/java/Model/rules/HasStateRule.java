package model.rules;

/**
 * Created by metro on 27/04/16.
 */
public class HasStateRule extends RuleExpression {

    @Override
    public Boolean validate() {
        return this.elementToValidate.hasState(this.elementOfElementToValidate);
    }

}
