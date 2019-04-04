package MotionCBS.client.ui.login.StartView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;

public class StartView {
    interface StartViewUiBinder extends UiBinder<com.google.gwt.user.client.ui.HTMLPanel, StartView> {
    }

    private static StartViewUiBinder ourUiBinder = GWT.create(StartViewUiBinder.class);

    public StartView() {
        com.google.gwt.user.client.ui.HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    }
}