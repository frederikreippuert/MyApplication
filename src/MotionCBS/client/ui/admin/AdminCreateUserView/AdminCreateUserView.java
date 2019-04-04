package MotionCBS.client.ui.admin.AdminCreateUserView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class AdminCreateUserView extends Composite {
    interface AdminCreateUserViewUiBinder extends UiBinder<HTMLPanel, AdminCreateUserView> {
    }

    private static AdminCreateUserViewUiBinder ourUiBinder = GWT.create(AdminCreateUserViewUiBinder.class);

    public AdminCreateUserView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}