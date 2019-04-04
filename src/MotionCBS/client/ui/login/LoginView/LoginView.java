package MotionCBS.client.ui.login.LoginView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class LoginView extends Composite {
    interface LoginViewUiBinder extends UiBinder<com.google.gwt.user.client.ui.HTMLPanel, LoginView> {
    }

    @UiField
    Button loginBtn;

    private static LoginViewUiBinder ourUiBinder = GWT.create(LoginViewUiBinder.class);

    public LoginView() {
        com.google.gwt.user.client.ui.HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    }



    /**
     * Method to add a Click Handler to the login button
     *
     * @param clickHandler
     */
    public void addClickHandlers(ClickHandler clickHandler) {
        loginBtn.addClickHandler(clickHandler);
    }

}
