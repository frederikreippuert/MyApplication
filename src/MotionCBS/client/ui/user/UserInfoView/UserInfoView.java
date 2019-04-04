package MotionCBS.client.ui.user.UserInfoView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class UserInfoView extends Composite {
    interface UserInfoViewUiBinder extends UiBinder<HTMLPanel, UserInfoView> {
    }

    private static UserInfoViewUiBinder ourUiBinder = GWT.create(UserInfoViewUiBinder.class);

    public UserInfoView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}