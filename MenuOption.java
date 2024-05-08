package ProjectPOO;

import java.text.ParseException;

class MenuOption implements Controller {
    String text;
    Controller controller;

    public MenuOption(String text, Controller controller) {
        this.text = text;
        this.controller = controller;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void execute(User user) throws ParseException {

    }
}