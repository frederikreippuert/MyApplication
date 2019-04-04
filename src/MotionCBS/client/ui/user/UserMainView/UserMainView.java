package MotionCBS.client.ui.user.UserMainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class UserMainView extends Composite {
    interface UserMainViewUiBinder extends UiBinder<HTMLPanel, UserMainView> {
    }

    private static UserMainViewUiBinder ourUiBinder = GWT.create(UserMainViewUiBinder.class);

    public UserMainView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}