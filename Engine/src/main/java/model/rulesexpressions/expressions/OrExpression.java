package model.rulesexpressions.expressions;

public class OrExpression extends LogicExpression {

    //Methods
    public OrExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() || this.rightExpression.interpret());
    }

    @Override
    protected String getFinalMessage() {
        String failMessage = this.leftExpression.getFailMessage();

        if (failMessage.isEmpty()) {
            return this.rightExpression.getFailMessage();
        }
        return failMessage;
    }

}
