package MotionCBS.client.ui.user.UserChangeInfoView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class UserChangeInfoView extends Composite {
    interface UserChangeInfoViewUiBinder extends UiBinder<HTMLPanel, UserChangeInfoView> {
    }

    private static UserChangeInfoViewUiBinder ourUiBinder = GWT.create(UserChangeInfoViewUiBinder.class);

    public UserChangeInfoView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}