package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.dao.UserDAO;
import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.EmailResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class M365ServiceImpl implements M365Service {

    private final UserDAO userDAO;

    public M365ServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ArrayList<ActivityData.M365> getM365Mails() {
        ArrayList<ActivityData.M365> m365List = new ArrayList<>();
        List<EmailResponse.EmailListResponse> emailListResponse = new ArrayList<>();
        EmailResponse emailResponse = userDAO.getM365Mails();
        if (null != emailResponse && null != emailResponse.getValue()) {
            emailListResponse = emailResponse.getValue();
        }

        for (EmailResponse.EmailListResponse email : emailListResponse) {
            ActivityData.M365 m365 = new ActivityData.M365();
            m365.setTimestamp(email.getReceivedDateTime());
            m365.setEvent(email.getSubject());
            m365List.add(m365);
        }

        return m365List;
    }
}
