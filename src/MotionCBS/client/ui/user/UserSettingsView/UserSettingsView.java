package MotionCBS.client.ui.user.UserSettingsView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class UserSettingsView extends Composite {
    interface UserSettingsViewUiBinder extends UiBinder<HTMLPanel, UserSettingsView> {
    }

    private static UserSettingsViewUiBinder ourUiBinder = GWT.create(UserSettingsViewUiBinder.class);

    public UserSettingsView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}