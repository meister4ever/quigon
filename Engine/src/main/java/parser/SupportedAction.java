package parser;

public class SupportedAction {
    private int numberOfItemsAffected;
    private String actionID;

    public SupportedAction(int numberOfItemsAffected, String actionID) {
        this.numberOfItemsAffected = numberOfItemsAffected;
        this.actionID = actionID.toLowerCase();
    }

    public Boolean isEqual(String actionID) {
        if (actionID.length() < this.actionID.length()) {
            return false;
        } else if (actionID.length() == this.actionID.length()) {
            return this.actionID.equals(actionID) && this.numberOfItemsAffected == 0;
        } else {
            String itemsString = actionID.substring(this.actionID.length() + 1);

            return (this.actionID.regionMatches(true, 0, actionID, 0, this.actionID.length())
                    && itemsString.split(" ").length == this.numberOfItemsAffected);
        }
    }

    public int getNumberOfItemsAffected() {
        return this.numberOfItemsAffected;
    }

    public String getActionID() {
        return this.actionID;
    }
}
