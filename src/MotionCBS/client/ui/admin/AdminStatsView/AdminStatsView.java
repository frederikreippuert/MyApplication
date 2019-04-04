package MotionCBS.client.ui.admin.AdminStatsView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class AdminStatsView extends Composite {
    interface AdminStatsViewUiBinder extends UiBinder<HTMLPanel, AdminStatsView> {
    }

    private static AdminStatsViewUiBinder ourUiBinder = GWT.create(AdminStatsViewUiBinder.class);

    public AdminStatsView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}