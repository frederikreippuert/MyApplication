package MotionCBS.client.ui.admin.AdminUserChangeInfoView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class AdminUserChangeInfoView extends Composite {
    interface AdminUserChangeInfoViewUiBinder extends UiBinder<HTMLPanel, AdminUserChangeInfoView> {
    }

    private static AdminUserChangeInfoViewUiBinder ourUiBinder = GWT.create(AdminUserChangeInfoViewUiBinder.class);

    public AdminUserChangeInfoView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}